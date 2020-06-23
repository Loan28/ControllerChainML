package com.chainML.service;

import com.google.gson.*;
import com.google.protobuf.ByteString;
import com.chainML.pb.*;
import io.grpc.ManagedChannel;
import com.chainML.pb.chainMLServiceGrpc.chainMLServiceBlockingStub;

import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;

public class connection {
    private static final Logger logger = Logger.getLogger(connection.class.getName());

    private final ManagedChannel channel;
    private final chainMLServiceBlockingStub blockingStub;
    private final chainMLServiceGrpc.chainMLServiceStub asyncStub;


    public connection(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = chainMLServiceGrpc.newBlockingStub(channel);
        asyncStub = chainMLServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }
    public void defineOrder(String name) {
        OrderRequest request = OrderRequest.newBuilder().setName(name).build();
        OrderReply response;
        try {
            response = blockingStub.defineOrder(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }


    //Function to upload file to the server, arg: file path
    public void uploadFile(String imagePath, String type) throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<UploadFileRequest> requestObserver = asyncStub.uploadFile(new StreamObserver<UploadFileResponse>() {
                    @Override
                    public void onNext(UploadFileResponse response) {
                        logger.info("receive response: " + response);
                    }

                    @Override
                    public void onError(Throwable t) {
                        logger.log(Level.SEVERE, "upload failed: " + t);
                        finishLatch.countDown();
                    }

                    @Override
                    public void onCompleted() {
                        logger.info("file uploaded");
                        finishLatch.countDown();
                    }
                });

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(imagePath);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "cannot read file " + e.getMessage());
        }

        String imageType = imagePath.substring(imagePath.lastIndexOf("."));
        ImageInfo info = ImageInfo.newBuilder().setImageType(imageType).build();
        TypeFile typeFile = TypeFile.newBuilder().setTypefile(type).build();
        UploadFileRequest request = UploadFileRequest.newBuilder().setInfo(info).setTypeFile(typeFile).build();

        try {
            requestObserver.onNext(request);
            logger.info("sent file info" + info);

            byte[] buffer = new byte[1024];
           //fileInputStream.getChannel().size();
            while (true) {
                int n = fileInputStream.read(buffer);
                if (n <= 0) {
                    break;
                }

                if (finishLatch.getCount() == 0) {
                    return;
                }
                request = UploadFileRequest.newBuilder()
                        .setChunkData(ByteString.copyFrom(buffer, 0, n))
                        .build();
                requestObserver.onNext(request);
                logger.info("sent file chunk with size: " + n);
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "unexcepted error: " + e.getMessage());
            requestObserver.onError(e);
            return;
        }

        requestObserver.onCompleted();

        if (!finishLatch.await(1, TimeUnit.MINUTES)){
            logger.warning("request cannot finish within 1 minute");
        }
    }

    public static connection setUpDevices(String ip){
        connection Device = new connection(ip, 50051);
        return Device;
    }

    public void DeployAlgo(String ML1Path, String Label1Path, String nextDevice, connection Device) throws InterruptedException {
            Device.defineOrder(nextDevice);
            Device.uploadFile("tmp/" + ML1Path, "model");
            Device.uploadFile("tmp/" + Label1Path, "label");
    }
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        JsonParser parser = new JsonParser();
        JsonObject jsonLinux = null;
        JsonObject jsonAndroid = null;
        JsonObject jsonRPI = null;
        String model = "";
        String label = "";
        String nextDevice = "";
        String first = "";
        String firstDevice = "";

        Object json = parser.parse(new FileReader("tmp/controller.json"));
        JsonObject jsonObject = (JsonObject) json;

        JsonArray listDevice = (JsonArray) jsonObject.get("device");
        JsonArray ListML = (JsonArray) jsonObject.get("application");

        for(int i = 0; i < listDevice.size();i++){
            if(listDevice.get(i).getAsJsonObject().get("type").getAsString().equals("Linux")){
                jsonLinux = listDevice.get(i).getAsJsonObject();
            } else if(listDevice.get(i).getAsJsonObject().get("type").getAsString().equals("Android")){
                jsonAndroid = listDevice.get(i).getAsJsonObject();
            } else{
                jsonRPI = listDevice.get(i).getAsJsonObject();
            }
        }

        connection DeviceLinux = setUpDevices(jsonLinux.get("ip").getAsString());
        connection DeviceAndroid = setUpDevices(jsonAndroid.get("ip").getAsString());
        connection DeviceRPI = setUpDevices(jsonRPI.get("ip").getAsString());

        for(int i = 0; i < ListML.size();i++){
            model = ListML.get(i).getAsJsonObject().get("model").getAsString();
            label = ListML.get(i).getAsJsonObject().get("label").getAsString();
            nextDevice = ListML.get(i).getAsJsonObject().get("nextDevice").getAsString();
            first = ListML.get(i).getAsJsonObject().get("first").getAsString();

            if(ListML.get(i).getAsJsonObject().get("location").getAsString().equals(jsonLinux.get("id").getAsString())){
                if(first.equals("1"))
                {
                    firstDevice = "Linux";
                }
                DeviceLinux.DeployAlgo(model, label, nextDevice, DeviceLinux);
            } else if(ListML.get(i).getAsJsonObject().get("location").getAsString().equals(jsonAndroid.get("id").getAsString())){
                if(first.equals("1"))
                {
                    firstDevice = "Android";
                }
                DeviceAndroid.DeployAlgo(model, label, nextDevice, DeviceAndroid);
            } else{
                if(first.equals("1"))
                {
                    firstDevice = "RPI";
                }
                //DeviceRPI.DeployAlgo(model, label, nextDevice, DeviceRPI);
            }
        }

        try {
            if(firstDevice.equals("Android")) {
                DeviceAndroid.uploadFile("tmp/goose.jpg", "image");
            }
        }
        finally {
            DeviceLinux.shutdown();
            DeviceAndroid.shutdown();
            DeviceRPI.shutdown();
        }
    }

}

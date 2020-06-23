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
        Object json = parser.parse(new FileReader("tmp/controller.json"));
        JsonObject jsonObject = (JsonObject) json;
        JsonArray list = (JsonArray) jsonObject.get("device");
        JsonElement dev1 = list.get(0);
        JsonElement dev2 = list.get(1);
        JsonElement dev3 = list.get(2);

        JsonObject jsonDev1 = (JsonObject) dev1;
        JsonObject jsonDev2 = (JsonObject) dev2;
        JsonObject jsonDev3 = (JsonObject) dev3;
        JsonObject jsonTemp = null;


        //Put devices in order
        if(jsonDev1.get("order").getAsString().equals("2")) {
            if(jsonDev2.get("order").getAsString().equals("1")){
                jsonTemp = jsonDev2;
                jsonDev2 = jsonDev1;
                jsonDev1 = jsonTemp;
            }else{
                jsonTemp = jsonDev2;
                jsonDev2 = jsonDev1;
                jsonDev1 = jsonDev3;
                jsonDev3 = jsonTemp;;
            }
        }else if(jsonDev1.get("order").getAsString().equals("3")){
            if(jsonDev2.get("order").getAsString().equals("1"))
            {
                jsonTemp = jsonDev1;// t = 3
                jsonDev1 = jsonDev2;// 1 = 1
                jsonDev2 = jsonDev3;// 2 = 2
                jsonDev3 = jsonTemp;// 3 = 3
            }
            else{
                jsonTemp = jsonDev3;
                jsonDev3 = jsonDev1;
                jsonDev1 = jsonTemp;
            }
        }
        else{
            if(jsonDev2.get("order").getAsString().equals("3")){
                jsonTemp = jsonDev3;
                jsonDev3 = jsonDev2;
                jsonDev2 = jsonTemp;
            }
        }

        connection Device1 = setUpDevices(jsonDev1.get("ip").getAsString());
        connection Device2 = setUpDevices(jsonDev2.get("ip").getAsString());
        connection Device3 = setUpDevices(jsonDev3.get("ip").getAsString());

        //Device1.DeployAlgo(jsonDev1.get("model").getAsString(),jsonDev1.get("label").getAsString(),jsonDev2.get("type").getAsString(), Device1);
        //Device2.DeployAlgo(jsonDev2.get("model").getAsString(),jsonDev2.get("label").getAsString(),jsonDev3.get("type").getAsString(), Device2);
        //Device3.DeployAlgo(jsonDev3.get("model").getAsString(),jsonDev3.get("label").getAsString(),"end", Device3);
        Device1.DeployAlgo(jsonDev1.get("model").getAsString(),jsonDev1.get("label").getAsString(),"end", Device1);

        try {
           Device1.uploadFile("tmp/goose.jpg", "image");
        }
        finally {
            Device1.shutdown();
            Device2.shutdown();
             Device3.shutdown();
        }
    }

}

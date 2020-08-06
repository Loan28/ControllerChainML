package com.chainML.service;

import com.chainML.pb.*;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controllerClient {

    private static final Logger logger = Logger.getLogger(connection.class.getName());

    private final ManagedChannel channel;
    private final chainMLServiceGrpc.chainMLServiceBlockingStub blockingStub;
    private final chainMLServiceGrpc.chainMLServiceStub asyncStub;


    public controllerClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = chainMLServiceGrpc.newBlockingStub(channel);
        asyncStub = chainMLServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
    }
    //
    //function to send the order of the devices in chain
    public void defineOrder(String name) {
        OrderRequest request = OrderRequest.newBuilder().setName(name).build();
        OrderReply response;
        try {
            response = blockingStub.defineOrder(request);
            logger.info(response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }
    //
    //send ip and port of the controller to the devices
    public void defineController(String ip, int port) {
        DefineControllerRequest request = DefineControllerRequest.newBuilder()
                .setIpController(ip)
                .setPortController(port)
                .build();
        DefineControllerReply response;
        try {
            response = blockingStub.defineController(request);
            logger.info(response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }
    //
    //Sends :
    // - All the information needed from a device to run a model
    // - Action that should be taken once this model has been run
    public void defineModelLabel(String model, String label, String condition, String condition2, String action, String action2, String type, String applicationType, String location, String ipController, int portController){
        DefineModelLabelRequest request = DefineModelLabelRequest.newBuilder()
                .setModel(model)
                .setLabel(label)
                .setCondition(condition)
                .setCondition2(condition2)
                .setAction(action)
                .setAction2(action2)
                .setType(type)
                .setApplicationType(applicationType)
                .setLocation(location)
                .setIpConroller(ipController)
                .setPortController(portController)
                .build();
        DefineModelLabelReply response;
        try {
            response = blockingStub.defineModelLabel(request);
            logger.info(response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }
    //
    //Not used for now
    public void getSpec(String name) {
        OrderRequest request = OrderRequest.newBuilder().setName(name).build();
        OrderReply response;
        try {
            response = blockingStub.getSpecs(request);
            logger.info(response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }

    //
    //Function to upload file to a device
    public void uploadFile(String imagePath, String type) throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<UploadFileRequest> requestObserver = asyncStub.uploadFile(new StreamObserver<UploadFileResponse>() {
            @Override
            public void onNext(UploadFileResponse response) {
                //logger.info("receive response: " + response);
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.SEVERE, "upload failed: " + t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Client : file uploaded");
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
        String imageName = imagePath.substring(imagePath.lastIndexOf("/"),imagePath.lastIndexOf("."));
        FileInfo info = FileInfo.newBuilder().setImageType(imageType).build();
        TypeFile typeFile = TypeFile.newBuilder().setTypefile(type).build();
        FileName fileName = FileName.newBuilder().setFilename(imageName).build();
        UploadFileRequest request = UploadFileRequest.newBuilder().setInfo(info).setTypeFile(typeFile).setFileName(fileName).build();

        try {
            requestObserver.onNext(request);
            logger.info("Client : sent file info " + info);

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
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Client : unexcepted error: " + e.getMessage());
            requestObserver.onError(e);
            return;
        }

        requestObserver.onCompleted();

        if (!finishLatch.await(60, TimeUnit.MINUTES)){
            logger.warning("Client : request cannot finish within 1 minute");
        }
    }

    //
    //Call the functions to make the device specified ready to run in the chain
    public void DeployAlgo(String model, String label, String type,String condition, String condition2, String action, String action2, String nextDevice, String applicationType, String location, String ipController, int portController, controllerClient Device) throws InterruptedException {
        Device.defineOrder(nextDevice);
        Device.defineController(ipController, portController);
        Device.defineModelLabel(model, label, condition, condition2, action, action2, type, applicationType, location, ipController, portController);

    }
}

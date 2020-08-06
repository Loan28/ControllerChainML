package com.chainML.service;

import com.chainML.pb.chainMLServiceGrpc;
import com.chainML.pb.*;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class controllerService extends chainMLServiceGrpc.chainMLServiceImplBase {

    String nextDevice;
    private static final Logger logger = Logger.getLogger(controllerService.class.getName());
    private FileStore fileStore;

    long execTimeLinux = 0;
    long execTimeAndroid = 0;
    long execTimeRPI = 0;
    long memoryTimeLinux = 0;
    long memoryTimeAndroid = 0;
    long memoryTimeRPI = 0;
    long uploadTimeLinux = 0;
    long uploadTimeAndroid = 0;
    long uploadTimeRPI = 0;
    String fileID = "";
    public controllerService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Override
    public void defineOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) {
        logger.info("Server : " + request.getName());
        OrderReply reply = OrderReply.newBuilder().setMessage("received").build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    //
    //Function that stores file receive from a client
    @Override
    public StreamObserver<UploadFileRequest> uploadFile(StreamObserver<UploadFileResponse> responseObserver) {
        return new StreamObserver<UploadFileRequest>() {
            private String fileType;
            private ByteArrayOutputStream fileData;
            private TypeFile type_file;
            private FileName file_name;

            @Override
            public void onNext(UploadFileRequest request) {
                if(request.getDataCase() == UploadFileRequest.DataCase.INFO) {
                    FileInfo info = request.getInfo();
                    type_file = request.getTypeFile();
                    fileType = info.getImageType();
                    fileData = new ByteArrayOutputStream();
                    file_name = request.getFileName();
                    return;

                }
                ByteString chunkData = request.getChunkData();
                try {
                    chunkData.writeTo(fileData);
                } catch (IOException e) {
                    responseObserver.onError(
                            Status.INTERNAL
                                    .withDescription("cannot write chunk data: " + e.getMessage())
                                    .asRuntimeException()
                    );
                    return;
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.warning(t.getMessage());
            }

            @Override
            public void onCompleted() {

                int imageSize = fileData.size();
                String imageName = "image"+System.nanoTime();
                try {
                    fileID = fileStore.Save(fileType, fileData, imageName);
                    logger.info("receive " + type_file.getTypefile());

                } catch (IOException e) {
                    responseObserver.onError(
                            Status.INTERNAL
                                    .withDescription("cannot save the " + type_file.getTypefile() + " to the store: " + e.getMessage())
                                    .asRuntimeException()
                    );
                }

                UploadFileResponse response = UploadFileResponse.newBuilder()
                        .setId(fileID)
                        .setSize(imageSize)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();

            }

        };
    }

    @Override
    public void sendUploadTime(TimeRequest request, StreamObserver<TimeReply> responseObserver) {
        logger.info("Upload time : " + request.getTime());
        if(request.getDevice().equals("0000001")){      //0000001 is the id of the device
            uploadTimeLinux += request.getTime();
            logger.info("TOTAL Upload time 0000001 : " + uploadTimeLinux);
        }else if(request.getDevice().equals("0000003")){
            uploadTimeAndroid += request.getTime();
            logger.info("TOTAL Upload time 0000003 : " + uploadTimeAndroid);
        }else{
            uploadTimeRPI += request.getTime();
            logger.info("TOTAL Upload time 0000002 : " + uploadTimeRPI);
        }
        TimeReply reply = TimeReply.newBuilder().setName("time upload received").build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void sendExecTime(TimeRequest request, StreamObserver<TimeReply> responseObserver) {
        logger.info("Exec time : " + request.getTime());
        if(request.getDevice().equals("0000001")){
            execTimeLinux += request.getTime();
            logger.info("TOTAL Exec time 0000001 : " + execTimeLinux);
        }else if(request.getDevice().equals("0000003")){
            execTimeAndroid += request.getTime();
            logger.info("TOTAL Exec time 0000003 : " + execTimeAndroid);
        }else if(request.getDevice().equals("0000002")){
            execTimeRPI += request.getTime();
            logger.info("TOTAL Exec time 0000002 : " + execTimeRPI);
        }else if(request.getDevice().equals("0000001m")){
            memoryTimeLinux += request.getTime();
            logger.info("TOTAL Mem time 0000001m : " + memoryTimeLinux);
        }else if(request.getDevice().equals("0000003m")){
            memoryTimeAndroid += request.getTime();
            logger.info("TOTAL Mem time 0000003m : " + memoryTimeAndroid);
        }else if(request.getDevice().equals("0000002m")){
            memoryTimeRPI += request.getTime();
            logger.info("TOTAL Mem time 0000002m : " + memoryTimeRPI);
        }else{
            logger.info("unknown request");
        }


        TimeReply reply = TimeReply.newBuilder().setName("time exec received").build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

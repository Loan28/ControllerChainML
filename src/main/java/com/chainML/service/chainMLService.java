package com.chainML.service;

import com.chainML.pb.chainMLServiceGrpc;
import com.chainML.pb.*;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.logging.Logger;

public class chainMLService extends chainMLServiceGrpc.chainMLServiceImplBase {

    String nextDevice;
    private static final Logger logger = Logger.getLogger(chainMLService.class.getName());


    public chainMLService(){}

    @Override
    public void defineOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) {
        logger.info("Server : " + request.getName());
        OrderReply reply = OrderReply.newBuilder().setMessage("received").build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}

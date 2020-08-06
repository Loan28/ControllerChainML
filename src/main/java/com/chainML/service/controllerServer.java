package com.chainML.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class controllerServer {

    private static final Logger logger = Logger.getLogger(controllerServer.class.getName());
    private  final int port;
    private final Server server;


    public controllerServer(int port,FileStore fileStore) {
        this(ServerBuilder.forPort(port), port, fileStore);
    }

    public controllerServer(ServerBuilder serverBuilder, int port, FileStore fileStore){
        this.port = port;
        controllerService Service = new controllerService(fileStore);
        server = serverBuilder.addService(Service).build();
    }

    public void start() throws IOException{
        server.start();
        logger.info("Server : server started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shut down gRPC server because JVM shuts down");
                try {
                    controllerServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("server shut down");
            }
        });
    }
    public void stop() throws  InterruptedException {
        if (server != null) {
            server.shutdownNow().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

}


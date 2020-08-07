package com.chainML.pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: chainML_service.proto")
public final class chainMLServiceGrpc {

  private chainMLServiceGrpc() {}

  public static final String SERVICE_NAME = "chainML.chainMLService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.TimeRequest,
      com.chainML.pb.TimeReply> getSendUploadTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendUploadTime",
      requestType = com.chainML.pb.TimeRequest.class,
      responseType = com.chainML.pb.TimeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chainML.pb.TimeRequest,
      com.chainML.pb.TimeReply> getSendUploadTimeMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.TimeRequest, com.chainML.pb.TimeReply> getSendUploadTimeMethod;
    if ((getSendUploadTimeMethod = chainMLServiceGrpc.getSendUploadTimeMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getSendUploadTimeMethod = chainMLServiceGrpc.getSendUploadTimeMethod) == null) {
          chainMLServiceGrpc.getSendUploadTimeMethod = getSendUploadTimeMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.TimeRequest, com.chainML.pb.TimeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendUploadTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.TimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.TimeReply.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("sendUploadTime"))
              .build();
        }
      }
    }
    return getSendUploadTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.TimeRequest,
      com.chainML.pb.TimeReply> getSendExecTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendExecTime",
      requestType = com.chainML.pb.TimeRequest.class,
      responseType = com.chainML.pb.TimeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chainML.pb.TimeRequest,
      com.chainML.pb.TimeReply> getSendExecTimeMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.TimeRequest, com.chainML.pb.TimeReply> getSendExecTimeMethod;
    if ((getSendExecTimeMethod = chainMLServiceGrpc.getSendExecTimeMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getSendExecTimeMethod = chainMLServiceGrpc.getSendExecTimeMethod) == null) {
          chainMLServiceGrpc.getSendExecTimeMethod = getSendExecTimeMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.TimeRequest, com.chainML.pb.TimeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendExecTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.TimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.TimeReply.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("sendExecTime"))
              .build();
        }
      }
    }
    return getSendExecTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.OrderRequest,
      com.chainML.pb.OrderReply> getDefineOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DefineOrder",
      requestType = com.chainML.pb.OrderRequest.class,
      responseType = com.chainML.pb.OrderReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chainML.pb.OrderRequest,
      com.chainML.pb.OrderReply> getDefineOrderMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.OrderRequest, com.chainML.pb.OrderReply> getDefineOrderMethod;
    if ((getDefineOrderMethod = chainMLServiceGrpc.getDefineOrderMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getDefineOrderMethod = chainMLServiceGrpc.getDefineOrderMethod) == null) {
          chainMLServiceGrpc.getDefineOrderMethod = getDefineOrderMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.OrderRequest, com.chainML.pb.OrderReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DefineOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.OrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.OrderReply.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("DefineOrder"))
              .build();
        }
      }
    }
    return getDefineOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.DefineModelLabelRequest,
      com.chainML.pb.DefineModelLabelReply> getDefineModelLabelMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DefineModelLabel",
      requestType = com.chainML.pb.DefineModelLabelRequest.class,
      responseType = com.chainML.pb.DefineModelLabelReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chainML.pb.DefineModelLabelRequest,
      com.chainML.pb.DefineModelLabelReply> getDefineModelLabelMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.DefineModelLabelRequest, com.chainML.pb.DefineModelLabelReply> getDefineModelLabelMethod;
    if ((getDefineModelLabelMethod = chainMLServiceGrpc.getDefineModelLabelMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getDefineModelLabelMethod = chainMLServiceGrpc.getDefineModelLabelMethod) == null) {
          chainMLServiceGrpc.getDefineModelLabelMethod = getDefineModelLabelMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.DefineModelLabelRequest, com.chainML.pb.DefineModelLabelReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DefineModelLabel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.DefineModelLabelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.DefineModelLabelReply.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("DefineModelLabel"))
              .build();
        }
      }
    }
    return getDefineModelLabelMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.DefineControllerRequest,
      com.chainML.pb.DefineControllerReply> getDefineControllerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DefineController",
      requestType = com.chainML.pb.DefineControllerRequest.class,
      responseType = com.chainML.pb.DefineControllerReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chainML.pb.DefineControllerRequest,
      com.chainML.pb.DefineControllerReply> getDefineControllerMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.DefineControllerRequest, com.chainML.pb.DefineControllerReply> getDefineControllerMethod;
    if ((getDefineControllerMethod = chainMLServiceGrpc.getDefineControllerMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getDefineControllerMethod = chainMLServiceGrpc.getDefineControllerMethod) == null) {
          chainMLServiceGrpc.getDefineControllerMethod = getDefineControllerMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.DefineControllerRequest, com.chainML.pb.DefineControllerReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DefineController"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.DefineControllerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.DefineControllerReply.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("DefineController"))
              .build();
        }
      }
    }
    return getDefineControllerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.OrderRequest,
      com.chainML.pb.OrderReply> getGetSpecsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSpecs",
      requestType = com.chainML.pb.OrderRequest.class,
      responseType = com.chainML.pb.OrderReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chainML.pb.OrderRequest,
      com.chainML.pb.OrderReply> getGetSpecsMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.OrderRequest, com.chainML.pb.OrderReply> getGetSpecsMethod;
    if ((getGetSpecsMethod = chainMLServiceGrpc.getGetSpecsMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getGetSpecsMethod = chainMLServiceGrpc.getGetSpecsMethod) == null) {
          chainMLServiceGrpc.getGetSpecsMethod = getGetSpecsMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.OrderRequest, com.chainML.pb.OrderReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSpecs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.OrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.OrderReply.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("getSpecs"))
              .build();
        }
      }
    }
    return getGetSpecsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.chainML.pb.UploadFileRequest,
      com.chainML.pb.UploadFileResponse> getUploadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadFile",
      requestType = com.chainML.pb.UploadFileRequest.class,
      responseType = com.chainML.pb.UploadFileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.chainML.pb.UploadFileRequest,
      com.chainML.pb.UploadFileResponse> getUploadFileMethod() {
    io.grpc.MethodDescriptor<com.chainML.pb.UploadFileRequest, com.chainML.pb.UploadFileResponse> getUploadFileMethod;
    if ((getUploadFileMethod = chainMLServiceGrpc.getUploadFileMethod) == null) {
      synchronized (chainMLServiceGrpc.class) {
        if ((getUploadFileMethod = chainMLServiceGrpc.getUploadFileMethod) == null) {
          chainMLServiceGrpc.getUploadFileMethod = getUploadFileMethod =
              io.grpc.MethodDescriptor.<com.chainML.pb.UploadFileRequest, com.chainML.pb.UploadFileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.UploadFileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chainML.pb.UploadFileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new chainMLServiceMethodDescriptorSupplier("UploadFile"))
              .build();
        }
      }
    }
    return getUploadFileMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static chainMLServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<chainMLServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<chainMLServiceStub>() {
        @java.lang.Override
        public chainMLServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new chainMLServiceStub(channel, callOptions);
        }
      };
    return chainMLServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static chainMLServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<chainMLServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<chainMLServiceBlockingStub>() {
        @java.lang.Override
        public chainMLServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new chainMLServiceBlockingStub(channel, callOptions);
        }
      };
    return chainMLServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static chainMLServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<chainMLServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<chainMLServiceFutureStub>() {
        @java.lang.Override
        public chainMLServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new chainMLServiceFutureStub(channel, callOptions);
        }
      };
    return chainMLServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class chainMLServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendUploadTime(com.chainML.pb.TimeRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.TimeReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSendUploadTimeMethod(), responseObserver);
    }

    /**
     */
    public void sendExecTime(com.chainML.pb.TimeRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.TimeReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSendExecTimeMethod(), responseObserver);
    }

    /**
     */
    public void defineOrder(com.chainML.pb.OrderRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.OrderReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDefineOrderMethod(), responseObserver);
    }

    /**
     */
    public void defineModelLabel(com.chainML.pb.DefineModelLabelRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.DefineModelLabelReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDefineModelLabelMethod(), responseObserver);
    }

    /**
     */
    public void defineController(com.chainML.pb.DefineControllerRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.DefineControllerReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDefineControllerMethod(), responseObserver);
    }

    /**
     */
    public void getSpecs(com.chainML.pb.OrderRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.OrderReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSpecsMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.chainML.pb.UploadFileRequest> uploadFile(
        io.grpc.stub.StreamObserver<com.chainML.pb.UploadFileResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadFileMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendUploadTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chainML.pb.TimeRequest,
                com.chainML.pb.TimeReply>(
                  this, METHODID_SEND_UPLOAD_TIME)))
          .addMethod(
            getSendExecTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chainML.pb.TimeRequest,
                com.chainML.pb.TimeReply>(
                  this, METHODID_SEND_EXEC_TIME)))
          .addMethod(
            getDefineOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chainML.pb.OrderRequest,
                com.chainML.pb.OrderReply>(
                  this, METHODID_DEFINE_ORDER)))
          .addMethod(
            getDefineModelLabelMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chainML.pb.DefineModelLabelRequest,
                com.chainML.pb.DefineModelLabelReply>(
                  this, METHODID_DEFINE_MODEL_LABEL)))
          .addMethod(
            getDefineControllerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chainML.pb.DefineControllerRequest,
                com.chainML.pb.DefineControllerReply>(
                  this, METHODID_DEFINE_CONTROLLER)))
          .addMethod(
            getGetSpecsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.chainML.pb.OrderRequest,
                com.chainML.pb.OrderReply>(
                  this, METHODID_GET_SPECS)))
          .addMethod(
            getUploadFileMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.chainML.pb.UploadFileRequest,
                com.chainML.pb.UploadFileResponse>(
                  this, METHODID_UPLOAD_FILE)))
          .build();
    }
  }

  /**
   */
  public static final class chainMLServiceStub extends io.grpc.stub.AbstractAsyncStub<chainMLServiceStub> {
    private chainMLServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected chainMLServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new chainMLServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendUploadTime(com.chainML.pb.TimeRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.TimeReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendUploadTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendExecTime(com.chainML.pb.TimeRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.TimeReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendExecTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void defineOrder(com.chainML.pb.OrderRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.OrderReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDefineOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void defineModelLabel(com.chainML.pb.DefineModelLabelRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.DefineModelLabelReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDefineModelLabelMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void defineController(com.chainML.pb.DefineControllerRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.DefineControllerReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDefineControllerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSpecs(com.chainML.pb.OrderRequest request,
        io.grpc.stub.StreamObserver<com.chainML.pb.OrderReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSpecsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.chainML.pb.UploadFileRequest> uploadFile(
        io.grpc.stub.StreamObserver<com.chainML.pb.UploadFileResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class chainMLServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<chainMLServiceBlockingStub> {
    private chainMLServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected chainMLServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new chainMLServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.chainML.pb.TimeReply sendUploadTime(com.chainML.pb.TimeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendUploadTimeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.chainML.pb.TimeReply sendExecTime(com.chainML.pb.TimeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendExecTimeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.chainML.pb.OrderReply defineOrder(com.chainML.pb.OrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getDefineOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.chainML.pb.DefineModelLabelReply defineModelLabel(com.chainML.pb.DefineModelLabelRequest request) {
      return blockingUnaryCall(
          getChannel(), getDefineModelLabelMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.chainML.pb.DefineControllerReply defineController(com.chainML.pb.DefineControllerRequest request) {
      return blockingUnaryCall(
          getChannel(), getDefineControllerMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.chainML.pb.OrderReply getSpecs(com.chainML.pb.OrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSpecsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class chainMLServiceFutureStub extends io.grpc.stub.AbstractFutureStub<chainMLServiceFutureStub> {
    private chainMLServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected chainMLServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new chainMLServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chainML.pb.TimeReply> sendUploadTime(
        com.chainML.pb.TimeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendUploadTimeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chainML.pb.TimeReply> sendExecTime(
        com.chainML.pb.TimeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendExecTimeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chainML.pb.OrderReply> defineOrder(
        com.chainML.pb.OrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDefineOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chainML.pb.DefineModelLabelReply> defineModelLabel(
        com.chainML.pb.DefineModelLabelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDefineModelLabelMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chainML.pb.DefineControllerReply> defineController(
        com.chainML.pb.DefineControllerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDefineControllerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chainML.pb.OrderReply> getSpecs(
        com.chainML.pb.OrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSpecsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_UPLOAD_TIME = 0;
  private static final int METHODID_SEND_EXEC_TIME = 1;
  private static final int METHODID_DEFINE_ORDER = 2;
  private static final int METHODID_DEFINE_MODEL_LABEL = 3;
  private static final int METHODID_DEFINE_CONTROLLER = 4;
  private static final int METHODID_GET_SPECS = 5;
  private static final int METHODID_UPLOAD_FILE = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final chainMLServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(chainMLServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_UPLOAD_TIME:
          serviceImpl.sendUploadTime((com.chainML.pb.TimeRequest) request,
              (io.grpc.stub.StreamObserver<com.chainML.pb.TimeReply>) responseObserver);
          break;
        case METHODID_SEND_EXEC_TIME:
          serviceImpl.sendExecTime((com.chainML.pb.TimeRequest) request,
              (io.grpc.stub.StreamObserver<com.chainML.pb.TimeReply>) responseObserver);
          break;
        case METHODID_DEFINE_ORDER:
          serviceImpl.defineOrder((com.chainML.pb.OrderRequest) request,
              (io.grpc.stub.StreamObserver<com.chainML.pb.OrderReply>) responseObserver);
          break;
        case METHODID_DEFINE_MODEL_LABEL:
          serviceImpl.defineModelLabel((com.chainML.pb.DefineModelLabelRequest) request,
              (io.grpc.stub.StreamObserver<com.chainML.pb.DefineModelLabelReply>) responseObserver);
          break;
        case METHODID_DEFINE_CONTROLLER:
          serviceImpl.defineController((com.chainML.pb.DefineControllerRequest) request,
              (io.grpc.stub.StreamObserver<com.chainML.pb.DefineControllerReply>) responseObserver);
          break;
        case METHODID_GET_SPECS:
          serviceImpl.getSpecs((com.chainML.pb.OrderRequest) request,
              (io.grpc.stub.StreamObserver<com.chainML.pb.OrderReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_FILE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadFile(
              (io.grpc.stub.StreamObserver<com.chainML.pb.UploadFileResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class chainMLServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    chainMLServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.chainML.pb.ChainMLService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("chainMLService");
    }
  }

  private static final class chainMLServiceFileDescriptorSupplier
      extends chainMLServiceBaseDescriptorSupplier {
    chainMLServiceFileDescriptorSupplier() {}
  }

  private static final class chainMLServiceMethodDescriptorSupplier
      extends chainMLServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    chainMLServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (chainMLServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new chainMLServiceFileDescriptorSupplier())
              .addMethod(getSendUploadTimeMethod())
              .addMethod(getSendExecTimeMethod())
              .addMethod(getDefineOrderMethod())
              .addMethod(getDefineModelLabelMethod())
              .addMethod(getDefineControllerMethod())
              .addMethod(getGetSpecsMethod())
              .addMethod(getUploadFileMethod())
              .build();
        }
      }
    }
    return result;
  }
}

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.2)",
    comments = "Source: GrpcInterface.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GrpcServiceGrpc {

  private GrpcServiceGrpc() {}

  public static final String SERVICE_NAME = "GrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<DistanceTwoPointsRequest,
      DistanceTwoPointsResponse> getDistanceTwoPointsProcedureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "distanceTwoPointsProcedure",
      requestType = DistanceTwoPointsRequest.class,
      responseType = DistanceTwoPointsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DistanceTwoPointsRequest,
      DistanceTwoPointsResponse> getDistanceTwoPointsProcedureMethod() {
    io.grpc.MethodDescriptor<DistanceTwoPointsRequest, DistanceTwoPointsResponse> getDistanceTwoPointsProcedureMethod;
    if ((getDistanceTwoPointsProcedureMethod = GrpcServiceGrpc.getDistanceTwoPointsProcedureMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getDistanceTwoPointsProcedureMethod = GrpcServiceGrpc.getDistanceTwoPointsProcedureMethod) == null) {
          GrpcServiceGrpc.getDistanceTwoPointsProcedureMethod = getDistanceTwoPointsProcedureMethod =
              io.grpc.MethodDescriptor.<DistanceTwoPointsRequest, DistanceTwoPointsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "distanceTwoPointsProcedure"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DistanceTwoPointsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DistanceTwoPointsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("distanceTwoPointsProcedure"))
              .build();
        }
      }
    }
    return getDistanceTwoPointsProcedureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<DistanceThreePointsRequest,
      DistanceThreePointsResponse> getDistanceThreePointsProcedureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "distanceThreePointsProcedure",
      requestType = DistanceThreePointsRequest.class,
      responseType = DistanceThreePointsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DistanceThreePointsRequest,
      DistanceThreePointsResponse> getDistanceThreePointsProcedureMethod() {
    io.grpc.MethodDescriptor<DistanceThreePointsRequest, DistanceThreePointsResponse> getDistanceThreePointsProcedureMethod;
    if ((getDistanceThreePointsProcedureMethod = GrpcServiceGrpc.getDistanceThreePointsProcedureMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getDistanceThreePointsProcedureMethod = GrpcServiceGrpc.getDistanceThreePointsProcedureMethod) == null) {
          GrpcServiceGrpc.getDistanceThreePointsProcedureMethod = getDistanceThreePointsProcedureMethod =
              io.grpc.MethodDescriptor.<DistanceThreePointsRequest, DistanceThreePointsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "distanceThreePointsProcedure"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DistanceThreePointsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DistanceThreePointsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("distanceThreePointsProcedure"))
              .build();
        }
      }
    }
    return getDistanceThreePointsProcedureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceStub>() {
        @java.lang.Override
        public GrpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceStub(channel, callOptions);
        }
      };
    return GrpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceBlockingStub>() {
        @java.lang.Override
        public GrpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceFutureStub>() {
        @java.lang.Override
        public GrpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GrpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void distanceTwoPointsProcedure(DistanceTwoPointsRequest request,
        io.grpc.stub.StreamObserver<DistanceTwoPointsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDistanceTwoPointsProcedureMethod(), responseObserver);
    }

    /**
     */
    public void distanceThreePointsProcedure(DistanceThreePointsRequest request,
        io.grpc.stub.StreamObserver<DistanceThreePointsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDistanceThreePointsProcedureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDistanceTwoPointsProcedureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                DistanceTwoPointsRequest,
                DistanceTwoPointsResponse>(
                  this, METHODID_DISTANCE_TWO_POINTS_PROCEDURE)))
          .addMethod(
            getDistanceThreePointsProcedureMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                DistanceThreePointsRequest,
                DistanceThreePointsResponse>(
                  this, METHODID_DISTANCE_THREE_POINTS_PROCEDURE)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcServiceStub extends io.grpc.stub.AbstractAsyncStub<GrpcServiceStub> {
    private GrpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void distanceTwoPointsProcedure(DistanceTwoPointsRequest request,
        io.grpc.stub.StreamObserver<DistanceTwoPointsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDistanceTwoPointsProcedureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void distanceThreePointsProcedure(DistanceThreePointsRequest request,
        io.grpc.stub.StreamObserver<DistanceThreePointsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDistanceThreePointsProcedureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GrpcServiceBlockingStub> {
    private GrpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public DistanceTwoPointsResponse distanceTwoPointsProcedure(DistanceTwoPointsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDistanceTwoPointsProcedureMethod(), getCallOptions(), request);
    }

    /**
     */
    public DistanceThreePointsResponse distanceThreePointsProcedure(DistanceThreePointsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDistanceThreePointsProcedureMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GrpcServiceFutureStub> {
    private GrpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DistanceTwoPointsResponse> distanceTwoPointsProcedure(
        DistanceTwoPointsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDistanceTwoPointsProcedureMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DistanceThreePointsResponse> distanceThreePointsProcedure(
        DistanceThreePointsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDistanceThreePointsProcedureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DISTANCE_TWO_POINTS_PROCEDURE = 0;
  private static final int METHODID_DISTANCE_THREE_POINTS_PROCEDURE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DISTANCE_TWO_POINTS_PROCEDURE:
          serviceImpl.distanceTwoPointsProcedure((DistanceTwoPointsRequest) request,
              (io.grpc.stub.StreamObserver<DistanceTwoPointsResponse>) responseObserver);
          break;
        case METHODID_DISTANCE_THREE_POINTS_PROCEDURE:
          serviceImpl.distanceThreePointsProcedure((DistanceThreePointsRequest) request,
              (io.grpc.stub.StreamObserver<DistanceThreePointsResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrpcAppProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcService");
    }
  }

  private static final class GrpcServiceFileDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier {
    GrpcServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcServiceMethodDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (GrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcServiceFileDescriptorSupplier())
              .addMethod(getDistanceTwoPointsProcedureMethod())
              .addMethod(getDistanceThreePointsProcedureMethod())
              .build();
        }
      }
    }
    return result;
  }
}

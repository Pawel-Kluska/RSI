import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GrpcServer {
    public static void main(String[] args) {
        MyData.getData();
        int port = 50001;
        System.out.println("Starting server...");
        Server server = ServerBuilder
                .forPort(port)
                .addService(new GrpcServiceImpl()).build();
        try {
            server.start();
            System.out.println("...Server started");
            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class GrpcServiceImpl extends GrpcServiceGrpc.GrpcServiceImplBase {
        public void grpcProcedure(GrpcRequest req, StreamObserver<GrpcResponse> responseObserver) {
            String msg;
            System.out.println("...called GrpcProcedure");
            if (req.getAge() > 18)
                msg = "Mr/Ms " + req.getName();
            else
                msg = "Boy/Girl";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm:ss", new Locale("pl"));

            GrpcResponse response = GrpcResponse.newBuilder()
                    .setMessage("Hello " + msg + " \n" + LocalDateTime.now().format(formatter)).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
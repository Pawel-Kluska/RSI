import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
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
        public void distanceTwoPointsProcedure(DistanceTwoPointsRequest req, StreamObserver<DistanceTwoPointsResponse> responseObserver) {
            System.out.println("...called DistanceTwoPointsProcedure");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm:ss", new Locale("pl"));

            DistanceTwoPointsResponse response = DistanceTwoPointsResponse.newBuilder()
                    .setMessage(DistanceUtils
                            .distanceTwoPoints(req.getLat1(), req.getLon1(), req.getLat2(), req.getLon2())).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        public void distanceThreePointsProcedure(DistanceThreePointsRequest req, StreamObserver<DistanceThreePointsResponse> responseObserver) {
            System.out.println("...called DistanceThreePointsProcedure");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm:ss", new Locale("pl"));

            DistanceThreePointsResponse response = DistanceThreePointsResponse.newBuilder()
                    .setMessage(DistanceUtils
                            .distanceThreePoints(req.getLat1(), req.getLon1(),
                                    req.getLat2(), req.getLon2(),
                                    req.getLat3(), req.getLon3())).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class GrpcClient {
    public static void main(String[] args) {
        MyData.getData();
        String address = "localhost";
        int port = 50001;
        System.out.println("Running grpc client...");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext()
                .build();
        GrpcServiceGrpc.GrpcServiceBlockingStub stub = GrpcServiceGrpc.newBlockingStub(channel);
        Scanner sc = new Scanner(System.in);
        System.out.println("Wybierz opcję: \n1 - Podanie 2 punktóœ\n2 - Podanie 3 punktóœ");
        String option = sc.nextLine();

        switch(option){
            case "1":
                System.out.println("Podaj kolejno współrzędne obu miast - X,Y pierwszego oraz X,Y drugiego");
                DistanceTwoPointsRequest request = DistanceTwoPointsRequest.newBuilder()
                        .setLat1(sc.nextDouble())
                        .setLon1(sc.nextDouble())
                        .setLat2(sc.nextDouble())
                        .setLon2(sc.nextDouble())
                        .build();
                DistanceTwoPointsResponse response = stub.distanceTwoPointsProcedure(request);
                System.out.println(response.getMessage());
                break;
            case "2":
                System.out.println("Podaj kolejno współrzędne trzech miast - X,Y pierwszego, X,Y drugiego oraz X.Y trzeciego");
                DistanceThreePointsRequest request2 = DistanceThreePointsRequest.newBuilder()
                        .setLat1(sc.nextDouble())
                        .setLon1(sc.nextDouble())
                        .setLat2(sc.nextDouble())
                        .setLon2(sc.nextDouble())
                        .setLat3(sc.nextDouble())
                        .setLon3(sc.nextDouble())
                        .build();
                DistanceThreePointsResponse response2 = stub.distanceThreePointsProcedure(request2);
                System.out.println(response2.getMessage());
                break;

            default:
                System.out.println("Nie ma takiej opcji");
        }


        channel.shutdown();
        double lat1Wars = 52.2297;
        double lon1Wars = 21.0117;
        double lat2Madr = 40.4165;
        double lon2Madr = -3.7025;
        double lat3Bue = -34.6131;
        double lon3Bue = -58.3772;
    }
}
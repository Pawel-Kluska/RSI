import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class GrpcClient {
    public static void main(String[] args) {
        MyData.getData();
        String address = "localhost";
        int port = 50001;
        Scanner sc = new Scanner(System.in);
        System.out.println("Wpisz imię");
        String name = sc.nextLine();
        System.out.println("Running grpc client...");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext()
                .build();
        GrpcServiceGrpc.GrpcServiceBlockingStub stub = GrpcServiceGrpc.newBlockingStub(channel);
        GrpcRequest request = GrpcRequest.newBuilder().setName(name)
                .setAge(21)
                .build();
        GrpcResponse response = stub.grpcProcedure(request);
        System.out.println(response.getMessage());
        channel.shutdown();
    }
}
import com.example.grpc.ImageServiceGrpc;
import com.example.grpc.ImageServiceProto;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class GrpcClient {

    public static void main(String[] args) throws IOException {
        MyData.getData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wybierz tryb:");
            System.out.println("1. Download from client");
            System.out.println("2. Download from server");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    startClient();
                    break;
                case 2:
                    startClient2();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    public static void startClient() throws IOException {
        String address = "localhost";
        int port = 50000;
        final Integer[] packageNumber = {0};
        ImageServiceGrpc.ImageServiceStub stub;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext().build();
        stub = ImageServiceGrpc.newStub(channel);
        StreamObserver<ImageServiceProto.ImageChunk> requestObserver = stub.uploadImage(new StreamObserver<ImageServiceProto.UploadStatus>() {

            @Override
            public void onNext(ImageServiceProto.UploadStatus value) {
                packageNumber[0]++;
                System.out.println("Numer paczki " + packageNumber[0]);
                System.out.println("Upload status: " + value.getSuccess());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Upload completed");
            }
        });
        String fileName = "GrpcClient/src/main/resources/images/image.jpg";
        try (FileInputStream input = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                ImageServiceProto.ImageChunk chunk = ImageServiceProto.ImageChunk.newBuilder()
                        .setData(ByteString.copyFrom(buffer, 0, bytesRead))
                        .build();
                requestObserver.onNext(chunk);
            }
        }
        requestObserver.onCompleted();
        channel.shutdown();
    }

    public static void startClient2() throws IOException {
        String address = "localhost";
        int port = 50000;
        final Integer[] packageNumber = {0};
        ImageServiceGrpc.ImageServiceStub stub;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext().build();
        stub = ImageServiceGrpc.newStub(channel);
        ImageServiceProto.ImageRequest request = ImageServiceProto.ImageRequest.newBuilder().build();
        FileOutputStream fileOutputStream = new FileOutputStream("GrpcClient/src/main/resources/images/image_from_server.jpg");
        stub.downloadImage(request, new StreamObserver<ImageServiceProto.ImageChunk>() {
            @Override
            public void onNext(ImageServiceProto.ImageChunk response) {
                try {
                    packageNumber[0]++;
                    System.out.println("Numer paczki " + packageNumber[0]);
                    fileOutputStream.write(response.getData().toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                channel.shutdown();
            }
        });
    }
}



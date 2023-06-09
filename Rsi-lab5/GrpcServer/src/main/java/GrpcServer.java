import com.example.grpc.ImageServiceGrpc;
import com.example.grpc.ImageServiceProto;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        MyData.getData();
        startServer();
    }

    public static void startServer() throws IOException, InterruptedException {
        int port = 50000;
        Server server = ServerBuilder.forPort(port)
                .addService(new ImageServiceImpl())
                .build();

        try {
            server.start();
            System.out.println("Server started...");
            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ImageServiceImpl extends ImageServiceGrpc.ImageServiceImplBase {
        private FileOutputStream outputStream;

        @Override
        public StreamObserver<ImageServiceProto.ImageChunk> uploadImage(StreamObserver<ImageServiceProto.UploadStatus> responseObserver) {

            final Integer[] packageNumber = {0};
            try {
                outputStream = new FileOutputStream("GrpcServer/src/main/resources/image/image_from_client.jpg");
            } catch (IOException e) {
                responseObserver.onError(e);
            }
            return new StreamObserver<>() {
                @Override
                public void onNext(ImageServiceProto.ImageChunk value) {
                    try {
                        outputStream.write(value.getData().toByteArray());
                        packageNumber[0]++;
                        System.out.println("Numer paczki " + packageNumber[0]);
                    } catch (IOException e) {
                        onError(e);
                    }
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                    responseObserver.onError(t);
                }

                @Override
                public void onCompleted() {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        onError(e);
                    }
                    ImageServiceProto.UploadStatus uploadStatus = ImageServiceProto.UploadStatus.newBuilder()
                            .setSuccess(true)
                            .build();
                    responseObserver.onNext(uploadStatus);
                    responseObserver.onCompleted();
                }
            };
        }

        public void downloadImage(ImageServiceProto.ImageRequest request, StreamObserver<ImageServiceProto.ImageChunk> responseObserver) {

            try {
                String fileName = "GrpcServer/src/main/resources/image/image2.jpg";
                FileInputStream input = new FileInputStream(fileName);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = input.read(buffer)) != -1) {
                    ImageServiceProto.ImageChunk chunk = ImageServiceProto.ImageChunk.newBuilder()
                            .setData(ByteString.copyFrom(buffer, 0, bytesRead))
                            .build();
                    responseObserver.onNext(chunk);
                }
                responseObserver.onCompleted();
                input.close();
                System.out.println("Przesłano plik z serwera");
            } catch (IOException e) {
                e.printStackTrace();
                responseObserver.onError(e);
            }
        }
    }
}

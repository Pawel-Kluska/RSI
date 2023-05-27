package com.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        Scanner scanner = new Scanner(System.in);
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            boolean isEnd = false;
            while (!isEnd) {
                System.out.println("Wpisz wiadomość: ");
                String message = scanner.nextLine();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println("Wysłano: '" + message + "'");
                System.out.println("Czy chcesz zakończyć? (t/n)");
                String end = scanner.nextLine();
                if (end.equals("t")) {
                    isEnd = true;
                }
            }
        }
    }
}

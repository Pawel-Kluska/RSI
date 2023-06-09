package com.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Client {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        MyData.info();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("pawel");
        factory.setPassword("pawel");
        factory.setHost("10.108.127.202");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Oczekiwanie na wiadomości...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Otrzymano wiadomość: '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}

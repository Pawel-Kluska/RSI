package com.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        MyData.info();
        Runnable sender1 = getSender1();
        Runnable sender2 = getSender2();
        Thread thread1 = new Thread(sender1);
        Thread thread2 = new Thread(sender2);

        thread1.start();
        thread2.start();
    }

    public static Runnable getSender1() {
        Runnable basic = () ->
        {
            Random r = new Random();
            long min = 1500;
            long max = 2500;
            long random = (long) (min + r.nextDouble() * (max - min));
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("pawel");
            factory.setPassword("pawel");
            factory.setHost("10.108.127.202");

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                for (int i = 0; i < 5; i++) {
                    String message = "Pawel " + i;
                    String fullMessage = Thread.currentThread().getName() + ": '" + message + "'";
                    channel.basicPublish("", QUEUE_NAME, null, fullMessage.getBytes(StandardCharsets.UTF_8));
                    Thread.sleep(random);

                }
            } catch (IOException | TimeoutException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        return basic;
    }

    public static Runnable getSender2() {
        Runnable basic = () ->
        {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("pawel");
            factory.setPassword("pawel");
            factory.setHost("10.108.127.202");

            Random r = new Random();
            long min = 2000;
            long max = 3500;
            long random = (long) (min + r.nextDouble() * (max - min));

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                for (int i = 0; i < 5; i++) {
                    String message = "Katya " + i;
                    String fullMessage = Thread.currentThread().getName() + ": '" + message + "'";
                    channel.basicPublish("", QUEUE_NAME, null, fullMessage.getBytes(StandardCharsets.UTF_8));
                    Thread.sleep(random);

                }
            } catch (IOException | TimeoutException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        return basic;
    }
}

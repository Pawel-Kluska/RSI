package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MyData {

    public static void info() {
        System.out.println("Paweł Kluska, 260391");
        System.out.println("Katya Zyatikava, 245891");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm:ss", new Locale("pl"));
        System.out.println(LocalDateTime.now().format(formatter));

        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.name"));

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            System.out.println(socket.getLocalAddress().getHostAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
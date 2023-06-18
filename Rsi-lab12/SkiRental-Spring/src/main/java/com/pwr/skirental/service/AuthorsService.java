package com.pwr.skirental.service;

import com.pwr.skirental.entity.Authors;
import com.pwr.skirental.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class AuthorsService {

    public Authors getAuthors() throws EntityNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM HH:mm:ss", new Locale("pl"));
        Authors authors;
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));

            authors = Authors.builder()
                    .author1("Paweł Kluska 260391")
                    .author2("Katya Zyatikava 245891")
                    .datetime(LocalDateTime.now().format(formatter))
                    .javaVersion(System.getProperty("java.version"))
                    .userName(System.getProperty("user.name"))
                    .osName(System.getProperty("os.name"))
                    .localAddress(socket.getLocalAddress().getHostAddress())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (authors == null) {
            throw new EntityNotFoundException("Authors not found");
        }
        return authors;
    }
}

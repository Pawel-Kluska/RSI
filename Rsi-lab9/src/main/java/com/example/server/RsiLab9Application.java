package com.example.server;

import com.example.MyData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RsiLab9Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RsiLab9Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MyData.info();
    }
}

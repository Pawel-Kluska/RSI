package org.example.jaxws.server;


import com.example.MyData;
import jakarta.xml.ws.Endpoint;

import java.io.IOException;

import static java.lang.System.exit;

public class ServiceHost {
    public static void main(String[] args) {
        MyData.info();
        System.out.println("Web Service org.example.jaxws.server.PersonService is running ...");
        PersonServiceImpl psi = new PersonServiceImpl();
        Endpoint.publish("http://localhost:8081/personservice", psi);
        System.out.println("Press ENTER to STOP org.example.jaxws.server.PersonService ...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit(0);
    }
}

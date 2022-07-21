package org.canban.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class TracingApp {
    public static void main(String[] args) {
        SpringApplication.run(TracingApp.class, args);
    }
}

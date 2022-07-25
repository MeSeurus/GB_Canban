package com.canban.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CanbanGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(CanbanGatewayApp.class, args);
    }
}
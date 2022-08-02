package com.canban.web.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
@EnableEurekaClient
public class SpringAnalyticsApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringAnalyticsApp.class, args);
    }

}
package com.canban.web.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCoreCanbanApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreCanbanApplication.class, args);
    }

}

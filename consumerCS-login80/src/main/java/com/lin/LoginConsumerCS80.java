package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginConsumerCS80 {
    public static void main(String[] args) {
        SpringApplication.run(LoginConsumerCS80.class,args);
    }
}

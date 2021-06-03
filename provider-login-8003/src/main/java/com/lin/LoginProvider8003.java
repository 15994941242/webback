package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoginProvider8003 {
    public static void main(String[] args) {
        SpringApplication.run(LoginProvider8003.class, args);
    }
}

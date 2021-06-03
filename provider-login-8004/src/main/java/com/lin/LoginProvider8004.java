package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoginProvider8004 {
    public static void main(String[] args) {
        SpringApplication.run(LoginProvider8004.class);
    }
}

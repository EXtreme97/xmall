package com.example.xmall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XmallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmallOrderApplication.class, args);
    }
}

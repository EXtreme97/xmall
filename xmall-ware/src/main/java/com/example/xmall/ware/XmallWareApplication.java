package com.example.xmall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XmallWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmallWareApplication.class, args);
    }
}

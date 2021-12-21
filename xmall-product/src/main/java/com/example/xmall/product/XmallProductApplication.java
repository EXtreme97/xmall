package com.example.xmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.example.xmall.product.dao")
@SpringBootApplication
public class XmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallProductApplication.class, args);
    }

}

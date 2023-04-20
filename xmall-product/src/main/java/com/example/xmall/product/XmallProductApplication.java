package com.example.xmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients(basePackages = "com.example.xmall.product.feign")
@EnableDiscoveryClient
@MapperScan("com.example.xmall.product.dao")
@SpringBootApplication
public class XmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallProductApplication.class, args);
    }

}

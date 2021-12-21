package com.example.xmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages ="com.example.xmall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class XmallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmallMemberApplication.class, args);
    }
}

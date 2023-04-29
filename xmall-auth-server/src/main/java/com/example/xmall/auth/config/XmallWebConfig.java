package com.example.xmall.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class XmallWebConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/log.html").setViewName("log");
        registry.addViewController("/reg.html").setViewName("reg");
    }
}

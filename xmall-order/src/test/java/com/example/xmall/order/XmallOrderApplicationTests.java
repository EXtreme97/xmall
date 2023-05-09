package com.example.xmall.order;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class XmallOrderApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    void contextLoads() {
    }

    @Test
    public void createExchange(){
        DirectExchange directExchange = new DirectExchange("hello-java-exchange",true,false);
        amqpAdmin.declareExchange(directExchange);
        log.info("exchange[{}]创建成功","hello-java-exchange");
    }
}

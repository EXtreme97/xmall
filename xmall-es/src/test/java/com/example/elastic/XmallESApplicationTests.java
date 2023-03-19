package com.example.elastic;

import com.alibaba.fastjson.JSON;
import com.example.elastic.config.XmallElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class XmallESApplicationTests {
    @Resource
    private RestHighLevelClient client;

    @Test
    public void contextLoads() {
        System.out.println(client);
    }

    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        User user = new User();
        user.setAge(18);
        user.setUsername("abc");
        user.setGender("female");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);

        IndexResponse index = client.index(indexRequest, XmallElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);
    }

    @Data
    class User {
        private String username;
        private String gender;
        private Integer age;
    }
}

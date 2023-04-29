package com.example.thirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.example.thirdparty.component.SmsComponent;
import com.example.thirdparty.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class XmallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;
    @Autowired
    SmsComponent smsComponent;

    @Test
    void contextLoads() {
    }

    @Test
    public void testUpload() throws FileNotFoundException {
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "LTAI5tFgs3DVq1DcPAUe3CAx";
//        String accessKeySecret = "ePyxIt9D4rPeYXTIDSKiqqciIfuHiV";
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        InputStream inputStream = new FileInputStream("D:\\Download\\tests.jpg");
        ossClient.putObject("xmall97", "1.jpg", inputStream);

        ossClient.shutdown();

        System.out.println("上传完成...");
    }


    @Test
    public void testSendSms() {
        smsComponent.testSendSms("18781039815", "123456");
    }

}

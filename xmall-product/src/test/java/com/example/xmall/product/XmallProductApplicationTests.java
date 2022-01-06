package com.example.xmall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.example.xmall.product.entity.BrandEntity;
import com.example.xmall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
class XmallProductApplicationTests {
    @Autowired
    BrandService brandService;
//    @Autowired
//    OSSClient ossClient;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
//         brandEntity.setDescript("");
        brandEntity.setName("华为");
//         brandService.save(brandEntity);
        System.out.println("保存成功");
    }

    @Test
    public void testUpload() throws FileNotFoundException {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5tFgs3DVq1DcPAUe3CAx";
        String accessKeySecret = "ePyxIt9D4rPeYXTIDSKiqqciIfuHiV";

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        InputStream inputStream = new FileInputStream("<yourlocalFIle>");
        ((OSS) ossClient).putObject("<yourBucketName>", "<yourObjectName>", inputStream);

        ossClient.shutdown();
    }

}

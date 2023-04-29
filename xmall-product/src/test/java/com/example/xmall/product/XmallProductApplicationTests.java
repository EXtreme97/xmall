package com.example.xmall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.example.xmall.product.dao.AttrGroupDao;
import com.example.xmall.product.dao.SkuSaleAttrValueDao;
import com.example.xmall.product.entity.BrandEntity;
import com.example.xmall.product.service.BrandService;
import com.example.xmall.product.service.CategoryService;
import com.example.xmall.product.vo.SkuItemSaleAttrVo;
import com.example.xmall.product.vo.SpuItemAttrGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class XmallProductApplicationTests {
    @Autowired
    BrandService brandService;
//    @Autowired
//    OSSClient ossClient;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    SkuSaleAttrValueDao skuSaleAttrValueDao;
    @Test
    public void test(){
//        List<SpuItemAttrGroupVo> spuId = attrGroupDao.getAttrGroupWithAttrsSpuId(13L, 225L);
//        System.out.println(spuId);
        List<SkuItemSaleAttrVo> saleAttrsBySpuId = skuSaleAttrValueDao.getSaleAttrsBySpuId(13L);
        System.out.println(saleAttrsBySpuId);

    }
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

    @Test
    public void testFindPath(){
        Long[] catelogPath= categoryService.findCatelogPath(225L);
        log.info("完整路径,{}", Arrays.asList(catelogPath));
    }

}

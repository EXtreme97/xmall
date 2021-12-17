package com.example.xmall.product;

import com.example.xmall.product.entity.BrandEntity;
import com.example.xmall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class XmallProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity=new BrandEntity();
//         brandEntity.setDescript("");
         brandEntity.setName("华为");
         brandService.save(brandEntity);
         System.out.println("保存成功");
    }

}

package com.example.xmall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemVo {
    private Long skuId;
    private String title;

    private String image;

    /**
     * 商品套餐属性
     */
    private List<String> skuAttr;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;
    private Boolean hasStock;
    /** 商品重量 **/
    private BigDecimal weight = new BigDecimal("0.085");
}

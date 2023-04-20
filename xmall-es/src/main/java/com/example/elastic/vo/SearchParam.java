package com.example.elastic.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchParam {
    private String keyword;//关键字
    private Long catelog3Id;//三级分类的id
    private String sort;//排序条件
    private Integer hasStock;//是否有货：1.有货，0.无货
    private String skuPrice;//价格区间
    private List<Long> brandId;//品牌，可多选
    private List<String> attrs;//按属性筛选
    private Integer pageNum;//页码
    private  String _queryString;
}

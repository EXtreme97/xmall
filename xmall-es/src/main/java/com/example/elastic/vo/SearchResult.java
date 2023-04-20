package com.example.elastic.vo;

import com.example.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    private List<SkuEsModel> product;
    private Integer pageNum;
    private Long total;
    private Integer totalPage;
    private List<Integer> pageNavs;

    private List<BrandVo> brands;
    private List<AttrVo> attrs;
    private List<CatelogVo> catelogs;
    /* 面包屑导航数据 */
    private List<NavVo> navs;

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrsValue;
    }

    @Data
    public static class CatelogVo {
        private Long catelogId;
        private String catelogName;
    }
}

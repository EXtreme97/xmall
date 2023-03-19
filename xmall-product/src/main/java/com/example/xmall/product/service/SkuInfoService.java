package com.example.xmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.utils.PageUtils;
import com.example.xmall.product.entity.SkuInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-13 19:57:25
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    List<SkuInfoEntity> getSkusBySpuId(Long spuId);
}


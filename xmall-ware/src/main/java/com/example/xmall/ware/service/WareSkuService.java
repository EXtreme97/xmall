package com.example.xmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.to.OrderTo;
import com.example.common.to.mq.StockLockedTo;
import com.example.common.utils.PageUtils;
import com.example.xmall.ware.entity.WareSkuEntity;
import com.example.xmall.ware.vo.LockStockResultVo;
import com.example.xmall.ware.vo.SkuHasStockVo;
import com.example.xmall.ware.vo.WareSkuLockVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 23:02:53
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);

    Boolean orderLockStock(WareSkuLockVo vo) throws InvocationTargetException, IllegalAccessException;

    void unlockStock(StockLockedTo to);

    void unlockStock(OrderTo to);
}


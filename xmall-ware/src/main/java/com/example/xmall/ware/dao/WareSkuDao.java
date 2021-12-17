package com.example.xmall.ware.dao;

import com.example.xmall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 23:02:53
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}

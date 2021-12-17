package com.example.xmall.coupon.dao;

import com.example.xmall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 22:17:04
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}

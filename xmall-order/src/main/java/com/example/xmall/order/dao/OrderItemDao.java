package com.example.xmall.order.dao;

import com.example.xmall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 22:54:17
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}

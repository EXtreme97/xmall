package com.example.xmall.product.dao;

import com.example.xmall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 *
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-13 19:57:25
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    List<Long> selectSearchAttrsIds(@Param("attrIds") List<Long> attrIds);
}


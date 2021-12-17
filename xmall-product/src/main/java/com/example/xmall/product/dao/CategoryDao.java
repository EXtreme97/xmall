package com.example.xmall.product.dao;

import com.example.xmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-13 19:57:25
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

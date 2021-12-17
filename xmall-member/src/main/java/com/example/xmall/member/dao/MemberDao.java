package com.example.xmall.member.dao;

import com.example.xmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 22:33:25
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}

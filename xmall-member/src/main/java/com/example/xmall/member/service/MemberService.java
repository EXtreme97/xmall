package com.example.xmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.utils.PageUtils;
import com.example.xmall.member.entity.MemberEntity;
import com.example.xmall.member.exception.PhoneException;
import com.example.xmall.member.exception.UsernameException;
import com.example.xmall.member.vo.MemberLoginVo;
import com.example.xmall.member.vo.MemberRegisterVo;
import com.example.xmall.member.vo.SocialUser;

import java.util.Map;

/**
 * 会员
 *
 * @author xzy
 * @email xuzhenyuan30@gmail.com
 * @date 2021-12-16 22:33:25
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void regist(MemberRegisterVo vo);

    void checkPhoneUnique(String phone) throws PhoneException;

    void checkUserNameUnique(String userName) throws UsernameException;

    MemberEntity login(MemberLoginVo vo);

    MemberEntity login(SocialUser socialUser) throws Exception;
}


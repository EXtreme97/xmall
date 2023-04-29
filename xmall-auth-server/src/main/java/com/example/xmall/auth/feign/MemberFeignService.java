package com.example.xmall.auth.feign;

import com.example.common.utils.R;
import com.example.xmall.auth.vo.SocialUser;
import com.example.xmall.auth.vo.UserLoginVo;
import com.example.xmall.auth.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("xmall-member")
public interface MemberFeignService {
    @PostMapping(value = "/member/member/regist")
    R register(@RequestBody UserRegisterVo vo);

    @PostMapping(value = "/member/member/login")
    R login(@RequestBody UserLoginVo vo);

    @PostMapping(value = "/member/member/oauth2/login")
    R oauthLogin(@RequestBody SocialUser socialUser) throws Exception;
}

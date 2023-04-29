package com.example.xmall.auth.feign;

import com.example.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("xmall-third-party")
public interface ThirdPartyFeignService {
    @GetMapping(value = "/sms/sendCode")
    R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}

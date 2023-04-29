package com.example.xmall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.common.constant.AuthServerConstant;
import com.example.common.utils.HttpUtils;
import com.example.common.utils.R;
import com.example.xmall.auth.feign.MemberFeignService;
import com.example.common.vo.MemberRespVo;
import com.example.xmall.auth.vo.SocialUser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OAuth2Controller {
    @Autowired
    MemberFeignService memberFeignService;

    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "1645600379");
        map.put("client_secret", "a1cdae5affc50eb0f9160b832e7e7796");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://192.168.56.1:20000/oauth2.0/weibo/success");
        map.put("code", code);

        //1、根据用户授权返回的code换取access_token
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", new HashMap<>(), map, new HashMap<>());
        if (response.getStatusLine().getStatusCode() == 200) {
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);
            R oauthLogin = memberFeignService.oauthLogin(socialUser);
            if (oauthLogin.getCode() == 0) {
                MemberRespVo data = oauthLogin.getData("data", new TypeReference<MemberRespVo>() {
                });
                System.out.println("登录成功：用户信息：{}" + data.toString());

                //1、第一次使用session，命令浏览器保存卡号，JSESSIONID这个cookie
                //以后浏览器访问哪个网站就会带上这个网站的cookie
                //TODO 1、默认发的令牌。当前域（解决子域session共享问题）
                //TODO 2、使用JSON的序列化方式来序列化对象到Redis中
                session.setAttribute(AuthServerConstant.LOGIN_USER, data);

                //2、登录成功跳回首页
                return "redirect:http://192.168.56.1:11000/";
            }
        } else {
            return "redirect:http://192.168.56.1:20000/log.html";
        }
        return "redirect:http://192.168.56.1:11000/";
    }
}

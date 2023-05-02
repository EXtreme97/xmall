package com.example.xmall.auth.controller;

import com.alibaba.fastjson.TypeReference;
import com.example.common.constant.AuthServerConstant;
import com.example.common.exception.BizCodeEnum;
import com.example.common.utils.R;
import com.example.common.vo.MemberRespVo;
import com.example.xmall.auth.feign.MemberFeignService;
import com.example.xmall.auth.feign.ThirdPartyFeignService;
import com.example.xmall.auth.vo.UserLoginVo;
import com.example.xmall.auth.vo.UserRegisterVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Controller
public class LoginController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ThirdPartyFeignService thirdPartyFeignService;
    @Autowired
    MemberFeignService memberFeignService;


    @ResponseBody
    @GetMapping("/sms/sendCode")
    public R sendCode(@RequestParam("phone") String phone) {
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (redisCode != null) {
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 5);
        String substring = code + "_" + System.currentTimeMillis();
        redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone, substring, 10, TimeUnit.MINUTES);
        thirdPartyFeignService.sendCode(phone, code);
        return R.ok();
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterVo vo, BindingResult result, Model model,
                           RedirectAttributes attributes) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            System.out.println(errors.toString());

            attributes.addFlashAttribute("errors", errors);
            return "redirect://http://192.168.56.1:20000/reg.html";
        }//1、效验验证码
        String code = vo.getCode();

        //获取存入Redis里的验证码
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(redisCode)) {
            //截取字符串
            if (code.equals(redisCode.split("_")[0])) {
                //删除验证码;令牌机制
                redisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                //验证码通过，真正注册，调用远程服务进行注册
                R register = memberFeignService.register(vo);
                if (register.getCode() == 0) {
                    //成功
                    return "redirect:http://192.168.56.1:20000/log.html";
                } else {
                    //失败
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", register.getData("msg", new TypeReference<String>() {
                    }));
                    attributes.addFlashAttribute("errors", errors);
                    return "redirect:http://192.168.56.1:20000/reg.html";
                }


            } else {
                //效验出错回到注册页面
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                attributes.addFlashAttribute("errors", errors);
                return "redirect:http://192.168.56.1:20000/reg.html";
            }
        } else {
            //效验出错回到注册页面
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码错误");
            attributes.addFlashAttribute("errors", errors);
            return "redirect:http://192.168.56.1:20000/reg.html";
        }
    }

    @GetMapping(value = "/log.html")
    public String loginPage(HttpSession session) {

        //从session先取出来用户的信息，判断用户是否已经登录过了
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        //如果用户没登录那就跳转到登录页面
        if (attribute == null) {
            return "log";
        } else {
            return "redirect:http://192.168.56.1:11000/";
        }

    }
    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes attributes, HttpSession session) {
        R login = memberFeignService.login(vo);
        System.out.println(login);
        if (login.getCode() == 0) {
            MemberRespVo data = login.getData("data", new TypeReference<MemberRespVo>() {
            });
            session.setAttribute(AuthServerConstant.LOGIN_USER,data);
            return "redirect:http://192.168.56.1:11000/";
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", login.getData("msg", new TypeReference<String>() {
            }));
            attributes.addFlashAttribute("errors", errors);
            return "redirect:http://192.168.56.1:20000/log.html";
        }
    }
}

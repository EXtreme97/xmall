package com.example.xmallcart.interceptor;

import com.example.common.constant.AuthServerConstant;
import com.example.common.constant.CartConstant;
import com.example.common.vo.MemberRespVo;
import com.example.xmallcart.to.UserInforTo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


@Component
public class CartInterceptor implements HandlerInterceptor {
    public static ThreadLocal<UserInforTo> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        UserInforTo userInforTo = new UserInforTo();
        HttpSession session = request.getSession();
        MemberRespVo member = (MemberRespVo) session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (member != null) {
            userInforTo.setUserId(member.getId());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //user-key
                String name = cookie.getName();
                if (name.equals(CartConstant.TEMP_USER_COOKIE_NAME)) {
                    userInforTo.setUserKey(cookie.getValue());
                    //标记为已是临时用户
                    userInforTo.setTempUser(true);
                }
            }
        }
        if (StringUtils.isEmpty(userInforTo.getUserKey())) {
            String s = UUID.randomUUID().toString();
            userInforTo.setUserKey(s);
        }
        threadLocal.set(userInforTo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInforTo userInforTo = threadLocal.get();
        if (!userInforTo.getTempUser()) {
            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, userInforTo.getUserKey());
            cookie.setDomain("192.168.56.1");
            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);
            response.addCookie(cookie);
        }
    }
}

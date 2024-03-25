package com.xxz.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xxz.Pojo.Result;
import com.xxz.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//返回为false将不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("prehandle");

        //1.获取请求的url
        String url = req.getRequestURI().toString();
        log.info("请求的url{}",url);

        //2.判断请求url中是否包含login，如果包含，则说明是登陆操作，放行
        if(url.contains("login")){
            return true;
        }

        //3.获取请求头中的令牌
        String jwt=req.getHeader("token");

        //4.判断令牌是否存在 如果不存在 则返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登陆的信息");
            Result error=Result.error("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //5.判断令牌是否正确，如果不正确，则返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("令牌解析失败，返回未登陆的错误信息");
            Result error=Result.error("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //6.放行
        log.info("令牌合法 放行" );
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("aftercompletion");
    }



}

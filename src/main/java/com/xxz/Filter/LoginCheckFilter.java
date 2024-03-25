package com.xxz.Filter;

import com.alibaba.fastjson.JSONObject;
import com.xxz.Pojo.Result;
import com.xxz.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;

        //1.获取请求的url
        String url = req.getRequestURI().toString();
        log.info("请求的url{}",url);

        //2.判断请求url中是否包含login，如果包含，则说明是登陆操作，放行
        if(url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3.获取请求头中的令牌
        String jwt=req.getHeader("token");

        //4.判断令牌是否存在 如果不存在 则返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登陆的信息");
            Result error=Result.error("NOT_LOGIN");
            String notLogin= JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return ;
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
            return ;
        }

        //6.放行
        log.info("令牌合法 放行" );
        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }
}

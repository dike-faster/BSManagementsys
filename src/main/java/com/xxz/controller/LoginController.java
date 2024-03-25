package com.xxz.controller;

import com.xxz.Pojo.Emp;
import com.xxz.Pojo.Result;
import com.xxz.service.EmpService;
import com.xxz.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    EmpService empService;
    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp){
        log.info("用户{}请求登录",emp);
        Emp e=empService.login(emp);

        //登陆成功，生成令牌、下发令牌
        if(e!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());

            String jwt= JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        //登陆失败，返回错误信息

        return Result.error("用户名或密码错误");
    }
}

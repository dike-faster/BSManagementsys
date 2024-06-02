package com.xxz.controller;

import com.xxz.Pojo.Emp;
import com.xxz.Pojo.PageBean;
import com.xxz.Pojo.Result;
import com.xxz.anno.Log;
import com.xxz.service.DeptService;
import com.xxz.service.EmpService;
import com.xxz.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result showEmp(@Param("name")String name, @Param("gender") Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end, @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10")  Integer pageSize){
        log.info("分页查询 页数:{} 页面大小{} 参数{} {} {}",page,pageSize,name,gender,begin);
        return Result.success(empService.page(name,gender,begin,end,page,pageSize));
    }

    //实现批量删除
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteEmp(@PathVariable("ids") ArrayList<Integer> ids){
        log.info("批量删除{}",ids);
        empService.deleteEmp(ids);
        return Result.success();
    }

    //实现添加员工 POST请求 json格式
    @PostMapping
    @Log
    public Result addEmp(@RequestBody Emp emp){
        log.info("添加员工{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }
    //实现对于id进行查询数据的操作
    @GetMapping("/{id}")
    public Result selectEmp(@PathVariable Integer id){
        log.info("查询员工id为{}",id);
        return Result.success(empService.selectEmp(id));
    }

    @PutMapping
    @Log
    public Result updateEmp(@RequestBody Emp emp){
        log.info("修改员工{}",emp);
        empService.updateEmp(emp);
        return Result.success();
    }
}

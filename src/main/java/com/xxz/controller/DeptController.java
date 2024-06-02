package com.xxz.controller;

import com.xxz.Pojo.Dept;
import com.xxz.Pojo.Result;
import com.xxz.anno.Log;
import com.xxz.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
//相当于private static Logger log=LoggerFactory.getLogger(DeptController.class)
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;
//    @RequestMapping(value="/depts",method= RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
    //查询全部数据
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }
    //删除员工数据
    @Log
    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable("id")Integer deptId){
        log.info("删除部门数据");
        deptService.deleteDept(deptId);
        return Result.success();
    }
    //增加部门数据
    @Log
    @PostMapping
    public Result deleteDept(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptService.insertDept(dept);
        return Result.success();
    }



}

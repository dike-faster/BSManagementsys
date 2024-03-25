package com.xxz.service;

import com.xxz.Pojo.Emp;
import com.xxz.Pojo.PageBean;
import com.xxz.Pojo.Result;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 员工管理
 */
public interface EmpService {
//    PageBean showEmps(Integer page, Integer pageSize);
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    //批量删除员工
    void deleteEmp(ArrayList<Integer> ids);

    //添加员工操作
    void addEmp(Emp emp);

    //显示员工信息
    Emp selectEmp(Integer id);

    //更新修改员工信息
    void updateEmp(Emp emp);


    //处理用户登录信息
    Emp login(Emp emp);
}

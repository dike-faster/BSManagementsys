package com.xxz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xxz.Pojo.Emp;
import com.xxz.Pojo.PageBean;
import com.xxz.Pojo.Result;
import com.xxz.controller.EmpController;
import com.xxz.mapper.EmpMapper;
import com.xxz.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    //返回展示的页面
//    @Override
//    public PageBean showEmps(Integer page, Integer pageSize) {
//        PageBean pageBean=new PageBean();
//        pageBean.setTotal(empMapper.countTotal());
//        pageBean.setRows(empMapper.pageList((page-1)*pageSize,pageSize));
//        return pageBean;
//    }

    //使用插件完成分页查询
    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize){
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> empList = empMapper.list(name,gender, begin,end);
        Page<Emp> p =(Page<Emp>)empList;

        //3.封装PageBean
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteEmp(ArrayList<Integer> ids) {
        //适用迭代器获取集合中每一个元素
        Iterator iterator=ids.iterator();
        //对每个元素依次删除
        while(iterator.hasNext()){
            Object it=iterator.next();
            empMapper.deleteEmp((Integer) it);
        }
    }

    @Override
    public void addEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
    }

    @Override
    public Emp selectEmp(Integer id) {
        return empMapper.selectEmp(id);
    }

    //进行员工信息更新操作
    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);
    }

    //进行用户的登录操作
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }


}

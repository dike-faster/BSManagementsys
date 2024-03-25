package com.xxz.service.impl;

import com.xxz.Pojo.Dept;
import com.xxz.mapper.DeptMapper;
import com.xxz.mapper.EmpMapper;
import com.xxz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service

public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //当前方法已经交给spring进行事务管理
    @Override
    public void deleteDept(Integer deptId) {
//        try{
            deptMapper.deletedeptId(deptId);
            empMapper.deleteByDeptId(deptId);
//        }finally {
//            DeptLog deptLog
//        }
//
//        empMapper.deleteByDeptId(deptId);
    }

    @Override
    public void insertDept(Dept dept) {
        deptMapper.insertDept(dept);
    }
}

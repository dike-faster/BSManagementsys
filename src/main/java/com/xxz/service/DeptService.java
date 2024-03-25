package com.xxz.service;

import com.xxz.Pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
//    查询全部数据
    List<Dept> list();

    void deleteDept(Integer deptId);

    void insertDept(Dept dept);
}

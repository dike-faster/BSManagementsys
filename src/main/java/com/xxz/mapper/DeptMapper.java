package com.xxz.mapper;

import com.xxz.Pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理
 */

@Mapper
public interface DeptMapper {

    //编写对于部门的查询更新等操作

    @Select("select* from dept")
    List<Dept> list();

    @Delete("delete from dept where id=#{deptId}")
    void deletedeptId(Number deptId);

//    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insertDept(Dept dept);

}

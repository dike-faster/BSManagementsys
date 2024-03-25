package com.xxz.mapper;

import com.xxz.Pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    //统计员工的总人数
//    @Select("select count(*) from emp")
//    Long countTotal();

    //根据索引 返回对应的页面列表 分页查询
//    @Select("select* from emp order by id limit #{page},#{pageSize}")
//    List pageList(@Param("page") int page,@Param("pageSize") int pageSize);
    //使用插件完成分页查询

    public List<Emp> list(@Param("name")String name, @Param("gender")Short gender,
                          @Param("begin")LocalDate begin,@Param("end") LocalDate end);

    //删除给定的id序列
    @Delete("delete from emp where id=#{id}")
    void deleteEmp(Integer id);

    void addEmp(Emp emp );

    @Select("select* from emp where id=#{id}")
    Emp selectEmp(Integer id);


    void updateEmp(Emp emp);

    @Select("select* from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    //根据部门id删除员工
    @Delete("delete from emp where dept_id=#{dept_id}")
    void deleteByDeptId(Integer dept_id);
}

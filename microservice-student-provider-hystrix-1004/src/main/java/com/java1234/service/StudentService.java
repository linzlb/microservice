package com.java1234.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.java1234.entity.Student;

/**
 * 学生信息Service接口
 * @author Administrator
 */
public interface StudentService {

    /**
     * 添加或者修改学生信息
     * @param student
     */
    void save(Student student);

    /**
     * 根据id查找学生信息
     * @param id
     * @return
     */
    Optional<Student> findById(Integer id);

    /**
     * 查询学生信息
     * @return
     */
    List<Student> list();

    /**
     * 根据id删除学生信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取信息
     * @return
     */
    Map<String,Object> getInfo();
}
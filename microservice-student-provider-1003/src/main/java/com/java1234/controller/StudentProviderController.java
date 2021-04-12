package com.java1234.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java1234.entity.Student;
import com.java1234.service.StudentService;

/**
 * 服务提供者-学生信息控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/student")
public class StudentProviderController {

    @Resource
    private StudentService studentService;

    /**
     * 添加或者修改学生信息
     * @param student
     * @return
     */
    @PostMapping(value="/save")
    public boolean save(Student student){
        try{
            studentService.save(student);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 查询学生信息
     * http://localhost:1001/student/list
     * @return
     */
    @GetMapping(value="/list")
    public List<Student> list(){
        System.out.print("=========1003=============");
        return studentService.list();
    }

    /**
     * 根据id查询学生信息
     * @return
     * http://localhost:1001/student/get/1
     */
    @GetMapping(value="/get/{id}")
    public Optional<Student> get(@PathVariable("id") Integer id){
        return studentService.findById(id);
    }

    /**
     * 根据id删除学生信息
     * @return
     * http://localhost:1001/student/delete/3
     */
    @GetMapping(value="/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        try{
            studentService.delete(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}

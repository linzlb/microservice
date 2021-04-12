package com.java1234.service;
 
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.java1234.entity.Student;
 
/**
 * Student Feign接口客户端
 * @author Administrator
 *
 */
@FeignClient(value="MICROSERVICE-STUDENT")
public interface StudentClientService {
 
    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    @GetMapping(value="/student/get/{id}")
    Student get(@PathVariable("id") Integer id);
     
    /**
     * 查询学生信息
     * @return
     */
    @GetMapping(value="/student/list")
    List<Student> list();
     
    /**
     * 添加或者修改学生信息
     * @param student
     * @return
     */
    @PostMapping(value="/student/save")
    boolean save(Student student);
     
    /**
     * 根据id删除学生信息
     * @return
     */
    @GetMapping(value="/student/delete/{id}")
    boolean delete(@PathVariable("id") Integer id);
}
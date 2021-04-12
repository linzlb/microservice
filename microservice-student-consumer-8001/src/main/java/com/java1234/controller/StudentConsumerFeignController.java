package com.java1234.controller;
 
import java.util.List;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.java1234.entity.Student;
import com.java1234.service.StudentClientService;
 
/**
 * 服务消费者-学生信息控制器
 * @author Administrator
 *
 * 使用feign
 */
@RestController
@RequestMapping("/student")
public class StudentConsumerFeignController {
 
    @Autowired
    private StudentClientService studentClientService;
     
    /**
     * 添加或者修改学生信息
     * @param student
     * @return
     */
    @PostMapping(value="/save")
    public boolean save(Student student){
        return studentClientService.save(student);
    }
      
    /**
     * 查询学生信息
     * @return
     */
    @GetMapping(value="/list")
    public List<Student> list(){
        System.out.println("=============8001=============");
        return studentClientService.list();
    }
      
    /**
     * 根据id查询学生信息
     * @return
     */
    @GetMapping(value="/get/{id}")
    public Student get(@PathVariable("id") Integer id){
        return studentClientService.get(id);
    }
      
    /**
     * 根据id删除学生信息
     * @return
     */
    @GetMapping(value="/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        return studentClientService.delete(id);
    }
     
}
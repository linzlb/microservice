package com.java1234.controller;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
 
import com.java1234.entity.Student;
 
/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/student")
public class StudentConsumerController {
 
     @Resource
     private RestTemplate restTemplate;


    private static String PRE_HOST = "http://MICROSERVICE-STUDENT";

    /**
    * 根据id删除学生信息
    * @return
    */
    @SuppressWarnings("unchecked")
    @GetMapping(value="/getInfo")
    @ResponseBody
    public Map<String,Object> getInfo(){
        return restTemplate.getForObject(PRE_HOST+"/student/getInfo/", Map.class);
    }

     /**
      * 添加或者修改学生信息
      * @param student
      * @return
      * 服务提供端实体接收不到数据，因为是http rest方式交互，这里要求必须加上 @RequestBody
      */
     @PostMapping(value="/save")
     private boolean save(@RequestBody Student student){
         return restTemplate.postForObject("http://localhost:1001/student/save", student, Boolean.class);
     }
      
     /**
     * 查询学生信息
     * http://localhost/student/list
     * @return
     */
    @SuppressWarnings("unchecked")
    @GetMapping(value="/list")
    public List<Student> list(){
        return restTemplate.getForObject("http://localhost:1001/student/list", List.class);
    }
     
    /**
     * 根据id查询学生信息
     * @return
     * http://localhost/student/get/1
     */
    @GetMapping(value="/get/{id}")
    public Student get(@PathVariable("id") Integer id){
        return restTemplate.getForObject("http://localhost:1001/student/get/"+id, Student.class);
    }
     
    /**
     * 根据id删除学生信息
     * @return
     */
    @GetMapping(value="/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        try{
            restTemplate.getForObject("http://localhost:1001/student/delete/"+id, Boolean.class);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
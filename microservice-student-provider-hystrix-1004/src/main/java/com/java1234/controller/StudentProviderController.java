package com.java1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;
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

    //testHystrix
    /**
     * 获取信息
     * Thread.sleep(2000) 模拟超时；
     这里的话 我们加上@HystrixCommand注解 以及 fallbackMethod
     表明这个方法我们再 没有异常以及没有超时(hystrix默认1秒算超时)的情况，才返回正常的业务数据；
     否则，进入我们fallback指定的本地方法，我们搞的是500  系统出错，稍后重试，有效的解决雪崩效应，以及返回给用户界面
     很好的报错提示信息；
     * @return
     * @throws InterruptedException
     */
    //用@HystrixCommand fallbackMethod是很不好的，因为和业务代码耦合度太高，不利于维护
    //直接耦合代码的方式，相当于直接在服务端做，但是我们一般场景管不到客户端，所以用的比较多
    @ResponseBody
    @GetMapping(value="/getInfo")
    @HystrixCommand(fallbackMethod="getInfoFallback")
    public Map<String,Object> getInfo() throws InterruptedException{
        Thread.sleep(2000);//设置超时时间超过系统的超时时间，模拟fallback
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", 200);
        map.put("info", "业务数据xxxxx");
        return map;
    }

    public Map<String,Object> getInfoFallback() throws InterruptedException{
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", 500);
        map.put("info", "Hystrix的fallbackMethod信息：系统出错，稍后重试");
        return map;
    }

    /**
     * 获取信息  整合feign的方式
     * 具体看
     * Feign Hystrix整合&服务熔断服务降级彻底解耦 http://blog.java1234.com/blog/articles/439.html
     * 和 Feign Hystrix整合之超时时间配置 http://blog.java1234.com/blog/articles/440.html
     * 这个用的比较少，因为我们一般管不到客户端去
     * @return
     * @throws InterruptedException
     */
//    @ResponseBody
//    @GetMapping(value="/getInfo")
//    public Map<String,Object> getInfo() throws InterruptedException{
//        Thread.sleep(2000);
//        return studentService.getInfo();
//    }




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
        System.out.print("=========1004=============");
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

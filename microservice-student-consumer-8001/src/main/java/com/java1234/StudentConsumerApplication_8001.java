package com.java1234;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//这里的话 加了特殊配置 排除了 数据源注入，不加的话 会报错，老版本没有这个问题；
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
public class StudentConsumerApplication_8001 {
 
    public static void main(String[] args) {
        SpringApplication.run(StudentConsumerApplication_8001.class,args);
    }
}
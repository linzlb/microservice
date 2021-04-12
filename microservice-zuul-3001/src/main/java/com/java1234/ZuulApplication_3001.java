package com.java1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * zuul高级篇
 * http://www.ityouknow.com/springcloud/2018/01/20/spring-cloud-zuul.html
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApplication_3001 {

    //这个还没好，没有看到效果
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication_3001.class, args);
    }
     
}
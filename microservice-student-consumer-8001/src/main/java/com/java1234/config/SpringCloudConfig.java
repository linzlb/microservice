package com.java1234.config;
 
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
 
/**
 * SpringCloud相关配置
 * 主要是定义一个bean RestTemplate对象； springcloud消费者，服务提供者之间的交互是http rest方式
 * @author Administrator
 *
 */
@Configuration
public class SpringCloudConfig {
 
    /**
     * 调用服务模版
     * @return
     */
    @Bean
    @LoadBalanced  // 引入ribbon负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
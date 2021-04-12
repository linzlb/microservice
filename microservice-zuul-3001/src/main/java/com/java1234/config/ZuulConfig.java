package com.java1234.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import com.java1234.filter.AccessFilter;
 
/**
 * Zuul配置
 * @author Administrator
 *
 */
@Configuration
public class ZuulConfig {

    /**
     * 加入到请求拦截队列
     * @return
     */
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
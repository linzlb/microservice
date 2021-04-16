package com.linzlb;

import com.linzlb.filter.RequestTimeFilter;
import com.linzlb.filter.RequestTimeGatewayFilterFactory;
import com.linzlb.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 网关作为一个系统的流量的入口，有着举足轻重的作用，通常的作用如下：
 协议转换，路由转发
 流量聚合，对流量进行监控，日志输出
 作为整个系统的前端工程，对流量进行控制，有限流的作用
 作为系统的前端边界，外部流量只能通过网关才能访问系统
 可以在网关层做权限的判断
 可以在网关层做缓存

 Spring Cloud Gateway 的 Filter 的生命周期不像 Zuul 的那么丰富，它只有两个：“pre” 和 “post”。
 PRE： 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
 POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的 HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
 Spring Cloud Gateway 的 Filter 分为两种：GatewayFilter 与 GlobalFilter。GlobalFilter 会应用到所有的路由上，而 GatewayFilter 将应用到单个路由或者一个分组的路由上。

 */
@SpringBootApplication
@RestController
public class GateWayApplication_3333 {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication_3333.class, args);
    }

    // http://localhost:3333/get
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
       return builder.routes()
        .route(p -> p
            .path("/get")
            .filters(f -> f.addRequestHeader("Hello", "World"))
            .uri("http://httpbin.org:80"))
        .build();
    }


    // 自定义过滤器的使用
    // http://localhost:3333/customer/123
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        // @formatter:off
//        return builder.routes()
//                .route(r -> r.path("/customer/**")
//                        .filters(f -> f.filter(new RequestTimeFilter())
//                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
//                        .uri("http://httpbin.org:80/get")
//                        .order(0)
//                        .id("customer_filter_router")
//                )
//                .build();
//        // @formatter:on
//    }

    //改为用自定义过滤器工厂实现
    @Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }
    //全局过滤器 http://localhost:3333/customer/123
//    @Bean
//    public TokenFilter tokenFilter(){
//        return new TokenFilter();
//    }

}

package cn.simple.eurekaclient.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Simple
 * @Date 2023/3/22
 */
@Configuration
public class RouteConfig {

    /**
     * Java Bean方式的路由
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customerRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("route01",r->r.path("/guochuang").uri("https://www.bilibili.com/"))
                .route("route02",r->r.path("/doLogin").uri("https://localhost"))
                .build();

    }
}

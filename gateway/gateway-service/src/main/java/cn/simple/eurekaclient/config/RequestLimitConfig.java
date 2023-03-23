package cn.simple.eurekaclient.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author Simple
 * @Date 2023/3/23
 */
@Configuration
public class RequestLimitConfig {


    //针对某个接口ip来源限流
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getHost().getHostString());
    }



    //针对这个路径来限流 /doLogin
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

}

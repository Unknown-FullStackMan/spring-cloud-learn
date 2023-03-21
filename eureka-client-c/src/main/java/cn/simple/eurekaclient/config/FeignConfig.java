package cn.simple.eurekaclient.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@Configuration
public class FeignConfig {


    /**
     * 开启feign日志打印
     * @return
     */
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}

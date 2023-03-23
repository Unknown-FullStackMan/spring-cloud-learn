package cn.simple.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Simple
 * @Date 2023/3/22
 */
@SpringBootApplication
@EnableEurekaClient
public class LoginServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class,args);
    }
}

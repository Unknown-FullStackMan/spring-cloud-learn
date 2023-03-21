package cn.simple.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Simple
 * @Date 2023/3/15
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplicationB {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplicationB.class,args);
    }
}

package cn.simple.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Simple
 * @Date 2023/3/15
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication3 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication3.class,args);
    }
}

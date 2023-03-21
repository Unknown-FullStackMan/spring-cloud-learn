package com.simple.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@SpringBootApplication
@EnableEurekaClient
public class RentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentApplication.class,args);
    }
}

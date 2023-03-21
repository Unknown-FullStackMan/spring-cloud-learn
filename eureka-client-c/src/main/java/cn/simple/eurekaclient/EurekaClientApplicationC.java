package cn.simple.eurekaclient;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Simple
 * @Date 2023/3/15
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //开启feign的客客户端功能
public class EurekaClientApplicationC {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplicationC.class,args);
    }

    @Bean
    //ribbon注解 , 默认的负载均衡算法是轮询算法 底层是CAS算法，取模%
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 自定义负载均衡算法，可以使用内置的7个方法
     * 或者自己implement IRule接口
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}

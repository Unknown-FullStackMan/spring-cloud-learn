package cn.simple.eurekaclient.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Simple
 * @Date 2023/3/15
 */
@RestController
public class DiscoveryController {

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/test")
    public String get(String serviceName) {

        //服务发现  通过服务的应用名称找到服务的具体信息
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

        instances.forEach(System.out::println);
        return instances.get(0).toString();
    }
}

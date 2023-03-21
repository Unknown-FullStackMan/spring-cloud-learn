package com.simple;

import cn.simple.eurekaclient.feign.ProviderOrderFeign;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@SpringBootTest
public class TestFeign {

    @Resource
    @LoadBalanced
    private RestTemplate restTemplate;


    /**
     * 手写Feign
     */
    @Test
    void testWriteFeign() {
        ProviderOrderFeign myFeign = (ProviderOrderFeign) Proxy.newProxyInstance(TestFeign.class.getClassLoader(), new Class[]{ProviderOrderFeign.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * 获取方法名
                 */
                String name = method.getName();

                /**
                 * 获取请求方式
                 */
                GetMapping annotation = method.getAnnotation(GetMapping.class);
                /**
                 * 获取请求路径
                 */
                String[] paths = annotation.value();
                String path = paths[0];

                /**
                 * 获取请求服务名
                 */
                Class<?> classZ = method.getDeclaringClass();
                String className = classZ.getName();
                String serviceName = classZ.getAnnotation(FeignClient.class).value();


                String requestUrl = "http://" + serviceName  + path;

                return restTemplate.getForObject(requestUrl,String.class);
            }
        });

        System.out.println(myFeign.getOrder());
    }
}

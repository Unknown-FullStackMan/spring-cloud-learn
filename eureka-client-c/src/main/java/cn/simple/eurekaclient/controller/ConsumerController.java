package cn.simple.eurekaclient.controller;

import cn.simple.eurekaclient.feign.ProviderOrderFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author Simple
 * @Date 2023/3/16
 */
@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ProviderOrderFeign providerOrderFeign;

    /**
     * ribbon 是将 http://provider/hellp 路径请求成功
     * http://127.0.0.1:8080/hello
     * 1.拦截这个请求
     * 2.截取主机名称
     * 3.借助eureka来做服务发现list<>
     * 4.通过负载均衡算法 拿到服务的ip和port
     * 5reConstruct URL
     * 6.发起请求
     * @param serviceName
     * @return
     */

    @GetMapping("/testRibbon")
    public String testRibbon(String serviceName) {
        String result = restTemplate.getForObject("http://"+serviceName+"/hello",String.class);
        return result;
    }


    /**
     * feign默认的等待时间是1S
     * feign封装了ribbon 修改feign的等待时间需要去修改ribbon的配置
     * ribbon:
     *   ReadTimeout: 3000 #响应等待超时时间
     *   ConnectTimeout: 3000 #链接服务接口的超时时间
     * @return
     */
    @RequestMapping("/testOpenFeign")
    public String testOpenFeign() {
        String result = providerOrderFeign.getOrder();
        return result;
    }


}

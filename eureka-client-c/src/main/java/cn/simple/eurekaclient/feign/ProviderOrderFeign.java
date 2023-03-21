package cn.simple.eurekaclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Simple
 * @Date 2023/3/20
 * @FeignClient value值是提供者的服务名称
 */
@FeignClient(value = "provider")
public interface ProviderOrderFeign {

    @RequestMapping("/order")
    public String getOrder();

    @RequestMapping("/order/v1")
    public String getOrder(@RequestParam String name);

}

package com.simple.coustom.fegin;

import com.simple.coustom.fegin.hystrix.RentFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 指定熔断类
 */
@FeignClient(value = "rent-car-service",fallback = RentFeignHystrix.class)
public interface RentFeign {

    @RequestMapping("/rent")
    public String rent();
}

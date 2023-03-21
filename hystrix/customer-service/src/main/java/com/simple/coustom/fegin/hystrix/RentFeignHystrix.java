package com.simple.coustom.fegin.hystrix;

import com.simple.coustom.fegin.RentFeign;
import org.springframework.stereotype.Component;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@Component
public class RentFeignHystrix implements RentFeign {
    @Override
    public String rent() {
        return "Alternatives plan";
    }
}

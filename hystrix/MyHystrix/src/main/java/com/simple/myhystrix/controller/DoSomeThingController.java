package com.simple.myhystrix.controller;

import com.simple.myhystrix.annotion.MyHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@RestController
public class DoSomeThingController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("doRpc")
    @MyHystrix
    public String doRpc() {

        String result = restTemplate.getForObject("http://localhost:8989/abc",String.class);

        return result;
    }
}

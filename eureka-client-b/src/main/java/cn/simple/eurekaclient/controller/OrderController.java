package cn.simple.eurekaclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@RestController
public class OrderController {


    @RequestMapping("/order")
    public String getOrder() {
        return "get order:01 ";
    }


    @RequestMapping("/order/v1")
    public String getOrder(@RequestParam String name) {
        return "get order:01 " + name;
    }
}

package cn.simple.eurekaclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Simple
 * @Date 2023/3/16
 */
@RestController
public class ProviderController {

    @GetMapping("/hello")
    public String hello(){
        return "Im provider a";
    }
}

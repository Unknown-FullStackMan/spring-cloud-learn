package com.simple.rent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@RestController
public class RentCarController {

    @RequestMapping("/rent")
    public String rent() {
        return "rent succeed";
    }
}

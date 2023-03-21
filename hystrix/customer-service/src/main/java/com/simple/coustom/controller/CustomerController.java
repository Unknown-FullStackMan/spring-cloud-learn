package com.simple.coustom.controller;

import com.simple.coustom.fegin.RentFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Simple
 * @Date 2023/3/20
 */
@RestController
public class CustomerController {

    @Resource
    private RentFeign rentFeign;


    @RequestMapping("customerRent")
    public String CustomerRentCar() {

        String rent = rentFeign.rent();

        return rent;
    }
}

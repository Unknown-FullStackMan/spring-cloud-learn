package cn.simple.eurekaclient.controller;

import cn.simple.eurekaclient.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;


/**
 * @Author Simple
 * @Date 2023/3/16
 */
@RestController
public class LoginController {


    @Resource
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/doLogin")
    public String testOpenFeign(String name, String pwd) {


        User user = new User(1,name,pwd,20);
        String token = "xxx" ;
        redisTemplate.opsForValue().set(token , user.toString(), Duration.ofSeconds(7200));
        return token;
    }


}

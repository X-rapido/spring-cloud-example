package com.example.controller;

import com.example.feign.UserClient;
import com.example.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    /**
     * Feign 方式请求
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userClient.getUser(id);
        return user;
    }

    /**
     * Feign 方式请求复杂参数
     */
    @PostMapping("/user/info")
    public User getUser2(User user) {
        User u = userClient.getUserInfo(user);
        return u;
    }

    /**
     * hystrix 方式请求，设置熔断
     */
    @GetMapping("/user3/{id}")
    @HystrixCommand(fallbackMethod = "failure")
    public Object getUser3(@PathVariable Long id) {
        User user = userClient.getUser(id);
        return user;
    }

    /**
     * hystrix 熔断回掉方法
     */
    private Object failure(Long id) {
        return "启动 Hystrix 熔断方法，接口请求用户id为：" + id;
    }

}

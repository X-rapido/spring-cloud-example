package com.example.controller;

import com.example.feign.UserClient;
import com.example.feign.fallback.UserClientHystrix;
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
     * Feign - hystrix 方式请求，设置接口回掉方式熔断
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userClient.getUser(id);
        return user;
    }

    /**
     * hystrix 方式请求，设置熔断
     */
    @GetMapping("/user2/{id}")
    @HystrixCommand(fallbackMethod = "failure")
    public Object getUser2(@PathVariable Long id) {
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

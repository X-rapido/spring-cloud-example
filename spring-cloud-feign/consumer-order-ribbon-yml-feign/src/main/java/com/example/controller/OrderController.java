package com.example.controller;

import com.example.feign.UserClient;
import com.example.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserClient userClient;

    /**
     * RestTemplate 方式请求
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        String url = "http://192.168.199.160:8000";    // 提供者的url
        User user = restTemplate.getForObject(url + "/user/" + id, User.class);
        return user;
    }

    /**
     * EurekaClient 方式请求
     */
    @GetMapping("/user2/{id}")
    public User getUser2(@PathVariable Long id) {
        InstanceInfo info = eurekaClient.getNextServerFromEureka("provider-user", false);
        String url = info.getHomePageUrl();
        System.out.println("提供者url：" + url);
        User user = restTemplate.getForObject(url + "/user/" + id, User.class);
        return user;
    }

    /**
     * Feign 方式请求
     */
    @GetMapping("/user3/{id}")
    public User getUser3(@PathVariable Long id) {
        User user = userClient.getUser(id);
        return user;
    }

    /**
     * Feign 方式请求复杂参数
     */
    @PostMapping("/user/info")
    public User getUser4(User user) {
        User u = userClient.getUserInfo(user);
        return u;
    }

    /**
     * 获取负载轮训的url
     */
    @GetMapping("/address")
    public Object getAddress() {
        ServiceInstance choose = loadBalancerClient.choose("provider-user");
        return choose.getUri();
    }
}

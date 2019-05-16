package com.example.controller;

import com.example.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class ConsumerOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

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
     * 获取负载轮训的url
     */
    @GetMapping("/address")
    public Object getAddress() {
        ServiceInstance choose = loadBalancerClient.choose("provider-user");
        return choose.getUri();
    }
}

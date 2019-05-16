package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 服务发现测试
 */
@RestController
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 发现所有服务
     */
    @RequestMapping("services")
    public Object services() {
        List<String> services = discoveryClient.getServices();
        return services;
    }

    /**
     * 发现指定服务
     */
    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

}

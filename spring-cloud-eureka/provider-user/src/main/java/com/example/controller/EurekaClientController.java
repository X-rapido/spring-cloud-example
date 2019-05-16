package com.example.controller;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/eureka/info")
    public String info(){
        InstanceInfo info = eurekaClient.getNextServerFromEureka("provider-user",false);
        return info.getHomePageUrl();
    }

}

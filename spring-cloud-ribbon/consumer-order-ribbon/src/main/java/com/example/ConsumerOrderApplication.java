package com.example;

import com.config.MyRibbonConfig;
import com.example.conf.ExcludeFromComponetScan;
import com.example.conf.MyRibbonConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@RibbonClient(name = "provider-user", configuration = MyRibbonConfig2.class)    // 启用 ribbon 对 provider-user 进行负载
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponetScan.class)})
public class ConsumerOrderApplication {

    /**
     * 注册一个 RestTemplate Bean
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApplication.class, args);
    }

}

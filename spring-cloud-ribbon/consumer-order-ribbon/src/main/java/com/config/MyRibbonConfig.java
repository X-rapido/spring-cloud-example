package com.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 Ribbon 负载策略
 * 官方要求，不能被 @ComponentScan 或 @SpringBootApplication 所扫描到
 * 如果非要放在扫描到的包，那么需要做的就是注解扫描排除此类
 *
 * 当前位置不被扫描到
 */
@Configuration
public class MyRibbonConfig {

    @Autowired
    private IClientConfig clientConfig;

    /**
     * 创建负载均衡算法
     */
    @Bean
    public IRule ribbonRule(IClientConfig clientConfig){
        return new RandomRule();
    }
}

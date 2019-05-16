package com.example.feign.fallback;

import com.example.feign.UserClient;
import com.example.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断回退指定，使用工厂方式
 */
@Component
public class UserClientFactoryHystrix implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable throwable) {

        return new UserClient() {
            @Override
            public User getUser(Long id) {
                return new User().setId(-100L).setName("工厂模式 - 无名").setTimestamp(System.currentTimeMillis());
            }
        };
    }
}

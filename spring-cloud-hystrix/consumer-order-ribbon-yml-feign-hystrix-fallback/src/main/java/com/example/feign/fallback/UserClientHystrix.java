package com.example.feign.fallback;

import com.example.feign.UserClient;
import com.example.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 熔断回退指定
 */
@Component
public class UserClientHystrix implements UserClient {

    @Override
    public User getUser(Long id) {
        return new User().setId(-100L).setName("无名").setTimestamp(System.currentTimeMillis());
    }

}

package com.example.feign;

import com.example.feign.fallback.UserClientFactoryHystrix;
import com.example.feign.fallback.UserClientHystrix;
import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 使用 feign 请求消息提供者，指定 provider-user 接口的地址
 * 对接口指定 hystrix 熔断回掉 UserClientHystrixImpl 接口实现，而不是 @HystrixCommand 指定具体方法
 */
//@FeignClient(name = "provider-user",fallback = UserClientHystrix.class)
@FeignClient(name = "provider-user",fallbackFactory = UserClientFactoryHystrix.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User getUser(@PathVariable("id") Long id);

}

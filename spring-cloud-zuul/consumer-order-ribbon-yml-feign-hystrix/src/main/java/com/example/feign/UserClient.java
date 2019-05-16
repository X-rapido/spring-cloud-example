package com.example.feign;

import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 使用 feign 请求消息提供者，指定 provider-user 接口的地址
 */
@FeignClient("provider-user")
public interface UserClient {

    /**
     * PathVariable 括号里的 id 参数必须声明
     */
    @GetMapping("/user/{id}")
    User getUser(@PathVariable("id") Long id);

    /**
     * 复杂对象参数，需要消息提供方使用 post 方式传递，否则会出现405的情况，当然 消费方可以对 get 请求做一层封装
     * curl -X POST -d "id=100&name=程序喵" http://172.16.51.249:9000/order/user/info
     */
    @PostMapping("/user/info")
    User getUserInfo(@RequestBody User user);

}

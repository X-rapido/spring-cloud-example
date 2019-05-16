package com.example.controller;

import com.example.pojo.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return new User().setId(id);
    }

//    @GetMapping("/info")
    @PostMapping("/info")
    public User getUserInfo(@RequestBody User user){
        return user;
    }
}

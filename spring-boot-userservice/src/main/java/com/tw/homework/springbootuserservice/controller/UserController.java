package com.tw.homework.springbootuserservice.controller;

import com.tw.homework.springbootuserservice.model.User;
import com.tw.homework.springbootuserservice.model.UserQuery;
import com.tw.homework.springbootuserservice.service.UserServiceImpl;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/user")
@EnableEurekaClient
@RestController
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/getUserById")
    public User get(@RequestParam("userId") String id){
        User user= userService.get(id);
        return user;
    }

    @PostMapping("/updateUser")
    public void update(@RequestBody User user){
        userService.update(user);
    }

    @PostMapping("/deleterUserById/{id}")
    public void delete(@PathVariable String id){
        userService.delete(id);
    }

    @GetMapping("/findAllUser")
    public Page<User> findAllUser(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return userService.findUsers(page, size);
    }

    @GetMapping("/findAllUserBy")
    public Page<User> findAllUserBy(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("name") String name, @RequestParam("age") Integer age){
        return userService.findUsersBy(page, size, new UserQuery(name, age));
    }
}

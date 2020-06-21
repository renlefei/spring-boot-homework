package com.tw.homework.springbootemailservice.controller;

import com.tw.homework.springbootemailservice.service.EmailService;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/email")
@EnableEurekaClient
public class EmailController {
    @Resource
    EmailService emailService;

    @GetMapping("/{userId}")
    public String getUserEmail(@PathVariable String userId){
        return  emailService.getUserEmail(userId);
    }
}

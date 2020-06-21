package com.tw.homework.springbootuserservice.FeignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "emailservice",url = "http://localhost:8091/", fallbackFactory = EmailServiceFallback.class)
@Service
@RequestMapping("/email")
public interface EmailService {
    @GetMapping("/{userId}")
    public String getUserEmail(@PathVariable String userId);
}

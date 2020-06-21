package com.tw.homework.springbootuserservice.FeignService;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceFallback implements FallbackFactory<EmailService> {
    @Override
    public EmailService create(Throwable cause) {
        return new EmailService() {
            @Override
            public String getUserEmail(String userId) {
                return "default@email.com";
            }
        };
    }
}

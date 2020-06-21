package com.tw.homework.springbootemailservice.service;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public String getUserEmail(String id){
        return id + "@rest.local";
    }
}

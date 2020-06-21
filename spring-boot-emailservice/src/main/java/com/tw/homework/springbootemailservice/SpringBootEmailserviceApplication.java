package com.tw.homework.springbootemailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootEmailserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailserviceApplication.class, args);
	}

}

package com.example.demozullservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemozullserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemozullserviceApplication.class, args);
	}

	@RequestMapping(value = "/title")
	public String title() {
		return "Spring Boot 2.0 极简教程";
	}

	@RequestMapping(value = "/author")
	public String author() {
		return " 陈光剑";
	}
}

package com.jeb.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudConfigClientApplication {
	public static void main(String[] args) {
		SpringApplication  springApplication  = new SpringApplication(SpringCloudConfigClientApplication.class);
		springApplication.setWebEnvironment(true);
		springApplication.run(args);

		//SpringApplication.run(SpringCloudConfigClientApplication.class, args);
	}

	@Value("${hello}")
	private String hello;

	@RequestMapping("hello")
	public String hello() {
		return hello;
	}


}

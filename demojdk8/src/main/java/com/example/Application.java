package com.example;

import com.example.designmodel.publishsubscribe.PublishSubscribe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRetry
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		PublishSubscribe.publishEvent();
	}


}

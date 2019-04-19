package com.example.demozull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableZuulProxy
public class DemozullApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemozullApplication.class, args);
		try {
			Class.forName("com.example.demozull.controller.ControllerTest");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

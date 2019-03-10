package com.example.demoquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = "com")
public class DemoquartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoquartzApplication.class, args);
	}
	private static class MyBean {

		@PostConstruct
		public void init() {
			System.out.println("init");
		}

		public void doSomething() {
			System.out.println("in doSomething()");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("destroy");
		}
	}

}


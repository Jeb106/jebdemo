package com.jeb.springcloudconfigclient;

import com.jeb.springcloudconfigclient.demo.MyHealthIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@SpringBootApplication
@RestController
@Slf4j
public class SpringCloudConfigClientApplication {

	private final ContextRefresher contextRefresher;
	private final Environment environment;

	@Autowired
	public SpringCloudConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {
		this.contextRefresher = contextRefresher;
		this.environment = environment;
	}

	public static void main(String[] args) {
//		SpringApplication  springApplication  = new SpringApplication(SpringCloudConfigClientApplication.class);
//		springApplication.setWebEnvironment(true);
//		springApplication.run(args);

		SpringApplication.run(SpringCloudConfigClientApplication.class, args);
	}
	@Scheduled(fixedRate = 1000*10,initialDelay = 1000*2)
	public void autoRefresh() {
		log.info("refresh");
		Set<String> keys = contextRefresher.refresh();
		if (!keys.isEmpty()) {
			keys.forEach(key -> System.out.printf("[thread:%s ,key:%s,value:%s]",Thread.currentThread().getName(),key,environment.getProperty(key)));
		}

	}
	@Bean
	public MyHealthIndicator myHealthIndicator(){
		return new MyHealthIndicator();
	}

}

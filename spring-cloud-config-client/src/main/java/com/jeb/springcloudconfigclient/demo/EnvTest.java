package com.jeb.springcloudconfigclient.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：EnvTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-23 21:54
 */
@RestController
@RefreshScope
public class EnvTest {
	private final Environment environment;

	@Autowired
	public EnvTest(Environment environment) {
		this.environment = environment;
	}

	@Value("${my.name}")
	private String name;

	@GetMapping("/my-name")
	public String getEnvValue(){
		return  "name:"+name;
	}

	@GetMapping("/echo/env/{name}")
	public String getEvnProperties(@PathVariable String name) {
		return environment.getProperty(name);
	}
}

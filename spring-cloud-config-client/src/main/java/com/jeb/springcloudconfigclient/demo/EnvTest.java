package com.jeb.springcloudconfigclient.demo;

import org.springframework.beans.factory.annotation.Autowired;
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
public class EnvTest {
	private final Environment environment;

	@Autowired
	public EnvTest(Environment environment) {
		this.environment = environment;
	}

	@GetMapping("/echo/env/{name}")
	public String getEvnProperties(@PathVariable String name) {
		return environment.getProperty(name);
	}
}

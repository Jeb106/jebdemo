package com.jeb.demo.distributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName：Redis
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-03-09 22:26
 */
@RestController
public class Redis {
	@Autowired
	RedisTemplate redisTemplate;
	@RequestMapping("/stock")
	public String stock() {
		String key = "key";
		redisTemplate.opsForValue().set(key,"jeb");
		redisTemplate.opsForValue().setIfAbsent(key,"jeb");
		redisTemplate.opsForValue().setIfAbsent(key, "jeb", 10, TimeUnit.SECONDS);

		String obj = redisTemplate.opsForValue().get(key).toString();
		System.out.println(obj);
		return "";
	}
}

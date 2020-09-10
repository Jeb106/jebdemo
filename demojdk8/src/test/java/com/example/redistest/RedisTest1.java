package com.example.redistest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName：RedisTest1
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-08-19 14:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest1 {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	RedisTemplate redisTemplate;


	@Test
	public void redisStringTest(){
		 stringRedisTemplate.opsForValue().set("name","hjb");
	}


	@Test
	public void redisBoundHashOpsTest(){
		//BoundHashOps 集合操作
		BoundHashOperations fable = redisTemplate.boundHashOps("fable");
		fable.put("name","hjb");
		fable.put("name2","jeb");
		System.out.println(fable.getKey());
//		System.out.println(fable.get("name"));
		fable.values().forEach(v-> System.out.println(v));
		fable.entries().forEach((m,n) -> System.out.println("获取map键值对:" + m + "-" + n));
	}

	@Test
	public void redisSetTest(){

		//list存储
		this.redisTemplate.opsForList().leftPushAll("names","李四","王五","赵六");
		List names = this.redisTemplate.opsForList().range("names", 1, 2);
		System.out.println(names.toString());
	}

	@Test
	public void redisHashTest(){
		//hash使用
		Map<String, String> map = new HashMap<>(4);
		map.put("name","秦始皇");
		map.put("age","未知");
		map.put("sex","男");
		this.redisTemplate.opsForHash().putAll("HASH_PERSON",map);
		Object o = this.redisTemplate.opsForHash().get("HASH_PERSON", "name");
		System.out.println(o.toString());
	}

}

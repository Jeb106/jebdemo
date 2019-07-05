package com.jeb.springboot.demo.resttemplete;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName：RestTempleteTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-07-03 22:00
 */
public class RestTempleteTest {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		String forObject = restTemplate.getForObject("http://www.baidu.com", String.class);
		System.out.println(forObject);
	}
}

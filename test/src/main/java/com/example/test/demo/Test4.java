package com.example.test.demo;

import koal.usap.client.author.ClientForAuthor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：Test4
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-06-15 18:33
 */
@Slf4j
@RestController
public class Test4 {
	@GetMapping(value = "/test")
	public void test(){
		ClientForAuthor clientForAuthor = new ClientForAuthor();
		clientForAuthor.setAppCode("111");
		log.info("appCode:"+clientForAuthor.getAppCode());

	}

	public static void main(String[] args) {

		ClientForAuthor clientForAuthor = new ClientForAuthor();


	}
}

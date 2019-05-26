package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

	}
	public String getMessage(String name, int age) {
		return String.format("%s is %d years old.", name, age);







	}

	public void tesdfst() {

		String a = "{\"data\":[{\"KTL_FLG\":\"N\",\"name\":\"fss\",\"id\":2}],\"fieldNames\":\"id,name\",\"lobLines\":\"\"}";
	}
}

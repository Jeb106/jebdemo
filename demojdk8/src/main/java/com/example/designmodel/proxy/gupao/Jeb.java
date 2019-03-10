package com.example.designmodel.proxy.gupao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jinBiaoHu
 * @date 2019-02-23 23:11
 */
@Slf4j
public class Jeb implements Persion {
	private  String name;
	private  int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public void findLove() {
		log.info("my name is {} age is {}",name,age);
		log.info("标准:白富美");
	}
}

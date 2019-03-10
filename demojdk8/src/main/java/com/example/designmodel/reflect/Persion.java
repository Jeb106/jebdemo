package com.example.designmodel.reflect;

/**
 * @author jinBiaoHu
 * @date 2019-02-10 13:23
 */
public class Persion {
	private String name;

	public Persion(){
		System.out.println("无参构造");
	}

	public Persion(String name) {
		this.name = name;
	}

	public void play() {
		System.out.println("persion.play");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

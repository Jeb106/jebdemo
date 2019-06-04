package com.example;

/**
 * @ClassName：AliTemplete
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-05-30 22:50
 */
abstract class AliTemplete {
	private int age;
	private String address;

	public AliTemplete() {
	}

	public AliTemplete(int age, String address) {
		this.address = address;
		this.age = age;
	}


	public int count(int n) {
		return 100 * n * swin();
	}

	/**
	 * @return
	 */
	protected abstract int swin();

	private void say() {
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

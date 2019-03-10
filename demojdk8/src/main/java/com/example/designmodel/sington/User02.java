package com.example.designmodel.sington;

/**
 * 懒汉式单例模式
 * @author jinBiaoHu
 * @date 2019-02-10 16:11
 */
public class User02 {
	private static User02 user02 ;
	//1 构造函数私有化
	private User02() {
		System.out.println("无参构造");
	}
	public static synchronized User02 getInstance(){
		if (user02 == null ){
			user02 = new User02();

		}
		return user02;
	}

	public static void main(String[] args) {
		User02 i1 = User02.getInstance();
		User02 i2 = User02.getInstance();
		System.out.println(i1 == i2);
	}

}

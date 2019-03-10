package com.example.designmodel.sington;

/**
 * 恶汉式单例模式
 * @author jinBiaoHu
 * @date 2019-02-10 16:11
 */
public class User01 {
	private static final User01 user01 = new User01() ;
	//1 构造函数私有化
	private User01() {
		System.out.println("无参构造");
	}
	public static User01  getInstance(){
		return  user01;
	}

	public static void main(String[] args) {
		User01 i1 = User01.getInstance();
		User01 i2 = User01.getInstance();
		System.out.println(i1 == i2);
	}

}

package com.example.designmodel.sington;

/**
 * 双重判断 单例模式
 * @author jinBiaoHu
 * @date 2019-02-10 16:11
 */
public class User04 {
	private static volatile User04 user02 ;
	//1 构造函数私有化
	private User04() {
		System.out.println("无参构造");
	}
	public static  User04 getInstance(){
		if (user02 == null) {
			synchronized(User04.class){
				if (user02 == null ){
					user02 = new User04();
				}
			}
		}

		return user02;
	}

	public static void main(String[] args) {
		User04 i1 = User04.getInstance();
		User04 i2 = User04.getInstance();
		System.out.println(i1 == i2);
	}

}

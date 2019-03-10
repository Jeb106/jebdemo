package com.example.designmodel.sington;

/**
 * enum单例模式
 * @author jinBiaoHu
 * @date 2019-02-10 16:11
 */
public class User03 {

	//1 构造函数私有化
	private  User03() {
		System.out.println("无参构造");
	}

	public enum  UserEnum{
		INSTANCE;
		private  User03 user03;
		UserEnum(){
			user03 = new User03();
		}
		public User03 getInstance(){
			return user03;
		}
	}

	public static  User03 getInstance(){
		return  UserEnum.INSTANCE.getInstance();
	}

	public static void main(String[] args) {
		User03 i1 = User03.getInstance();
		User03 i2 = User03.getInstance();
		System.out.println(i1 == i2);
	}

}

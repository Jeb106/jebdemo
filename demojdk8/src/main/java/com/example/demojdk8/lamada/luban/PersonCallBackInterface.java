package com.example.demojdk8.lamada.luban;

/**
 * @ClassName：PersonCallBack
 * @description：
 * java8 接口支持添加默认方法 可以有多个默认方法
 * 还支持静态方法 但静态方法只能通过接口调用 实现类不能去调用
 * @author： huJb
 * @date：2020-05-01 19:19
 */


public interface PersonCallBackInterface<T> {

	void callBack(T t);
	void callBack2(T t);
	default  void callBack3(T t){
		System.out.println("default method");
	}

	default  void callBack4(T t){
		System.out.println("default method");
	}

	static void callBack5(){
		System.out.println("default method");
	}
	static void callBack6(){
		System.out.println("default method");
	}
}

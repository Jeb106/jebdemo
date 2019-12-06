package com.jeb.demo.ftptest;




public class StaticTest {
	public static String name = "jeb";

	static{
		log.info("=======================");
		log.info("静态代码块初始化");
		log.info("=======================");
	}

	public static void test() {
		log.info("=======================");
		log.info("静态方法初始化");
		log.info("=======================");
	}
}

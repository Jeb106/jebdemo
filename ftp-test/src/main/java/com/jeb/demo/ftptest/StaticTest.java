package com.jeb.demo.ftptest;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName：StaticTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-04 11:02
 */
@Slf4j
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

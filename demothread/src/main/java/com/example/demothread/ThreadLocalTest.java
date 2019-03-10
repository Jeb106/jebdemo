package com.example.demothread;

/**
 * @author jinBiaoHu
 * @date 2019-01-28 22:00
 */
public class ThreadLocalTest {


	public static  ThreadLocal<Integer> integerThreadLocal =  new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

}

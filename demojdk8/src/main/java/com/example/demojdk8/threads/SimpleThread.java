package com.example.demojdk8.threads;

import java.util.concurrent.TimeUnit;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-12-13 15:06
 *
 * @author jinBiaoHu
 */
public class SimpleThread {
	public static void main(String[] args) {
		String var = "sleep end";
		Runnable task = () ->{
			try {
				Thread.currentThread().setName("simpleThread1");
				String threadName = Thread.currentThread().getName();
				System.out.println("threadName:"+threadName);
				TimeUnit.SECONDS.sleep(5);
				System.out.println(var);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}


		};
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Done!");
	}
}

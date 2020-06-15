package com.example.demothread.test;

/**
 * @ClassName：ThreadStop
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-06-11 17:21
 */
public class ThreadStop {
	public static void main(String[] args) throws InterruptedException {
		StopThread thread = new StopThread();
		thread.start();
		// 休眠1秒，确保i变量自增成功
		Thread.sleep(1000);
		// 暂停线程
		//thread.stop(); // 错误的终止
		thread.interrupt(); // 正确终止
		while (thread.isAlive()) {
			// 确保线程已经终止
		} // 输出结果
		thread.print();
	}


}

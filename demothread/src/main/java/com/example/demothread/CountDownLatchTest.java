package com.example.demothread;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 	countDownLatch.countDown(); 线程执行完减一
 *  countDownLatch.await(); 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务
 * @author jinBiaoHu
 * @date 2019-01-30 16:02
 */

public class CountDownLatchTest {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		class Test1 extends Thread {
			@Override
			public void run() {
				System.out.println("===========Test1开始执行========");
				countDownLatch.countDown();
				System.out.println("===========Test1执行结束========");
			}
		}
		class Test2 extends Thread {
			@Override
			public void run() {
				System.out.println("===========Test2开始执行========");
				countDownLatch.countDown();
				System.out.println("===========Test2执行结束========");
			}
		}
		Test1 test1 = new Test1();
		Test2 test2 = new Test2();

		Thread t1 = new Thread(test1);
		Thread t2 = new Thread(test2);

		t1.start();
		t2.start();
		try {
			countDownLatch.await();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("两个子线程执行完毕....");
		System.out.println("主线程继续执行.....");
	}
}

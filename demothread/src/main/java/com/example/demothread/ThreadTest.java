package com.example.demothread;

/**
 * @author jinBiaoHu
 * @date 2019-01-27 21:41
 */
public class ThreadTest {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "___" + i);
			}
		});
		thread.setPriority(10);

		Thread thread2 = new Thread(() -> {

			try {
				thread.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "___" + i);
			}
		});

		Thread thread3 = new Thread(() -> {

			try {
				thread2.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "___" + i);
			}
		});

		thread.setName("thread1");
		thread2.setName("thread2");
		thread3.setName("thread3");

		thread.start();
		thread2.start();
		thread3.start();

	}
}

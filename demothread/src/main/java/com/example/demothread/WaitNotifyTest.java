package com.example.demothread;

import java.util.concurrent.TimeUnit;

/**
 * @author jinBiaoHu
 * @date 2019-01-29 8:58
 */
public class WaitNotifyTest {
	/**
	 * sleep不释放锁
	 * wait释放锁
	 * @param args
	 */
	public static void main(String[] args) {
		Res res = new Res();
		InputThread input = new InputThread(res);
		OutThread out = new OutThread(res);
		input.start();
		out.start();

	}

	static class Res {
		public String name;

		public String sex;

		/**
		 * flag false 只能写 不能读
		 * flag true  只能读 不能写
		 */
		public boolean flag = false;

	}

	static class InputThread extends Thread {
		private Res res;

		public InputThread(Res res) {
			this.res = res;
		}

		@Override
		public void run() {
				Integer count = 0;
				while (true) {
					synchronized (res) {
						if (res.flag) {
							try {
								res.wait();
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						try {
							TimeUnit.SECONDS.sleep(1);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					if (count == 0) {
						res.name = "小红";
						res.sex = "女";
					}
					else {
						res.name = "小军";
						res.sex = "男";
					}
					count = (count + 1) % 2;
						res.flag = true;
						res.notify();
				}
			}
		}
	}

	static class OutThread extends Thread {
		private Res res;

		public OutThread(Res res) {
			this.res = res;
		}

		@Override
		public void run() {
			while (true) {
				synchronized (res) {
					if (!res.flag) {
						try {
							res.wait();
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(res.name + "_" + res.sex);
					res.flag = false;
					res.notify();
				}
			}
		}
	}
}


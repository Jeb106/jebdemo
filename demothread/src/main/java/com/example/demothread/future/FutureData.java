package com.example.demothread.future;

/**
 * @author jinBiaoHu
 * @date 2019-01-30 23:04
 */
public class FutureData implements Data {

	public volatile static boolean ISFLAG = false;

	private RealData realData;

	public synchronized void setRealData(RealData realData) {
		//如果已经获取到数据 返回
		if (ISFLAG) {
			return;
		}
		//如果没有获取到真实对象,传递真实对象
		this.realData = realData;
		ISFLAG = true;
		notify();

	}

	@Override
	public synchronized String request() {

		while (!ISFLAG) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.request();
	}
}

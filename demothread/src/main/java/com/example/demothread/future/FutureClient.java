package com.example.demothread.future;

/**
 * @author jinBiaoHu
 * @date 2019-01-30 23:13
 */
public class FutureClient {

	public Data request(String queryStr) {
		FutureData futureData = new FutureData();
		new Thread(new Runnable() {
			@Override
			public void run() {
				RealData realData = new RealData("111");
				futureData.setRealData(realData);
			}
		}).start();
		return futureData;
	}

}

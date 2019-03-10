package com.example.demothread.future;

/**
 * @author jinBiaoHu
 * @date 2019-01-30 23:18
 */
public class Test {
	public static void main(String[] args) {
		FutureClient futureClient = new FutureClient();
		Data request = futureClient.request("test");
		System.out.println("请求发送成功!");
		System.out.println("执行其他任务...");
		request.request();
		System.out.println("获取结果:"+request);

	}

}

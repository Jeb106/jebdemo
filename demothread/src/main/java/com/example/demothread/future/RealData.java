package com.example.demothread.future;

/**
 * @author jinBiaoHu
 * @date 2019-01-30 23:05
 */
public class RealData implements Data {

	private  String result;
	public RealData(String data){
		System.out.println("正在使用" + data + "获取数据");
		try {
			Thread.sleep(5);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("操作完毕,获取结果");

		result = "jeb";
	}


	@Override

	public String request() {
		return result;
	}
}

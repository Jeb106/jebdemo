package com.example.designmodel.adapter;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 20:58
 */
public class Test {
	public static void main(String[] args) {
	/* 日本电饭煲正常工作110V
	   JP110VInterface jp110VInterface = new JP110Connection();
		DianFanBao dianFanBao = new DianFanBao(jp110VInterface);
		dianFanBao.work();*/

	// 日本电饭煲中国220V
		CN220VInterface cn220VInterface = new CN220Connection();
		Adapter adapter = new Adapter(cn220VInterface);
		DianFanBao dianFanBao = new DianFanBao(adapter);
		dianFanBao.work();
	}
}

package com.example.designmodel.adapter;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 20:56
 */
public class DianFanBao {
	JP110VInterface jp110VInterface;
	public  DianFanBao(JP110VInterface jp110VInterface){
		this.jp110VInterface = jp110VInterface;
	}

	public void work() {
		jp110VInterface.connection();
		System.out.println("开始做饭");

	}
}

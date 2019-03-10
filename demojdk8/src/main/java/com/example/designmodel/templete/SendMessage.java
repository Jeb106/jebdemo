package com.example.designmodel.templete;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 20:14
 */
public abstract class SendMessage {

	public void sendMessage() {
		addHeadMsg();
		sendMsg();
		addFootMsg();

	}

	private void addFootMsg() {
		System.out.println("添加尾部日志信息");
	}

	abstract void sendMsg(

	);

	private void addHeadMsg() {
		System.out.println("添加头部日志信息");
	}
}

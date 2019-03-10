package com.example.designmodel.adapter;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 20:55
 */
public class JP110Connection implements JP110VInterface
{
	@Override
	public void connection() {
		System.out.println("日本110V开始工作");
	}
}

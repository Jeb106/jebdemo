package com.example.designmodel.adapter;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 20:55
 */
public class CN220Connection implements CN220VInterface
{
	@Override
	public void connection() {
		System.out.println("中国220V开始工作");
	}
}

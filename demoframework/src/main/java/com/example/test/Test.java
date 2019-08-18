package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-12-13 20:40
 * 产生固定大小的数据
 *
 * @author jinBiaoHu
 */
public class Test {
	public static void main(String[] args) {
		List<int[]> list = new ArrayList();
		for (int i=0 ;i<Integer.MAX_VALUE;i++) {
			list.add(generate1M());
			System.out.println(i);
			try {
				TimeUnit.SECONDS.sleep(2);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static int[] generate1M(){
		generate1M();
		return  new int[1024 *256 *2];
	}
}

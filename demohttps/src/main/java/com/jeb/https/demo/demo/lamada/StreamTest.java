package com.jeb.https.demo.demo.lamada;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-11-05 22:54
 *
 * @author jinBiaoHu
 */
public class StreamTest {
	public static void main(String[] args) {
		Random random = new Random();
		// 随机产生数据
		Stream<Integer> stream = Stream.generate(() -> random.nextInt())
				// 产生500个 ( 无限流需要短路操作. )
				.limit(5)
				// 第1个无状态操作
				.peek(s -> print("peek: " + s))
				// 第2个无状态操作
				.filter(s -> {
					print("filter: " + s);
					return s > 1000000;
				})
				// 有状态操作
				.sorted((i1, i2) -> {
					print("排序: " + i1 + ", " + i2);
					return i1.compareTo(i2);
				})
				// 又一个无状态操作c
				.peek(s -> {
					print("peek2: " + s);
				}).parallel();

		// 终止操作
		stream.count();
	}

	/**
	 * 打印日志并sleep 5 毫秒
	 *
	 * @param s
	 */
	public static void print(String s) {
		// System.out.println(s);
		// 带线程名(测试并行情况)
		System.out.println(Thread.currentThread().getName() + " > " + s);
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
	}
}

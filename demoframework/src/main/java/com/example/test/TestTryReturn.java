package com.example.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-12-11 10:08
 * return与finally 执行顺序
 *
 * @author jinBiaoHu
 */
@Slf4j
public class TestTryReturn {
	private static int i = 10;

	public static void main(String[] args) {
		log.info(Integer.toString(test()));
		log.info(Integer.toString(i));

	}

	public static int test() {
		try {
			log.info("try module");
			return ++i;
		}
		catch (Exception e) {
			log.error("", e);
		}
		finally {
			++i;
			log.info("finally module");
		}
		return 0;
	}
	/**
	 * 其实return与finally并没有明显的谁强谁弱。在执行时，是return语句先把返回值写入但内存中，然后停下来等待finally语句块执行完，return再执行后面的一段
	 */
}

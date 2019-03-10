package com.example.test.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jinBiaoHu
 * @date 2019-01-25 8:56
 */
@Slf4j
public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(()->log.info(" submit:{}",executorService));
		executorService.execute(()->log.info(" submit:{}",executorService));

	}
}

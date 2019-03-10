package com.example.demojdk8.threads;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-12-13 15:20
 * @author jinBiaoHu
 */
@Data
public class ExecuteTest {
	@SneakyThrows
	public static void main(String[] args) {
		//记录异常信息
		List<String> err = new Vector<>();
		ThreadFactory threadFactory =  new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
		ExecutorService executorService = new ThreadPoolExecutor(10, 20,
				60L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1024),threadFactory,new ThreadPoolExecutor.AbortPolicy());
		System.out.println(System.currentTimeMillis());
		for (int i = 0;i<10;i++) {
			executorService.submit(() -> {
				try {
					testFunction();
					System.out.println(Thread.currentThread().getName());
				}
				catch (Exception e) {
					System.out.println("catch error");
					err.add(e.getMessage());
				}
			});
		}
		//退出线程池
		executorService.shutdown();
		while (true) {
			if (executorService.isTerminated()) {
				System.out.println(System.currentTimeMillis());
				System.out.println("执行完毕!");
				System.out.println("errorSize:"+err.size());
				break;
			}
			System.out.println("线程正在执行");
			TimeUnit.SECONDS.sleep(1);
		}
	}
	@SneakyThrows
	public static void testFunction(){
		TimeUnit.SECONDS.sleep(1);
		throw new RuntimeException();
	}
}

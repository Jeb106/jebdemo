package com.example.demojdk8.threads;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.SneakyThrows;

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
public class ExecuteTest2 {
	@SneakyThrows
	public static void main(String[] args) {
		//记录异常信息
		List<String> err = new Vector<>();
		ThreadFactory threadFactory =  new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
		ExecutorService executorService = new ThreadPoolExecutor(10, 20,
				60L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1024),threadFactory,new ThreadPoolExecutor.AbortPolicy());
		Callable<Integer> task =() ->{
			try {
				TimeUnit.SECONDS.sleep(1);
				return 1;
			}
			catch (Exception e) {
				throw new IllegalStateException();
			}
		};
		Future<Integer> future = executorService.submit(task);
		System.out.println(future.isDone());
		future.get();
		System.out.println(future.isDone());

	}
	@SneakyThrows
	public static void testFunction(){
		TimeUnit.SECONDS.sleep(1);
		throw new RuntimeException();
	}
}

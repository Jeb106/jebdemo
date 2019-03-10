package com.example.demothread.future;

import java.util.concurrent.*;

/**
 * @author jinBiaoHu
 * @date 2019-01-30 22:48
 */
public class FutureTest {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		System.out.println("主线程开始");
		AddNumberTask addNumberTask = new AddNumberTask();
		Future<Integer> submit = executorService.submit(addNumberTask);
		try {
			Integer integer = submit.get();
			System.out.println("获取结束");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class AddNumberTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("AddNumberTask.call");
		Thread.sleep(5000);
		return 5000;
	}
}

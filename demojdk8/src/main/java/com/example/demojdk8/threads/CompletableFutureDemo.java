package com.example.demojdk8.threads;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName：CompletableFutureDemo
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-07-07 15:33
 * @see java.util.concurrent.CompletionStage
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws Exception {
		CompletableFuture<String> completableFuture = new CompletableFuture();
		//1 单线程 同步操作 可以被其它线程去做
		/*completableFuture.complete("hello world");
		String value = completableFuture.get();
		System.out.println(value);*/

		// 2 异步执行 阻塞操作
	/*	CompletableFuture asyncCompletableFuture = CompletableFuture.runAsync(()->{
			System.out.println("hello world!");
		});
		//仍然是阻塞操作
		asyncCompletableFuture.get();
		Systeut.println("starting...");*/

	    // 3 异步操作
	/*	CompletableFuture asyncCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				return "hello world!";
			}
		});*/
		/*CompletableFuture<String> asyncCompletableFuture = CompletableFuture.supplyAsync(()->{
			//获取数据操作
			return String.format("[Thread :%s] Hello world...\n",
					Thread.currentThread().getName());

		});
		String value = asyncCompletableFuture.get();
		System.out.println("value:"+value);
		System.out.println("starting...");*/

		//4 合并操作
		CompletableFuture<String> combinedCompletableFuture = CompletableFuture.supplyAsync(()->{
			//获取数据操作
			return String.format("[Thread :%s] Hello world...\n",
					Thread.currentThread().getName());

		}).thenApply(value -> {
			return  value +"-来自于数据库";
		}).thenApply(value->{
			return value + new Date();
		}).thenApply(value->{
			System.out.println(value);
			return value;
		});
		//combinedCompletableFuture.get();
		while (!combinedCompletableFuture.isDone()) {

		}
		System.out.println("starting...");


	}
}

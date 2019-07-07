package com.example.demojdk8.threads;

import java.util.concurrent.ForkJoinPool;

/**
 * @ClassName：ForkJoinDemo
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-07-07 10:57
 */
public class ForkJoinDemo {
	public static void main(String[] args) {
		//并行：多核参与
		//并发：一同参与
		//Forkjoin 线程池
		System.out.printf("当前公用Fork线程池，并行数：%d\n",ForkJoinPool.commonPool().getParallelism());
		System.out.printf("当前cup处理数：%d\n",Runtime.getRuntime().availableProcessors());

	}
}

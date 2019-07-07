package com.example.demojdk8.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName：ForkJoinDemo  jdk1.7
 * 存在的问题
 * 无法手动完成
 * 阻塞式结果返回
 * 无法链式多个future
 * 无法合并多个future结果
 * 缺少异常处理
 *
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-07-07 10:57
 */
public class ForkJoinDemo2 {
	public static void main(String[] args) {
		//并行：多核参与
		//并发：一同参与
		//Forkjoin 线程池
		System.out.printf("当前公用Fork线程池，并行数：%d\n",ForkJoinPool.commonPool().getParallelism());
		System.out.printf("当前cup处理数：%d\n",Runtime.getRuntime().availableProcessors());

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//		RecursiveAction 递归操作
		//累加对象
		LongAdder longAdder = new LongAdder();
		AddTask addTask = new AddTask(nums,longAdder);
		forkJoinPool.invoke(addTask);
		forkJoinPool.shutdown();
		System.out.println(longAdder.toString());

	}

	private static class AddTask extends RecursiveAction {
		private final List<Integer> nums;
		private final LongAdder longAdder;
		private AddTask(List<Integer> nums,LongAdder longAdder) {
			this.nums = nums;
			this.longAdder = longAdder;
		}

		/**
		 * The main computation performed by this task.
		 */
		@Override
		protected void compute() {
			int size = nums.size();
			//
			if (size > 1) {
				// 二分部分数
				int parts = size / 2;
				// 上部分
				List<Integer> leftPart = nums.subList(0, parts);
				AddTask leftTask = new AddTask(leftPart, longAdder);
				//下部分
				List<Integer> rightPart = nums.subList(parts,size);
				AddTask rightTask = new AddTask(rightPart, longAdder);
				invokeAll(leftTask,rightTask);

			} else {
				if (size == 0) {
					return;
				}
				Integer value = nums.get(0);
				longAdder.add(value.longValue());
			}
		}
	}

}

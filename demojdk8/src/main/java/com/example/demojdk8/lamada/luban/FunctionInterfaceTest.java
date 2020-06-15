package com.example.demojdk8.lamada.luban;

import com.google.common.base.Joiner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName：FunctionInterfaceTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-05-05 09:59
 */
public class FunctionInterfaceTest {
	public static void main(String[] args) {
//		Predicate<Integer> positiveNumber = i -> i > 0;
//		Predicate<Integer> evenNumber = i -> i % 2 == 0;
//		System.out.println(2%2);
//		assertTrue(positiveNumber.test(-3));
////		assertTrue(positiveNumber.and(evenNumber).test(3));


//	    Consumer<String> println = System.out::println;
//	    println.andThen(println).accept("abcdefg");



//		 Arrays.asList(1,2,3,45).stream()
//				 .filter(i->i%2 == 0 || i%3 ==0)
//				 .map(i->i*i)
//				 .forEachOrdered(System.out::println);
////				 .forEach(System.out::print);



			IntStream list = IntStream.range(0, 100);
			Set<Thread> threadSet = new HashSet<>();
			//开始并行执行
//			list.forEach(i -> {
			list.parallel().forEach(i -> {
				Thread thread = Thread.currentThread();
				System.err.println("integer：" + i + "，" + "currentThread:" + thread.getName());
				threadSet.add(thread);
			});
			System.out.println("all threads：" + Joiner.on("，").join(threadSet.stream().map(Thread::getName).collect(Collectors.toList())));


	}

}

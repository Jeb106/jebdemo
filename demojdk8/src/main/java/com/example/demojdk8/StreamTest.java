package com.example.demojdk8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jinBiaoHu
 * @date 2019-01-10 14:08
 */
public class StreamTest {
	public static void main(String[] args) {

		test();
	}

	private static void test() {
		List<String> list = Arrays.asList("a", "f", "c", "j", "e");
		list.forEach(System.out::println);
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		Collections.sort(list,(s1,s2)->s1.compareTo(s2));
		System.out.println(list);




		List<String> collect = list.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println(collect);

		List<Integer> listNum = Arrays.asList(1,2,3,4,5);
		IntSummaryStatistics intSummaryStatistics = listNum.stream().mapToInt(x -> x).summaryStatistics();
		System.out.println("count:"+intSummaryStatistics.getCount());

		listNum.stream().map(i -> i * i).distinct().collect(Collectors.toList()).forEach(System.out::println);


		list.forEach(System.out::print);
		Random random = new Random();
		random.ints().limit(5).forEach(System.out::println);



	}
}

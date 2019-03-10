package com.example.demojdk8;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author jinBiaoHu
 * @date 2019-01-10 18:46
 */
public class LamadaT {

	interface FunInterface{
		void operation();
	}
	public static void add (String a,FunInterface funInterface){
		funInterface.operation();
		System.out.println(a+"lamada test");
	}
	public static void dec (String a,LamadaI lamadaI){
		lamadaI.exec(a);
	}

	public static void printString (String a){

		System.out.println(a);
	}

	public static void filter(List names, Predicate condition) {
		names.stream().filter((name) -> (condition.test(name)))
				.forEach((name) -> {System.out.println(name + " ");
				});
	}
}

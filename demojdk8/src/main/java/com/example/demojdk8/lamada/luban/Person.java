package com.example.demojdk8.lamada.luban;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @ClassName：Person
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-05-01 19:18
 */
public class Person {
	private  String name;
	private String age;

	public Person(String name, String age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public static void main(String[] args) {

		//普通写法
// 		new Person().test("a", new PersonCallBack() {
//			@Override
//			public void callBack() {
//				System.out.println("call back");
//			}
//		});

		//lamada形式

//		Consumer consumer = (t)-> System.out.println(t);
//		consumer.accept("test");

		//直接写lamada 函数式接口只能有一个方法
//		new Person().test("a",(t) ->System.out.println(t));
//
//		PersonCallBack personCallBack = (t)-> System.out.println(t);
//		new Person().test("test",personCallBack);


		//方法引用 把 System.out::println; 当成Consumer accept方法但实现类  需要和accept 有一样但入参和出参
//		Consumer consumer = s -> System.out.println(s);
//		Consumer consumer2 = System.out::println;
//		consumer2.accept("test");

		//方法引用 要求 lamada中只调用一个方法  而且形式要与函数接口一致
//		Consumer<String> consumer2 =PersionExec::test;
//		consumer2.accept("test");

		Function<Long,Integer> function = t->{int i = 1;return i;};
		Function<Long,Long> function2 = Math::abs;
		function2.apply(10l);

	}

	/**
	 *
	 * @param a
	 * @param callBack 回调函数
	 */
	public void test(String a, PersonCallBack callBack) {

		System.out.println(a);
		callBack.callBack(a);


		Consumer<String> consumer2 =PersionExec::test;
		consumer2.accept("test");
	}
}

package com.example.demojdk8.lamada.luban;


import java.util.Optional;
import java.util.function.Supplier;

/**
 * @ClassName：OptionalTest
 * @description：Optional提供很多有用的方法，这样我们 就不用显式进行空值检测。
 * @author： huJb
 * @date：2020-05-02 07:29
 */
public class OptionalTest {
	public static void main(String[] args) {
		Person person = new Person("jeb","11");

//		System.out.println(new OptionalTest().test(null));
		System.out.println(new OptionalTest().test(person));

	}

	public String test(Person person) {
		Supplier supplier = ()-> new RuntimeException("test");
//		Optional optional = Optional.of(person);
		Optional<Person> person1 = Optional.ofNullable(person);
//		return Optional.ofNullable(person).map(person2 -> person2.getName()).orElse(null);
//		System.out.println(Optional.ofNullable(person).map(person2 -> person2.getName()).get());
//		System.out.println(Optional.ofNullable(person).map(person2 -> person2.getName()).orElseThrow(supplier));
//		System.out.println(Optional.ofNullable(person).map(person2 -> person2.getName()).orElse("other"))	;

		System.out.println(Optional.ofNullable(person).map(person2 -> person2.getName()).map(name->name.toUpperCase()).orElse("test"));
		return Optional.ofNullable(person).map(person2 -> person2.getName()).orElse(null);

//		System.out.println(person1.isPresent());
//		return "ok";
	}
}

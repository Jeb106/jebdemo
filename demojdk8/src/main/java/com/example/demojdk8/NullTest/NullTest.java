package com.example.demojdk8.NullTest;

import java.util.Optional;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 13:48
 */
public class NullTest {
	public static void main(String[] args) {
		Outer outer = new Outer();
		Optional.of(outer).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo).ifPresent(System.out::println);

	}
}

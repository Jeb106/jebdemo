package com.example.demojdk8.interfaces;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 18:59
 */
public class MethodTest {

	public static void main(String[] args) {
		Converter<String, Integer> converter = Integer::valueOf;

		SomeThing someThing = new SomeThing();
		Converter<String,String> converters = someThing::startWith;
		String convert = converters.convert("java");
		System.out.println(convert);
	}


}

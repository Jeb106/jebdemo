package com.example.demojdk8.interfaces;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 19:02
 */
public class ConvertTest {

	public static void main(String[] args) {
		Converter<String,Integer> convert = (from -> Integer.valueOf(from) );
		Integer integer = convert.convert("123");
		System.out.println(integer);
		Converter<String, Integer> converter = new Converter<String, Integer>() {
			@Override
			public Integer convert(String from) {
				return Integer.valueOf(from);
			}
		};
		Converter<String, Integer> convert2 = Integer::valueOf;
		Integer convert1 = convert2.convert("345");
		System.out.println(convert1);

	}

}

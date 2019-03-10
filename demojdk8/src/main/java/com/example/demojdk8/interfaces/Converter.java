package com.example.demojdk8.interfaces;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 19:01
 */
@FunctionalInterface
public interface Converter<F,T> {
	T convert(F from);
}

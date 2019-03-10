package com.example.demojdk8.interfaces;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 18:47
 */
public interface Formula {
	double calculate(int a);

	default  double sqrt(int a){
		return Math.sqrt(a);
	}
}

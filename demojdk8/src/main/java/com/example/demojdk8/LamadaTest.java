package com.example.demojdk8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jinBiaoHu
 * @date 2019-01-10 16:56
 */
public class LamadaTest {

	public static void main(String[] args) {
		LamadaT.add("sss",()->System.out.println("ss") );
		LamadaT.dec("param",LamadaT::printString);
		LamadaT.dec("param",System.out::println);

		LamadaTest lamadaTest  = new LamadaTest();

		MathOperation additionAdd = (int a, int b) -> a + b;
		System.out.println(additionAdd.operation(1, 2));


	}

	interface MathOperation {
		int operation(int a, int b);
	}

	public static String printLine(){
		System.out.println("lamada test");
		return  "lamada test";
	}

}

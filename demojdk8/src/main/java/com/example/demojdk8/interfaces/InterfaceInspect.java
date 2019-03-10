package com.example.demojdk8.interfaces;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 18:48
 */
public class InterfaceInspect implements  Formula {
	@Override
	public double calculate(int a) {
		return 0;
	}

	public static void main(String[] args) {
		InterfaceInspect interfaceInspect = new InterfaceInspect();

		System.out.println(interfaceInspect.sqrt(3));
	}
}

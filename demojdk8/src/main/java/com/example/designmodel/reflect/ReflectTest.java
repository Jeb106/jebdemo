package com.example.designmodel.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jinBiaoHu
 * @date 2019-02-10 13:23
 */
public class ReflectTest {
	public static void main(String[] args)
			throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException,
			NoSuchFieldException {
		Class<?> forName = Class.forName("com.example.designmodel.reflect.Persion");
		Persion persin= (Persion) forName.newInstance();
		/*System.out.println(forName.getName());
		Method[] declaredMethods = forName.getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			System.out.println(declaredMethod.getName());
			declaredMethod.invoke(persin,null);
			Class<?> returnType = declaredMethod.getReturnType();
			System.out.println(returnType.getName());
		}*/
		Field field = forName.getDeclaredField("name");
		field.setAccessible(true);
		field.set(persin, "jeb");
		System.out.println(persin.getName());
	}

}

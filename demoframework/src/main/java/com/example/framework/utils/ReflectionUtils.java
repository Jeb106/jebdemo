package com.example.framework.utils;

import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * @author by wolf on 2018/4/12.
 */
public class ReflectionUtils {

	private static HashMap<String, Field> fieldHashMap = new HashMap<>();

	private static HashMap<String, Method> methodHashMap = new HashMap<>();

	/**
	 * 获取可更改的final修饰字段，会缓存.
	 *
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static Field getWritableFinalField(Class clazz, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		Assert.notNull(clazz, "clazz can`t null!");
		Assert.hasText(fieldName, "fieldName can`t empty!");
		String key = clazz.getName() + "." + fieldName;
		if (!fieldHashMap.containsKey(key)) {
			Field finalField = org.springframework.util.ReflectionUtils.findField(clazz, fieldName);
			finalField.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(finalField, finalField.getModifiers() & ~Modifier.FINAL);
			fieldHashMap.put(key, finalField);
		}
		return fieldHashMap.get(key);
	}

	/**
	 * 获取字段,缓存
	 *
	 * @param clazz     clazz
	 * @param fieldName fieldName
	 * @return
	 */
	public static Field getWritableField(Class clazz, String fieldName) {
		Assert.notNull(clazz, "clazz can`t null!");
		Assert.hasText(fieldName, "fieldName can`t empty!");
		String key = clazz.getName() + "." + fieldName;
		if (!fieldHashMap.containsKey(key)) {
			Field field = org.springframework.util.ReflectionUtils.findField(clazz, fieldName);
			field.setAccessible(true);
			fieldHashMap.put(key, field);
		}
		return fieldHashMap.get(key);
	}

	/**
	 * 获取方法,缓存
	 *
	 * @param clazz      clazz
	 * @param methodName methodName
	 * @return
	 */
	public static Method getWritableMethod(Class clazz, String methodName, Class<?>... paramTypes) {
		Assert.notNull(clazz, "clazz can`t null!");
		Assert.hasText(methodName, "methodName can`t empty!");
		String key = clazz.getName() + "." + methodName;
		if (!methodHashMap.containsKey(key)) {
			Method method = org.springframework.util.ReflectionUtils.findMethod(clazz, methodName, paramTypes);
			method.setAccessible(true);
			methodHashMap.put(key, method);
		}
		return methodHashMap.get(key);
	}
}

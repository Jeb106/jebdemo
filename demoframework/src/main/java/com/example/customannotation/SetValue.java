package com.example.customannotation;

import java.lang.annotation.*;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 21:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetValue {
	/**
	 * 需要的class
	 */
	Class<?> beanClass();

	/**
	 * bean的哪个方法
	 */
	String method();

	/**
	 * 传入的参数字段
	 */
	String paramField();

	/**
	 * 要获取的字段的属性
	 */
	String targetField() default  "";
}

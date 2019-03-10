package com.jeb.framework.annotation;

import java.lang.annotation.*;

/**
 * @author jinBiaoHu
 * @date 2019-01-13 17:19
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JebService {
	String value() default  "";
}

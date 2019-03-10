package com.example.customannotation;

import java.lang.annotation.*;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 21:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedSetFieldValue {
}

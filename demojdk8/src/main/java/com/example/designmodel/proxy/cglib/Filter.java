package com.example.designmodel.proxy.cglib;

import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 文件名：Filter
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-01 21:32
 */
public class Filter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if(method.getName().equals("toString")) {
            return 1;
        }
        return 0;
    }
}

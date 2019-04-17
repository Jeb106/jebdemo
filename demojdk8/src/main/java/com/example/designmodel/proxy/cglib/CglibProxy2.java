package com.example.designmodel.proxy.cglib;

/**
 * 文件名：CglibProxy
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-01 21:30
 */


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy2 implements MethodInterceptor {

    //实现MethodInterceptor接口，定义方法的拦截器
    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("pre2");
        //通过代理类调用父类中的方法,即实体类方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after2");
        return result;
    }
}
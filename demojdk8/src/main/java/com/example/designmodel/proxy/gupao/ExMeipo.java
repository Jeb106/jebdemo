package com.example.designmodel.proxy.gupao;

import java.lang.reflect.Method;

/**
 * 文件名：ExMeipo
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-03 21:31
 */
public class ExMeipo implements ExInvocationHandler {

    private  Object target;

    public Object getInstance(Object obj){
        this.target = obj;
        return ExProxy.newProxyInstance(new ExClassLoader(),target.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始物色对象");
        method.invoke(target,args);
        System.out.println("开始相亲");

        return null;
    }
}

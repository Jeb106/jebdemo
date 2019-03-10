package com.example.designmodel.proxy.gupao;

import java.lang.reflect.Method;

/**
 * 文件名：ExInvocationHandler
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-03 21:28
 */
public interface ExInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}

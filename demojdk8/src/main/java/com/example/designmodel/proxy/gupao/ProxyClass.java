package com.example.designmodel.proxy.gupao;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jinBiaoHu
 * @date 2019-02-23 23:14
 */
@Slf4j
public  class ProxyClass implements InvocationHandler {
	Object target;
	public  Object getInstance(Object obj){
		this.target = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("----------------------");
		log.info("开始物色对象;");
		method.invoke(target, args);
		log.info("开始相处");
		log.info("----------------------");
		return null;
	}
}

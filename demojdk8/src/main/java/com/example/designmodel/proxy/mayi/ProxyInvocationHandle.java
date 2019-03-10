package com.example.designmodel.proxy.mayi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 16:22
 */
public class ProxyInvocationHandle implements InvocationHandler {
	private Object target;

	public ProxyInvocationHandle(Object target){
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("=======开启事务前===========");
		Object invoke = method.invoke(target, args);
		System.out.println("=======开启事务后===========");
		return invoke;
	}

	public static void main(String[] args) {
		IUserDao iUserDao = new UserDaoImpl();
		ProxyInvocationHandle proxyInvocationHandle = new ProxyInvocationHandle(iUserDao);
		IUserDao userDao = (IUserDao)Proxy.newProxyInstance(iUserDao.getClass().getClassLoader(), iUserDao.getClass().getInterfaces(),
				proxyInvocationHandle);
		userDao.add("jeb");
	}
}

package com.example.demonetty.rpc;

/**
 * @author jinBiaoHu
 * @date 2018-12-26 18:12
 */
public class HelloServiceImpl implements  HelloService {
	@Override
	public String hello(String name) {
		return "Hello " + name;
	}
}

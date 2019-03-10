package com.jeb.demo;

import com.jeb.framework.annotation.JebService;

/**
 * @author jinBiaoHu
 * @date 2019-01-13 17:27
 */
@JebService
public class DemoService implements IDemoService {
	public String get(String name) {
		return "hello:"+name;
	}
}

package com.example.designmodel.eventlistener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName：SpringEvetListenerDemo
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-23 12:19
 */
public class SpringEvetListenerDemo {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//注册监听器
		context.addApplicationListener(new MyApplicationListener());
		context.refresh();
		//发布事件
		context.publishEvent(new MyApplicationEvent("test"));
		context.publishEvent(new MyApplicationEvent(11));




	}
}

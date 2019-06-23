package com.example.designmodel.eventlistener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName：MyApplicationListener
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-23 12:18
 */
public class MyApplicationListener implements ApplicationListener {
	/**
	 * Handle an application event.
	 *
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("监听到消息：" + event.getSource());

	}
}

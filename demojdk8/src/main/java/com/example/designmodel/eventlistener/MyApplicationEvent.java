package com.example.designmodel.eventlistener;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName：MyApplicationEvent
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-23 12:15
 */
public class MyApplicationEvent extends ApplicationEvent {
	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public MyApplicationEvent(Object source) {
		super(source);
	}
}

package com.example.designmodel.publishsubscribe;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author jinBiaoHu
 * @date 2019-02-21 9:02
 */
@Component
public class PublishSubscribe implements ApplicationContextAware{

	@Autowired
	private static ApplicationContext applicationContext;
	public static void publishEvent(){
		OrderModel orderModel = new OrderModel();
		orderModel.setId("11");
		orderModel.setName("test");
		applicationContext.publishEvent(orderModel);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}

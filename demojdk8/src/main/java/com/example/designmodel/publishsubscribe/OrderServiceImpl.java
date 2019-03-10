package com.example.designmodel.publishsubscribe;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author jinBiaoHu
 * @date 2019-02-21 9:06
 */
@Service
public class OrderServiceImpl implements Orderservice {
	@EventListener
	@Override
	public void update(OrderModel orderModel) {
		System.out.println("update method");
	}
}

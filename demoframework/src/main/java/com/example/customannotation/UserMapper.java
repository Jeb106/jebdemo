package com.example.customannotation;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 21:23
 */
@Component
public class UserMapper {
	public String find(String customerId) {
		return "customerName:"+customerId;
	}

	public List<Order> list() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order("1","uid1"));
		orders.add(new Order("2","uid2"));
		return orders;
	}
}

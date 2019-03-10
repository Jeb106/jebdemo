package com.example.customannotation;

import lombok.Data;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 21:22
 */
@Data
public class Order {
	private String id;
	private String customerId;
	@SetValue(beanClass = UserMapper.class,method = "find",paramField = "customerId",targetField = "userName")
	private String customerName;

	public Order(String id, String customerId) {
		this.id =id;
		this.customerId =customerId;
	}
}

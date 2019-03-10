package com.example.customannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 21:28
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	UserMapper userMapper;

	@NeedSetFieldValue
	public List<Order> list() {
		List<Order> list = userMapper.list();
		return  list;
	}

}

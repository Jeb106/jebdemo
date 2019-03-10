package com.example.customannotation;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author jinBiaoHu
 * @date 2019-01-13 17:18
 */
@Controller
@RequestMapping("/demo")
public class OrderController {

	@Autowired
	private  OrderService orderService;

	@RequestMapping("/quary")
	@NeedSetFieldValue
	public void quary(HttpServletResponse resp){
		 List<Order> list = orderService.list();
		try {
			resp.getWriter().write(JSON.toJSONString(list));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

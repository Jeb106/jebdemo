package com.jeb.https.demo.demo.business;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建时间：2018-11-03 21:20
 * @author jinBiaoHu
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

	@RequestMapping("/sslTest")
	public String sslTest() {
		System.out.println("hello,https");
		return "https";
	}


}

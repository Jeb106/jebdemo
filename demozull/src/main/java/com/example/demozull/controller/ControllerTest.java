package com.example.demozull.controller;

import com.example.demozull.service.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinBiaoHu
 * @date 2018-12-30 13:42
 */
@RestController
@RequestMapping("/api")
public class ControllerTest {
	@Autowired
	private IPerson iPerson;

	@RequestMapping("/test")
	public void test(){
		iPerson.say();
	}

}

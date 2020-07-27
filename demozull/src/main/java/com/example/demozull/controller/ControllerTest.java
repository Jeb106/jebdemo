package com.example.demozull.controller;

import com.example.demozull.service.IPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinBiaoHu
 * @date 2018-12-30 13:42
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ControllerTest {
	@Autowired
	private IPerson iPerson;

	@RequestMapping("/test")
	public void test(){
		iPerson.say();
	}


	@RequestMapping("/input/{value}")
	public String  input(@PathVariable( name = "value")  int value){
		log.info("info");
		log.debug("debug");
		log.error("error");
		log.trace("trace");

		if (value > 0) {
			return "success";
		} else {
			throw new RuntimeException("tt");
		}



	}



	public static String   out(int value){
		if (value > 0) {
			return "success";
		} else {
			throw new RuntimeException("tt");
		}



	}

}

package com.example.demospring.module.user.web;

import com.example.demospring.module.user.domain.Person;
import com.example.demospring.module.user.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName：PersonController
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-18 11:38
 */
@RestController
@RequestMapping("/api/person")
@Slf4j
public class PersonController {
	@Autowired
	private PersonService personService;
	@RequestMapping("/add")
	public boolean addPerson(){
		Person person = new Person();
		person.setId(UUID.randomUUID().toString());
		person.setName("jeb");
		return  personService.addPerson(person);
	}
	@RequestMapping("/test")
	public Map<String, Object> test(HttpServletRequest request) {
		log.info("===================requestParam:{}", request.getParameter("name"));
		log.info("===================sessionAttribute:{}", request.getSession().getAttribute("name"));
		Map<String, Object> result = new HashMap<>();
		result.put("returnCode", 123);
		result.put("message","jeb");
		return  result;
	}
}

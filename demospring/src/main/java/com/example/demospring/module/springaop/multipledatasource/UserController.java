package com.example.demospring.module.springaop.multipledatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：UserController
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-16 16:23
 */
@RestController
public class UserController {

	@Autowired
	private DataSourceRepository  dataSourceRepository;

	@PostMapping("/api/user/add")
	public boolean addUser(@RequestBody User user) {

		return dataSourceRepository.addUser(user);
	}
}

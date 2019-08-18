package com.example.demospring.module.user.service;

import com.example.demospring.module.user.domain.Person;

/**
 * @ClassName：PersonService
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-18 11:35
 */
public interface PersonService {
	boolean addPerson(Person person);

	Person findOne(String id);

	boolean deleteByName(String  name);

}

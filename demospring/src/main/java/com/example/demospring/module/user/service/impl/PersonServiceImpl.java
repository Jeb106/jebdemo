package com.example.demospring.module.user.service.impl;

import com.example.demospring.module.user.domain.Person;
import com.example.demospring.module.user.repository.PersonRepository;
import com.example.demospring.module.user.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName：PersonServiceImpl
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-18 11:36
 */
@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;
	@Override
	public boolean addPerson(Person person) {
		return personRepository.save(person) != null;
	}

	@Override
	public Person findOne(String id) {
		return personRepository.findOne(id);
	}

	@Override
	public boolean deleteByName(String name) {
		List<Person> people = personRepository.findByName(name);
		personRepository.delete(people);
		return true;
	}


}

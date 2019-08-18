package com.example.junitpersontest;

import com.example.demospring.DemospringApplication;
import com.example.demospring.module.user.domain.Person;
import com.example.demospring.module.user.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @ClassName：PersonServiceTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-18 11:42
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemospringApplication.class)
@Rollback
@Transactional
@Slf4j
public class PersonServiceTest {
	@Autowired
	private PersonService personService;

	@Test
	public void addPerson(){
		Person person = new Person();
		String id = UUID.randomUUID().toString();
		person.setId(id);
		person.setName("jeb");
		Assert.assertTrue(personService.addPerson(person));

		Person serviceOne = personService.findOne(id);
		System.out.println("=======  "+serviceOne);
		Assert.assertNotNull(serviceOne);
		Assert.assertTrue(serviceOne.getName().equals("jeb"));
	}
}

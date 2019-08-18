package com.example.junitpersontest;

import com.example.demospring.DemospringApplication;
import com.example.demospring.module.user.domain.Person;
import com.example.demospring.module.user.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName：PersonServiceTest
 * @description：多线程事务
 * @author： huJb
 * @date：2019-08-18 11:42
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemospringApplication.class)
//@Rollback
//@Transactional
@Slf4j
public class PersonServiceTest2 {
	@Autowired
	private PersonService personService;

	private CountDownLatch latch = new CountDownLatch(5);
	private class ExecuteThread implements Runnable{

		private  String id;

		public ExecuteThread(String id) {
			this.id = id;
		}
		@Override
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Person person = new Person();

			String id = UUID.randomUUID().toString();
			person.setId(id);
			person.setName("jeb");
			Assert.assertTrue(personService.addPerson(person));

			Person serviceOne = personService.findOne(id);
			System.out.println("=======  "+serviceOne);
			Assert.assertTrue(serviceOne != null);
			Assert.assertTrue(serviceOne.getName().equals("jeb"));

//			System.out.println("===========================");
//			Person serviceOne = personService.findOne(id);
//
//			System.out.println("==========================="+serviceOne);
//			Assert.assertTrue(serviceOne != null);
		}
	}

	@Test
	public void test2() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new ExecuteThread("1"));
			t.start();
			latch.countDown();
		}
		Thread.currentThread().sleep(3000);
	}

	@After
	public void destory() throws Exception{
		personService.deleteByName("jeb");
	}

	/**
	 * @Test  注解只会启动一个主线程
	 */
	@Test
	public void addPerson(){
		Person person = new Person();
		String id = UUID.randomUUID().toString();
		person.setId(id);
		person.setName("jeb");
		Assert.assertTrue(personService.addPerson(person));

		Person serviceOne = personService.findOne(id);
		System.out.println("=======  "+serviceOne);
		Assert.assertTrue(serviceOne != null);
		Assert.assertTrue(serviceOne.getName().equals("jeb"));
	}


}

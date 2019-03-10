package com.example.demojdk8;

import com.example.Application;
import com.example.moudle.retry.Student;
import com.example.moudle.retry.StudetnAct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class Demojdk8ApplicationTests {

//	@Autowired
//	private StudetnAct studetnAct ;

//
//	@Test
//	public void contextLoads() throws Exception {
//
//		studetnAct.run(1);
//	}

	@Test
	public void contextLoads() throws Exception {
		Class classs = Class.forName("com.example.moudle.retry.Student");
		Student.class.getMethod("run",Integer.class);
		Method run = classs.getMethod("run", Integer.class);
		run.invoke(classs.newInstance(),1);

	}

}


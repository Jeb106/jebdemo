package com.example.junitpersontest;

import com.example.demospring.DemospringApplication;
import com.example.demospring.module.user.web.PersonController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
public class PersonServiceMockTest3 {

	@Autowired
	private PersonController personController;

	@Test
	public void test2() throws InterruptedException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("name", "jeb");
		HttpSession session = request.getSession();
		session.setAttribute("name", "jeb");
		Map<String, Object> result = personController.test(request);

		Assert.assertTrue(null != result);
		Assert.assertTrue("123".equalsIgnoreCase(result.get("returnCode")+""));
	}



}

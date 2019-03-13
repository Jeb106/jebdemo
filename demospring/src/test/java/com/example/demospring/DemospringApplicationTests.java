package com.example.demospring;

import com.example.demospring.module.springaop.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemospringApplicationTests {

    @Autowired
    private LoginService loginService;
    @Test
    public void contextLoads() {
        loginService.login();

    }

}

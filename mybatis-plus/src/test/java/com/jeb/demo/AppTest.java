package com.jeb.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jeb.demo.module.test.domain.User;
import com.jeb.demo.module.test.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest 
{
    @Autowired
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(new QueryWrapper<>());
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}

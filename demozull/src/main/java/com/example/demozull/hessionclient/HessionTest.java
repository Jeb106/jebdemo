package com.example.demozull.hessionclient;

import com.example.HessionApi.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名：HessionTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-17 22:25
 */
@RestController
@RequestMapping("/api/hession")
public class HessionTest {
    @Autowired
    HelloWorldService helloWorldService;
    @RequestMapping("/test")
    public String test(){
        return helloWorldService.sayHello("Spring boot with Hessian.");
    }
}

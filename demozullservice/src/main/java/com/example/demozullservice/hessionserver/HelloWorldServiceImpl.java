package com.example.demozullservice.hessionserver;

import com.example.HessionApi.HelloWorldService;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override    
    public String sayHello(String name) {
        return "Hello World! " + name;    
    }
}
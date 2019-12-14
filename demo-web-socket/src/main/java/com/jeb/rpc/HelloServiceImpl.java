package com.jeb.rpc;

public class HelloServiceImpl implements HelloService {
  
    public String hello(String name) {  
        return "Hello " + name;  
    }  
  
}  
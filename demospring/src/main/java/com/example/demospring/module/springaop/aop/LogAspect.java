package com.example.demospring.module.springaop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 文件名：LogAspect
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-12 09:59
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.example.demospring.module.springaop.service..*.*(..))")
    public void logAspect(){}

    @Before("logAspect()")
    public void before(){
        log.info("=======before advice======");
    }
    @After("logAspect()")
    public void after(){
        log.info("=======after advice======");
    }
    @AfterReturning("logAspect()")
    public void AfterReturning(){
        log.info("=======AfterReturning advice======");
    }
    @AfterThrowing("logAspect()")
    public void AfterThrowing(){
        log.info("=======AfterThrowing advice======");
    }

}

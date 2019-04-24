package com.example.demozull.service.impl;

import com.example.demozull.service.IPerson;
import org.springframework.context.annotation.Primary;

/**
 * 文件名：Student
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-19 14:19
 */
@Primary
public class Student implements IPerson {
    @Override
    public String say() {
        System.out.println("sutent");
        return "Student";
    }
}

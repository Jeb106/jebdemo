package com.example.demozull.service.impl;

import com.example.demozull.service.IPerson;

/**
 * 文件名：Student
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-19 14:19
 */
public class Teacher implements IPerson {
    @Override
    public String say() {
        System.out.println("Teacher");
        return "Teacher";
    }
}

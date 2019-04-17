package com.example.test.demo;

/**
 * 文件名：Test
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-16 18:09
 */
public class Test {
    public static void main(String[] args) throws  Exception {

        Class<?> aClass = Class.forName("ch.qos.logback.classic.db.DBAppender");
        System.out.println(aClass.getName());


    }
}

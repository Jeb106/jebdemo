package com.example.test.demo;

import org.springframework.beans.BeanUtils;

/**
 * 文件名：BeanCopyTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 08:39
 */
public class BeanCopyTest {
    public static void main(String[] args) {
        Persion p1 = new Persion();
        Persion p2 = new Persion();
        p2.setName("jeb2");
        p2.setAge(22);
        BeanUtils.copyProperties(p1,p2);
        System.out.println(p2.toString());
    }
}

package com.example.moudle.rmitest;

/**
 * 文件名：UserClient
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 23:12
 */
public class UserClient {
    public static void main(String[] args) throws Exception {
        User user = new User_Stub();
        int age = user.getAge();
        System.out.println(age);

    }
}

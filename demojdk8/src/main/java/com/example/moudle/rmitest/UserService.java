package com.example.moudle.rmitest;

/**
 * 文件名：UserService
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 23:04
 */
public class UserService extends User {
    public static void main(String[] args) {
        UserService userService  = new UserService();
        userService.setAge(18);

        User_Skeleton user_skeleton = new User_Skeleton(userService);
        user_skeleton.start();

    }
}

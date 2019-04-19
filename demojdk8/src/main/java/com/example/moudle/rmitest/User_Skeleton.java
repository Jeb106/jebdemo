package com.example.moudle.rmitest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件名：User_Skeleton
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 23:00
 */

public class User_Skeleton extends Thread{

    private UserService userService;

    public User_Skeleton(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
             serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            while (socket != null) {
                ObjectInputStream read = new ObjectInputStream(socket.getInputStream());
                String method = (String)read.readObject();
                if (method.equals("age")) {
                    int age = userService.getAge();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeInt(age);
                    objectOutputStream.flush();
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

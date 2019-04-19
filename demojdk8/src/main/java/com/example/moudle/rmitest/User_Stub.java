package com.example.moudle.rmitest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 文件名：User_Stub
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 23:00
 */
// User 可以认为是一个远程接口
public class User_Stub extends User {
    private Socket socket;

    public User_Stub() throws IOException {

        socket = new Socket("localhost", 8888);


    }

    public int getAge() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject("age");
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readInt();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }
}

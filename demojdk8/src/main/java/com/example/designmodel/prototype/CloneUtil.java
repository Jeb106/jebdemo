package com.example.designmodel.prototype;

import java.io.*;

/**
 * 文件名：CloneUtil
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 23:43
 */

/**
* 如何利用序列化来完成对象的拷贝呢？在内存中通过字节流的拷贝是比较容易实现的。把母对象写入到一个字节流中，
* 再从字节流中将其读出来，这样就可以创建一个新的对象了，并且该新对象与母对象之间并不存在引用共享的问题，真正实现对象的深拷贝。
 *
 * 所以使用该工具类的对象只要实现 Serializable 接口就可实现对象的克隆，无须继承 Cloneable 接口实现 clone() 方法。
 *
 * 使用该工具类的对象必须要实现 Serializable 接口，否则是没有办法实现克隆的。
* */
public class CloneUtil {

    public static <T extends Serializable>T clone(T t){
        T cloneObj = null;

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cloneObj = (T) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            objectOutputStream.close();
            objectOutputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return  cloneObj;

    }
}

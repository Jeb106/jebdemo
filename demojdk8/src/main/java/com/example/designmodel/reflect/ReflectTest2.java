package com.example.designmodel.reflect;

import com.example.designmodel.proxy.gupao.Jeb;

import java.lang.reflect.Method;

/**
 * 文件名：ReflectTest2
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-04 22:54
 */
public class ReflectTest2 {
    public static void main(String[] args) throws Exception {
        Class<Persion> jebClass = Persion.class;
        Method setName = jebClass.getMethod("setName", String.class);
        Persion p = (Persion) jebClass.newInstance();
        setName.invoke(p,"sss");
        System.out.println(p.getName());
    }
}

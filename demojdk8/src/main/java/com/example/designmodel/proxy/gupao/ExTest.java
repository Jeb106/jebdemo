package com.example.designmodel.proxy.gupao;

/**
 * 文件名：ExTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-03 23:26
 */
public class ExTest {
    public static void main(String[] args) {

        Jeb jeb = new Jeb();
        jeb.setName("jeb");
        jeb.setAge(11);
        ExMeipo exMeipo = new ExMeipo();
        Persion instance = (Persion)exMeipo.getInstance(jeb);
        instance.findLove();

    }
}

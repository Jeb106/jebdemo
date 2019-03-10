package com.example.designmodel.factory;

import java.sql.SQLOutput;

/**
 * 文件名：FactoryTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 23:11
 */

/**
 * 抽象工厂是简单工厂和工厂模式的结合
 */
public class FactoryTest {
    public static void main(String[] args) {
        DefaultCarFactory defaultCarFactory = new DefaultCarFactory();
        Car car = defaultCarFactory.getCar("Bmw");
        System.out.println(car.getName());
    }

}

package com.example.designmodel.factory;

/**
 * 文件名：AudiFactory
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 22:58
 */
public class AudiFactory extends  AbstractCarFactor{

    @Override
    protected Car getCar() {
        return new AudiCar();
    }
}

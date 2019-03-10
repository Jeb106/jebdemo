package com.example.designmodel.factory;

/**
 * 文件名：DefaultCarFactory
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 23:10
 */
public class DefaultCarFactory extends AbstractCarFactor {
    AudiFactory audiFactory = new AudiFactory();
    @Override
    protected Car getCar() {
        return audiFactory.getCar();
    }
}

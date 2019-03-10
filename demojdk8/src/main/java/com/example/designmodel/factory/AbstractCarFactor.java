package com.example.designmodel.factory;

/**
 * 文件名：AbstractCarFactor
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 22:57
 */
public abstract  class AbstractCarFactor {

    protected  abstract  Car getCar();

    public Car getCar(String name) {
        if (name.equals("Audi")) {
            return  new AudiFactory().getCar();
        } else if (name.equals("Bmw")) {
            return new BmwFactory().getCar();
        } else {
            System.out.println("型号不存在！");
        }
        return null;
    }
}

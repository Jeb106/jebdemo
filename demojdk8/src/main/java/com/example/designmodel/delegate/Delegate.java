package com.example.designmodel.delegate;

/**
 * 文件名：Delegate
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 22:44
 */
public class Delegate implements IExcuter {

    //委派具体到员工去做事
    //干活是我到 功劳是你的
    IExcuter iExcuter;
    public Delegate(IExcuter iExcuter) {
        this.iExcuter = iExcuter;
    }
    @Override
    public void doSomeing() {
        iExcuter.doSomeing();
    }
}

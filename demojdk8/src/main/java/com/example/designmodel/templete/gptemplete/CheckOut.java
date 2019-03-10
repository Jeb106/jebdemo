package com.example.designmodel.templete.gptemplete;

/**
 * 文件名：CheckOut
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 21:34
 */
public class CheckOut extends BankBusiness {
    public CheckOut() {
        System.out.println("==============子类初始化=================");
    }

    @Override

    protected void business() {
        System.out.println("=================取钱=================");
    }

    @Override
    protected void waiting() {
        System.out.println("=========子类等待=============");
    }
}

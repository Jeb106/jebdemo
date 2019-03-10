package com.example.designmodel.templete.gptemplete;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.waiting;

/**
 * 文件名：BankBusiness
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 21:19
 */
public abstract class BankBusiness {

    public BankBusiness() {
        System.out.println("================父类初始化============");
    }


    public void doBusiness() {
        getNum();
        waiting();
        business();
        go();
    }

    private void go() {
        System.out.println("=================go=============");
    }

    protected abstract void business();

    protected void getNum() {
        System.out.println("=================getNum=============");
    }

    protected void waiting() {
        System.out.println("=================waiting=============");
    }
}

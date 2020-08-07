package com.example.designmodel.chainpattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件名：CountDownLatchTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：责任链模式
 * 创建人： huJb
 * 创建时间：2019-04-24 22:10
 */

// 参考 https://www.jianshu.com/p/aeacde691cb0
@Slf4j
public class Test {
    public static void main(String[] args) {
        LeaveNote leaveNote = new LeaveNote("小李", "结婚", 15);
        DirectorHandler directorHandler = new DirectorHandler();
        ManagerHandler managerHandler  = new ManagerHandler();
        directorHandler.setNextHandler(managerHandler);
        directorHandler.handLeave(leaveNote);
        log.info(String.format("test:[%s],hi,%s",new Object[]{"hello","jeb"}));
    }
}

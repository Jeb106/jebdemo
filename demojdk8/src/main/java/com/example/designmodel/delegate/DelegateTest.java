package com.example.designmodel.delegate;

/**
 * 文件名：DelegateTest
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 22:46
 */

/**
 * 委派模式
 *
 * 类比：项目经理和普通员工
 * 项目经理：安排工作
 * 普通员工：执行任务
 *
 * 看上去好像是项目经理在干活
 * 实际上干活到是普通员工
 * 这就是典型到 干活是我到 功劳是你的
 *
 * 使用场景：IOC容器中 又一个register的东西（为了告诉我们的容器，在这个类被初始化的过程中，需要做很多不同的处理逻辑，需要实现多个执行者
 *  分别实现各自的功能  保证结果的多样性  对应用户只有一种方法）
 */
public class DelegateTest {
    public static void main(String[] args) {
        Delegate delegate = new Delegate(new ExecuteA());
        delegate.doSomeing();

    }
}

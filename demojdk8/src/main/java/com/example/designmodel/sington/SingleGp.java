package com.example.designmodel.sington;

/**
 * 文件名：SingleGp
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-05 21:58
 */
public class SingleGp {

    private SingleGp() {

    }
    public static final SingleGp getInstance() {

        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        private static final SingleGp INSTANCE = new SingleGp();
    }
}

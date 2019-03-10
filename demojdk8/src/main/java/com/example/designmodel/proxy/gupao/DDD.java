package com.example.designmodel.proxy.gupao;

import java.lang.reflect.Method;

/**
 * 文件名：DDD
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-04 22:41
 */



        import java.lang.reflect.Method;

        import com.example.designmodel.proxy.gupao.*;

public class DDD implements Persion {
    private ExInvocationHandler h;

    public DDD(ExInvocationHandler h) {
        this.h = h;
    }

    public void findLove() {
        try {
            Method m = Persion.class.getMethod("findLove", new Class[]{});
            this.h.invoke(this, m, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

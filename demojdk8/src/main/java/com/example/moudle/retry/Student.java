package com.example.moudle.retry;

import com.example.utils.Env;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 文件名：StudentClon
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 14:20
 */
@Component
public class Student implements IPersion, Serializable

{
    @Override
    public void run(Integer i) {
        StudetnAct bean = Env.getBean(StudetnAct.class);
        bean.run(i);

    }


}

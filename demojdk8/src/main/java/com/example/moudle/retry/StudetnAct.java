package com.example.moudle.retry;

import com.example.designmodel.proxy.gupao.Persion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * 文件名：StudetnAct
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 10:38
 */
@Component
@Slf4j
public class StudetnAct implements IPersion {
    private static int COUNTER = 0;
    @Retryable(value = {RetryException.class},
            maxAttempts = 4, backoff = @Backoff(2000))
    public void run(Integer i) {
        if (i == 1) {
            COUNTER++;
            log.info("COUNTER:"+COUNTER);
            throw new RetryException("=============异常==============");
        }

    }

    @Recover
    public void recover(RetryException e) {
        System.out.println("============recovery===============");
    }
}

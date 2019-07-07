package com.example.demojdk8.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件名：ExecuteTest3
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 15:35
 */
@Slf4j
public class ExecuteTest4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        //必须手动结束，不然不会自动结束
        executorService.shutdown();

        //类型转换
        Executor executor =  Executors.newFixedThreadPool(1);
        if (executor instanceof ExecutorService) {
            ExecutorService executorService1 = ExecutorService.class.cast(executor);
        }
    }




}

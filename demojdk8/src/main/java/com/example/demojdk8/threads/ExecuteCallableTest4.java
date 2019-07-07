package com.example.demojdk8.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 文件名：ExecuteTest3
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 15:35
 */
@Slf4j
public class ExecuteCallableTest4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Object> returns = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                return "hello";
                //无法处理异常
               // throw new RuntimeException("连接失败！");
            }
        });
        Object object = returns.get();
        System.out.println(object.toString());
        //必须手动结束，不然不会自动结束
        executorService.shutdown();


    }




}

package com.example.demojdk8.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 文件名：ExecuteTest3
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 15:35
 *
 * 抛出InterruptedException 异常基本都是阻塞的
 */
@Slf4j
public class ExecuteFuterTest4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Object> returns = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello";
            }
        });

        //等待完成
        while (true) {
            if (returns.isDone()) {
                break;
            }
        }

        //Futer#get 方法会阻塞当前线程
        Object object = returns.get();
        System.out.println(object.toString());
        System.out.println("test");
        //必须手动结束，不然不会自动结束
        executorService.shutdown();


    }




}

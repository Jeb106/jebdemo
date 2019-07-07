package com.example.demojdk8.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 文件名：ExecuteTest3
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-18 15:35
 */
@Slf4j
public class ExecuteTest3 {
    public static void main(String[] args) {
        ExecuteTest3 executeTest3 = new ExecuteTest3();
        executeTest3.execute();

    }

    public void execute() {
       ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

       //下面这个只能正常启动才能用
 //       ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
         //       new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("执行操作");
                for (int i=0;i<5;i++){

                    System.out.println("test:"+i);
                    TaskExcute.execute();
                }
            }
        }, 0, 5, TimeUnit.SECONDS);


        executorService.shutdown();

    }


}

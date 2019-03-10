package com.example.demoquartz.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SimpleScheduleTasks implements Job {
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//@Scheduled(cron = "*/10 * *  * * * ")
	public void reportCurrentTime() {
		System.out.println(Thread.currentThread().getName() + " ===> Scheduling Tasks Examples: The time is now " +
				sdf.format(new Date()));
	}

	//每10秒执行一次
	//@Scheduled(cron = "*/10 * *  * * * ")

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(Thread.currentThread().getName() + " ===> Scheduling Tasks Examples: The time is now " +
				sdf.format(new Date()));

		try {
			TimeUnit.SECONDS.sleep(10);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}

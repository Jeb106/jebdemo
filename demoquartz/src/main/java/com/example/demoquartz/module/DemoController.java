package com.example.demoquartz.module;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.dropwizard.DropwizardMetricServices;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jinBiaoHu
 * @date 2018-12-18 18:08
 */
@Slf4j
@RestController
@RequestMapping("/api/demo/")
public class DemoController {
	@Autowired
	private Scheduler scheduler;

	@Autowired
	private DropwizardMetricServices dropwizardMetricServices;
	@RequestMapping("test/")
	public void test(){
		dropwizardMetricServices.increment("meter.api.utils.test");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<JobExecutionContext> currentlyExecutingJobs = null;
		try {
			currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
			for (JobExecutionContext jobExecutionContext:currentlyExecutingJobs ) {
				JobKey key = jobExecutionContext.getJobDetail().getKey();
				log.info(key.getName());
			}
			TimeUnit.SECONDS.sleep(1);
			stopWatch.stop();

			System.out.println(stopWatch.getTotalTimeMillis());

		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Autowired
	private SyncTest syncTest;
	@RequestMapping("test2/")
	public Boolean test2(){
		System.out.println("runngin start");
		syncTest.syncTest();
	    System.out.println("runngin end");
	    return true;

	}

}

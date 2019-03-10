package com.example.demoquartz.mbean;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * @author jinBiaoHu
 * @date 2018-12-19 11:00
 */
@ManagedResource(objectName = "bean:name=QuartzRunningSize", description = "quartz正在运行的任务个数")
@Component
public class QuartzRunningSize {

	@Autowired
	private Scheduler scheduler;


	private int runningSize;

	@ManagedAttribute(description = "quartz正在运行的任务个数")
	public String getRunningTask() {
		try {
			runningSize = scheduler.getCurrentlyExecutingJobs().size();

		}
		catch (SchedulerException e) {
			e.printStackTrace();
		}
		return Integer.toString(runningSize);
	}

	@ManagedAttribute(description = "quartz group name")
	public String getGroupName() {
		try {

			return scheduler.getJobGroupNames().get(0);

		}
		catch (SchedulerException e) {
			e.printStackTrace();
		}
		return  "no quartz now!";
	}

}

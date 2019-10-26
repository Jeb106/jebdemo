package com.example.demoquartz.quartz.service.impl;

import com.example.demoquartz.quartz.SchedulerManager;
import com.example.demoquartz.quartz.dto.JobBean;
import com.example.demoquartz.quartz.service.QuartzService;
import com.example.demoquartz.task.SubscribeTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人：jinBiaoHu
 * 创建时间：2018-05-09 15:02
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {
	@Autowired
	private SchedulerManager schedulerManager;

	@Override
	public boolean addJobs() {

		// addJob( "* 0/5 * * * ?",SimpleScheduleTasks.class);

		return true;
	}
	private boolean addJob(String cron, Class jobClass) {
		SubscribeTask subscribeTask = new SubscribeTask();
		subscribeTask.setTaskId("22222");
		subscribeTask.setTaskName("taskName");

		try {
			JobBean jobBean = JobBean.builder().jobClazz(jobClass)
					.id(subscribeTask.getTaskId())
					.jobName(subscribeTask.getTaskId())
					.cronExpression(cron)
					.data(subscribeTask)
					.startTime(new Date())
					.endTime(null)
					.build();


				if(schedulerManager.jobExist(jobClass.getName(),jobClass)){
					schedulerManager.deleteJob(jobClass.getName(),jobClass);
					schedulerManager.addJobByCronSchedule(jobBean);
					log.debug("job已经存在:"+jobClass.getName()+",先删除,重新加入");
				}else{
					schedulerManager.addJobByCronSchedule(jobBean);
				}
		}
		catch (Exception e) {
			log.error("job添加失败,JobClass:"+jobClass.getName()+":"+e.getMessage());
			return false;
		}
		return true;
	}
}

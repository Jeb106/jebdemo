package com.example.demoquartz.listener;

import com.alibaba.fastjson.JSON;
import com.example.demoquartz.quartz.service.QuartzService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.servlet.ServletContextListener;
import java.util.List;

/*
* 版权：Copyright © Fable  Data Technology NanJing Co , Ltd.
* 描述：〈一句话功能简述〉
* 创建人：@author jinBiaoHu
* 创建时间：2018-01-03 14:17
*/
@Component
@Slf4j
public class TaskApplicationEventListener implements ApplicationListener {

	@Autowired
	private QuartzService quartzService;
	@Autowired
	private Scheduler scheduler;

	@PreDestroy
	public void test(){
		log.info("===========shutdown now !==================");
		List<JobExecutionContext> currentlyExecutingJobs = null;
		try {
			currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
			for (JobExecutionContext jobExecutionContext:currentlyExecutingJobs ) {
				JobKey key = jobExecutionContext.getJobDetail().getKey();
				log.info("jobKey:"+key.toString());
				log.info("date:"+JSON.toJSONString(jobExecutionContext.getJobDetail().getJobDataMap().get(key)));
			}
		}
		catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		/*log.info("+++++++++++++++++++++++++++++++++++++++++++++");
		if (event instanceof ContextStartedEvent){
			log.info("================:{}", "ContextStartedEvent");
		}
		if (event instanceof ContextRefreshedEvent){
			log.info("================:{}", "ContextRefreshedEvent");
		}
		if (event instanceof ContextClosedEvent){
			log.info("================:{}", "ContextClosedEvent");
		}
		if (event instanceof ContextStoppedEvent){
			log.info("================:{}", "ContextStoppedEvent");
		}
		if (event instanceof EmbeddedServletContainerInitializedEvent){
			log.info("================:{}", "EmbeddedServletContainerInitializedEvent");
		}
		if (event instanceof ApplicationReadyEvent){
			log.info("================:{}", "ApplicationReadyEvent");
		}
		log.info(">>>>>>>>>>>>>>>>:{}\n", event.getClass().getName());*/
		if (event instanceof ApplicationReadyEvent){
			quartzService.addJobs();
		}

	}

}
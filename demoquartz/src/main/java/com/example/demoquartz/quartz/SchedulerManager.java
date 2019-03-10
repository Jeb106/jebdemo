package com.example.demoquartz.quartz;

import com.example.demoquartz.quartz.dto.JobBean;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.spi.OperableTrigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 定时任务管理器,启动、关闭;
 *
 * @author by wolf on 2016/12/20.
 */
@Slf4j
public class SchedulerManager {

	private SchedulerFactoryBean schedulerFactoryBean;

	public SchedulerManager(SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}

	private List<JobBean> existJobs = new LinkedList<>();

	private Scheduler scheduler;

	@PostConstruct
	void init() {
		scheduler = schedulerFactoryBean.getScheduler();

		try {
			scheduler.start();
			log.info("schedule.start ok.");
		}
		catch (SchedulerException e) {
			log.error("scheduler start error", e);
		}
	}

	@PreDestroy
	void destroy() {
		try {
			scheduler.shutdown();
			log.debug("schedule.shutdown ok.");
		}
		catch (SchedulerException e) {
			log.error("scheduler shutdown error", e);
		}
	}

//	/**
//	 * add a job, add into scheduler.
//	 *
//	 * @param clazz          job type, and simpleName for jobGroupName.
//	 * @param jobName        job name.
//	 * @param cronExpression corn expression.
//	 */
//	public boolean addJob(Class<? extends Job> clazz, String jobName, String cronExpression) {
//
//		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobName, clazz.getSimpleName()).build();
//
//		// 触发器设置
//		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, clazz.getSimpleName())
//				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
//
//		return scheduleJobInternal(jobDetail, trigger);
//	}

	/**
	 * 自定义调试策略
	 *
	 * @param jobBean         jobBean
	 * @param scheduleBuilder scheduleBuilder
	 * @return boolean
	 */
	public boolean addJobByCustomerSchedule(JobBean jobBean, ScheduleBuilder scheduleBuilder) {
		return addJobInternal(jobBean, scheduleBuilder);
	}

	/**
	 * Cron模式，see CronScheduleBuilder.
	 *
	 * @param jobBean jobBean
	 * @return boolean
	 */
	public boolean addJobByCronSchedule(JobBean jobBean) {

		validateParam(jobBean);

		Assert.notNull(jobBean.getCronExpression(), "jobBean.cronExpression can`t null!");
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobBean.getCronExpression());

		return addJobInternal(jobBean, cronScheduleBuilder);
	}

	/**
	 * 简单定时模式，see SimpleScheduleBuilder.
	 *
	 * @param jobBean jobBean
	 * @return boolean
	 */
	public boolean addJobBySimpleSchedule(JobBean jobBean) {
		return addJobBySimpleSchedule(jobBean, JobBean.MisFirePolicy.SMART_POLICY);
	}

	/**
	 * 简单定时模式，指定Mis-Fire；see SimpleScheduleBuilder.
	 *
	 * @param jobBean       jobBean
	 * @param misFirePolicy misFirePolicy
	 * @return boolean
	 */
	public boolean addJobBySimpleSchedule(JobBean jobBean, JobBean.MisFirePolicy misFirePolicy) {

		validateParam(jobBean);

		SimpleScheduleBuilder simpleScheduleBuilder = getSimpleScheduleBuilder(jobBean, misFirePolicy);

		return addJobInternal(jobBean, simpleScheduleBuilder);
	}

	/**
	 * check job exist.
	 *
	 * @param jobName jobName
	 * @param clazz   clazz
	 * @return boolean
	 */
	public boolean jobExist(String jobName, Class<?> clazz) {
		try {
			return scheduler.checkExists(getJobKey(jobName, clazz));
		}
		catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * resume a job.
	 *
	 * @param jobName jobName
	 * @param clazz   clazz
	 * @return boolean
	 */
	public boolean resumeJob(String jobName, Class<?> clazz) {
		try {
			if (scheduler.checkExists(getJobKey(jobName, clazz))) {
				scheduler.resumeJob(getJobKey(jobName, clazz));
				log.info(String.format("job[%s] start!", jobName));
				return true;
			}
			return false;
		}
		catch (SchedulerException e) {
			log.error("start job error", e);
			return false;
		}
	}

	/**
	 * pause job,and update db;
	 *
	 * @param jobName jobName
	 * @param clazz   clazz
	 * @return boolean
	 */
	public boolean pauseJob(String jobName, Class<?> clazz) {
		try {
			if (scheduler.checkExists(getJobKey(jobName, clazz))) {
				scheduler.pauseJob(getJobKey(jobName, clazz));
				log.info(String.format("job[%s] paused!", jobName));
			}
			return true;//只要不异常,都认为成功。
		}
		catch (SchedulerException e) {
			log.error("pause job error", e);
			return false;
		}
	}

	/**
	 * delete job, delete from db, delete from scheduler.
	 *
	 * @param jobName jobName
	 * @param clazz   clazz
	 * @return boolean
	 */
	public boolean deleteJob(String jobName, Class<?> clazz) {
		return this.deleteJob(getJobKey(jobName, clazz));// 删除任务
	}

	public boolean deleteJob(JobKey jobKey) {
		try {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			scheduler.deleteJob(jobKey);// 删除任务
			log.info(String.format("job[%s] deleted!", jobKey.getName()));
			JobBean jobBean = (JobBean) jobDetail.getJobDataMap().get("bean");
			if (jobBean != null) {
				existJobs.remove(jobBean);
			}
			return true;
		}
		catch (SchedulerException e) {
			log.error("delete job error", e);
			return false;
		}
	}

	/**
	 * get jobDetail
	 *
	 * @param clazz   clazz
	 * @param jobName jobName
	 * @return JobDetail
	 * @throws SchedulerException excp
	 */
	public JobDetail getJobDetail(String jobName, Class<?> clazz) throws SchedulerException {
		return scheduler.getJobDetail(getJobKey(jobName, clazz));
	}

	/**
	 * get trigger.
	 *
	 * @param jobName name
	 * @param clazz   clazz
	 * @return Trigger
	 * @throws SchedulerException excp
	 */
	public Trigger getTrigger(String jobName, Class<?> clazz) throws SchedulerException {
		return scheduler.getTrigger(getTriggerKey(jobName, clazz.getSimpleName()));
	}

	/**
	 * @param jobName name
	 * @param clazz   clazz
	 * @return Date
	 * @throws SchedulerException excp
	 */
	public Date getNextFireTime(String jobName, Class<?> clazz) throws SchedulerException {
		return getTrigger(jobName, clazz).getNextFireTime();
	}

	/**
	 * 查询下一次执行时间
	 *
	 * @param jobBean
	 * @return
	 * @See getNextFireTimeByJobBean
	 */
	public Date getNextFireTimeByJobBean(JobBean jobBean) throws SchedulerException {
		return getNextFireTimeByJobBean(jobBean, JobBean.MisFirePolicy.SMART_POLICY);
	}

	/**
	 * 查询下一次执行时间-表达式模式，与指定的策略
	 *
	 * @param jobBean
	 * @param misFirePolicy
	 * @return
	 * @throws SchedulerException
	 * @See getNextFireTimeByJobBean
	 */
	public Date getNextFireTimeByCronJobBean(JobBean jobBean, JobBean.MisFirePolicy misFirePolicy) throws
			SchedulerException {
		return getNextFireTimeByJobBean(jobBean, misFirePolicy);
	}

	/**
	 * 查询下一次执行时间-Corn模式，与指定的策略
	 *
	 * @param jobBean
	 * @param misFirePolicy
	 * @return
	 * @throws SchedulerException
	 * @See getNextFireTimeByJobBean
	 */
	public Date getNextFireTimeBySimpleJobBean(JobBean jobBean, JobBean.MisFirePolicy misFirePolicy) throws
			SchedulerException {
		return getNextFireTimeByJobBean(jobBean, misFirePolicy);
	}


	/**
	 * 查询下一次执行时间
	 *
	 * @param jobBean
	 * @param misFirePolicy
	 * @return
	 */
	public Date getNextFireTimeByJobBean(JobBean jobBean, JobBean.MisFirePolicy misFirePolicy)
			throws SchedulerException {
		Assert.notNull(jobBean, "jobBean can`t null!");
		ScheduleBuilder scheduleBuilder = null;
		if (!StringUtils.isEmpty(jobBean.getCronExpression())) {
			scheduleBuilder = getCronScheduleBuilder(jobBean, misFirePolicy);
		}
		else if (jobBean.getIntervalUnit() != null && jobBean.getIntervalNumber() != null) {
			scheduleBuilder = getSimpleScheduleBuilder(jobBean, misFirePolicy);
		}
		else {
			throw new IllegalArgumentException("must has cronExpression or (intervalUnit and invervalNumber) param!");
		}

		Trigger trigger = TriggerBuilder.newTrigger()
				.startAt(jobBean.getStartTime())
				.endAt(jobBean.getEndTime())
				.withSchedule(scheduleBuilder).build();

		//method from QuartzScheduler.scheduleJob();
		Calendar cal = null;
		if (trigger.getCalendarName() != null) {
			cal = scheduler.getCalendar(trigger.getCalendarName());
		}
		OperableTrigger trig = (OperableTrigger) trigger;
		trig.computeFirstFireTime(cal);
		trig.updateAfterMisfire(cal);

		return trigger.getNextFireTime();
	}

	/**
	 * get exist jobBeans.
	 *
	 * @return
	 */
	public List<JobBean> getExistJobs() {
		List newList = new LinkedList();
		Collections.copy(newList, existJobs);
		return newList;
	}

	/*************************** private methods *********************/

	private SimpleScheduleBuilder getSimpleScheduleBuilder(JobBean jobBean, JobBean.MisFirePolicy misFirePolicy) {
		Assert.notNull(jobBean.getIntervalUnit(), "jobBean.intervalUnit can`t null!");
		Assert.notNull(jobBean.getIntervalNumber(), "jobBean.intervalNumber can`t null!");

		//触发时间点
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
		switch (jobBean.getIntervalUnit()) {
		case Hours:
			simpleScheduleBuilder.withIntervalInHours(jobBean.getIntervalNumber());
			break;
		case Minutes:
			simpleScheduleBuilder.withIntervalInMinutes(jobBean.getIntervalNumber());
			break;
		case Seconds:
			simpleScheduleBuilder.withIntervalInSeconds(jobBean.getIntervalNumber());
			break;
		}

		switch (misFirePolicy) {
		case SMART_POLICY:
			//default is: misfireInstruction = SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY;
			break;
		case IGNORE_MISFIRE_POLICY:
			simpleScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
			break;

		case SIMPLE_FIRE_NOW:
			simpleScheduleBuilder.withMisfireHandlingInstructionFireNow();
			break;
		case SIMPLE_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT:
			simpleScheduleBuilder.withMisfireHandlingInstructionNowWithExistingCount();
			break;
		case SIMPLE_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT:
			simpleScheduleBuilder.withMisfireHandlingInstructionNowWithRemainingCount();
			break;
		case SIMPLE_RESCHEDULE_NEXT_WITH_EXISTING_COUNT:
			simpleScheduleBuilder.withMisfireHandlingInstructionNextWithExistingCount();
			break;
		case SIMPLE_RESCHEDULE_NEXT_WITH_REMAINING_COUNT:
			simpleScheduleBuilder.withMisfireHandlingInstructionNextWithRemainingCount();
			break;
		default:
			throw new IllegalArgumentException(misFirePolicy + "can`t match for SimpleScheduleBuilder");
		}

		simpleScheduleBuilder.repeatForever();
		return simpleScheduleBuilder;
	}

	private ScheduleBuilder getCronScheduleBuilder(JobBean jobBean, JobBean.MisFirePolicy misFirePolicy) {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobBean.getCronExpression());
		switch (misFirePolicy) {
		case SMART_POLICY:
			//default is :CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY
			break;
		case IGNORE_MISFIRE_POLICY:
			scheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
			break;

		case CORN_DO_NOTHING:
			scheduleBuilder.withMisfireHandlingInstructionDoNothing();
			break;
		case CORN_FIRE_ONCE_NOW:
			scheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
			break;
		default:
			throw new IllegalArgumentException(misFirePolicy + "can`t match for CronScheduleBuilder");
		}

		return scheduleBuilder;
	}

	//添加并执行任务
	private boolean addJobInternal(JobBean jobBean, ScheduleBuilder scheduleBuilder) {
		// 触发器设置
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(getTriggerKey(jobBean.getJobName(), jobBean.getJobClazzName()))
				.startAt(jobBean.getStartTime())
				.endAt(jobBean.getEndTime())
				.withSchedule(scheduleBuilder).build();

		JobDetail jobDetail = JobBuilder.newJob(jobBean.getJobClazz())
				.withIdentity(jobBean.getJobName(), jobBean.getJobClazzName())
				.build();
		jobDetail.getJobDataMap().put("bean", jobBean);
		jobDetail.getJobDataMap().put("data", jobBean.getData());

		boolean ret = scheduleJobInternal(jobDetail, trigger);
		if (ret) {
			existJobs.add(jobBean);
		}
		return ret;
	}

	//加入任务调试器
	private boolean scheduleJobInternal(JobDetail jobDetail, Trigger trigger) {
		try {
			if (scheduler.checkExists(jobDetail.getKey())) {
				log.info(String.format("job[%s] exist, add failure!", jobDetail.getKey()));
				return false;
			}
			else {
				//加入任务调试器
				scheduler.scheduleJob(jobDetail, trigger);
				log.info(String.format("job[%s] add schedule success!", jobDetail.getKey()));
				return true;
			}
		}
		catch (SchedulerException e) {
			log.error("schedule job error", e);
			return false;
		}
	}

	//验证参数
	private void validateParam(JobBean jobBean) {
		Assert.notNull(jobBean, "jobBean can`t null!");
		Assert.notNull(jobBean.getJobClazz(), "jobBean.jobClazz can`t null!");
		Assert.notNull(jobBean.getJobName(), "jobBean.jobName can`t null!");
	}

	private TriggerKey getTriggerKey(String name, String simpleJobClazzName) {
		return TriggerKey.triggerKey(name, simpleJobClazzName);
	}

	private JobKey getJobKey(String jobName, Class<?> clazz) {
		return JobKey.jobKey(jobName, clazz.getSimpleName());
	}

}

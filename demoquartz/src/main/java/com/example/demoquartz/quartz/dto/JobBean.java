package com.example.demoquartz.quartz.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by wolf on 2016/12/20.
 */
@Builder
@Data
public class JobBean implements Serializable{
	private String id;

	private String jobName;

	private Class jobClazz;

	private String cronExpression;

	private boolean isStart;

	private Object data;

	private Date startTime;

	private Date endTime;

	private IntervalUnit intervalUnit;

	private Integer intervalNumber;

	private Integer repeatCount;

	public String getJobClazzName() {
		if (jobClazz != null) {
			return jobClazz.getSimpleName();
		}
		else {
			return null;
		}
	}

	public enum IntervalUnit {
		Seconds, Minutes, Hours
	}

	public enum MisFirePolicy {
		SMART_POLICY,//默认的，智能策略，常用。
		IGNORE_MISFIRE_POLICY,//忽略MIS-FIRE策略，会补充执行缺失的任务

		//for SimpleSchedule
		SIMPLE_FIRE_NOW,//立即执行
		SIMPLE_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT,//立即执行，exist repeat count.
		SIMPLE_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT,//立即执行，remaining repeat count
		SIMPLE_RESCHEDULE_NEXT_WITH_REMAINING_COUNT,//计算下时间，和重复次数
		SIMPLE_RESCHEDULE_NEXT_WITH_EXISTING_COUNT,//计算下次时间 ，和重复次数

		//for CornSchedule
		CORN_DO_NOTHING,
		CORN_FIRE_ONCE_NOW
	}

}

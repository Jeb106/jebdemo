package com.example.demoquartz.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.dropwizard.DropwizardMetricServices;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;

/**
 * 监控管理类
 *
 * @author by wolf on 2018/11/29.
 */
@Slf4j
@EnableScheduling
public class MetricsEsbManager {

	private final Field gaugesField;

	private MetricRegistry metricRegistry;

	private DropwizardMetricServices dropwizardMetricServices;

	private ConcurrentMap gauges;

	public MetricsEsbManager(MetricRegistry metricRegistry,
			DropwizardMetricServices dropwizardMetricServices) {
		this.metricRegistry = metricRegistry;
		this.dropwizardMetricServices = dropwizardMetricServices;

		// 设置可读属性，为了清理过期数据
		this.gaugesField = ReflectionUtils.findField(DropwizardMetricServices.class, "gauges");
		Assert.notNull(this.gaugesField,
				"DropwizardMetricServices.gauges field not found");
		this.gaugesField.setAccessible(true);
		gauges = (ConcurrentMap) ReflectionUtils.getField(this.gaugesField, dropwizardMetricServices);
	}

	/**
	 * 接口每分钟的访问量
	 * 接口每分钟的请求大小-todo
	 * 接口每分钟的响应大小-todo
	 * 计算思路：每分钟统计下当前的访问量，下分钟会被清理掉，被采集到上一分钟的统计数值
	 */
	@Scheduled(cron = "${dssg.esb.metric.job.per.minute:0 * * * * ?}")
	public void accessPerMinute() {
		log.debug("access per minute");
		SortedMap<String, Gauge> gauges = metricRegistry.getGauges();
		for (String key : gauges.keySet()) {
			//重置之前的统计数据
			if (key.startsWith("gauge.apm.")) {
				dropwizardMetricServices.submit(key, 0);
			}
		}

		// 统计前一分钟的接口访问量
		SortedMap<String, Counter> counters = metricRegistry.getCounters();
		Iterator<Map.Entry<String, Counter>> it = counters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Counter> entry = it.next();
			if (entry.getKey().startsWith("counter.call.")) {
				String key = entry.getKey().replaceFirst("counter.", "gauge.apm.");
				// gauge.　
				dropwizardMetricServices.submit(key, entry.getValue().getCount());
//				log.debug("gauge:{}, value:{}", key, entry.getValue().getCount());
				// 清理前段的数据.
				dropwizardMetricServices.reset(entry.getKey());
//				log.debug("reset:{}, value:{}", entry.getKey(), entry.getValue().getCount());
			}
		}
	}

	/**
	 * 凌晨整点，清理之前的所有数据
	 */
	@Scheduled(cron = "${dssg.esb.metric.job.clean.daily:1 0 0 * * ?}")
	public void cleanMetricsDaily() {
		log.info("begin clean metrics daily--->");

//		metricRegistry.getCounters();
//		metricRegistry.getGauges();
//		metricRegistry.getHistograms();
//		metricRegistry.getMeters();
//		metricRegistry.getTimers();

		SortedSet<String> names = metricRegistry.getNames();
		for (String name : names) {
			metricRegistry.remove(name);
			gauges.remove(name);
			log.debug("remove metric:{}", name);
		}

		log.info("finished clean metrics daily.");
	}

}

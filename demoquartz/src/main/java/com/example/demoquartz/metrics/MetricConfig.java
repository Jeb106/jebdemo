package com.example.demoquartz.metrics;

import com.codahale.metrics.MetricRegistry;
import com.readytalk.metrics.StatsDReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author by wolf on 2017/7/5.
 */
@EnableMetrics
@Configuration
@ConditionalOnProperty(value = "spring.metrics.export.enabled", matchIfMissing = true)
@EnableConfigurationProperties(value = { MetricExportProperties.class })
public class MetricConfig {

	@Autowired
	private MetricExportProperties metricExportProperties;

	@Bean
	@ConditionalOnMissingBean
	public MetricRegistry metricRegistry() {
		return new MetricRegistry();
	}

	@Autowired
	public void registryStatsDReporter(MetricRegistry metricRegistry) {
		// Using metrics3-statsd
		if (!StringUtils.isEmpty(metricExportProperties.getStatsd().getHost())) {
			StatsDReporter.forRegistry(metricRegistry)
					.prefixedWith(metricExportProperties.getStatsd().getPrefix())
					.build(metricExportProperties.getStatsd().getHost(),
							metricExportProperties.getStatsd().getPort())
					.start(metricExportProperties.getDelayMillis(), TimeUnit.MILLISECONDS);
		}
	}

//	/**
//	 * 自定义单位
//	 *
//	 * @param metrics
//	 * @return
//	 */
//	@Bean
//	public ListManager listManager(MetricRegistry metrics) {
//		return new ListManager(metrics);
//	}

//	/**
//	 * TPS 计算器
//	 *
//	 * @param metrics
//	 * @return
//	 */
//	@Bean
//	public Meter requestMeter(MetricRegistry metrics) {
//		return metrics.meter("request");
//	}
//
//	/**
//	 * 直方图
//	 *
//	 * @param metrics
//	 * @return
//	 */
//	@Bean
//	public Histogram responseSizes(MetricRegistry metrics) {
//		return metrics.histogram("response-sizes");
//	}
//
//	/**
//	 * 计数器
//	 *
//	 * @param metrics
//	 * @return
//	 */
//	@Bean
//	public Counter pendingJobs(MetricRegistry metrics) {
//		return metrics.counter("requestCount");
//	}
//
//	/**
//	 * 计时器
//	 *
//	 * @param metrics
//	 * @return
//	 */
//	@Bean
//	public Timer responses(MetricRegistry metrics) {
//		return metrics.timer("executeTime");
//	}

}

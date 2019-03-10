package com.example.demoquartz.metrics;

import com.codahale.metrics.MetricRegistry;
import org.springframework.boot.actuate.autoconfigure.MetricsDropwizardAutoConfiguration;
import org.springframework.boot.actuate.metrics.dropwizard.DropwizardMetricServices;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by wolf on 2018/11/22.
 */
@Configuration
@AutoConfigureAfter(value = { MetricsDropwizardAutoConfiguration.class })
public class MetricsAutoConfiguration {

	private MetricRegistry metricRegistry;


	private DropwizardMetricServices dropwizardMetricServices;

	public MetricsAutoConfiguration(
			DropwizardMetricServices dropwizardMetricServices,
			MetricRegistry metricRegistry) {
		this.dropwizardMetricServices = dropwizardMetricServices;
		this.metricRegistry = metricRegistry;
	}

	@Bean
	public MetricsEsbManager metricsManager() {
		return new MetricsEsbManager(metricRegistry, dropwizardMetricServices);
	}
}

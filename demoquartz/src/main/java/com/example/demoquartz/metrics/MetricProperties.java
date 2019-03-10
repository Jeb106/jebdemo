package com.example.demoquartz.metrics;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * 配置属性
 *
 * @author by wolf on 2018/11/22.
 */
@Data
@ConfigurationProperties(prefix = "dssg.esb.metric")
public class MetricProperties {

	@NotNull
	@Value("${spring.application.name:application}")
	private String keyPrefix;

	private boolean enabled;

	private boolean metricRequestEnabled;

	private boolean metricResponseEnabled;

}

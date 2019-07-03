package com.jeb.springcloudconfigclient.demo;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @ClassName：MyHealthIndicator
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-30 14:56
 */
public class MyHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		builder.up().withDetail("MyHealthIndicator", "Day Up");
	}
}

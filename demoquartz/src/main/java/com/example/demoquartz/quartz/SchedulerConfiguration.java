package com.example.demoquartz.quartz;

import com.example.demoquartz.config.Env;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author by wolf on 2016/12/20.
 */
@Configuration
@ConditionalOnClass(TransactionProxyFactoryBean.class)
public class SchedulerConfiguration {


	@Bean
	@ConditionalOnMissingBean(value = { SchedulerFactoryBean.class })
	public SchedulerFactoryBean schedulerClusterFactory(Env env, DataSource dataSource) {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		String value = Env.getProperty("org.quartz.jobStore.class");
		if (StringUtils.hasText(value)) {
			Properties quartzProps = env.getPrefixProperties("org.quartz");
			bean.setDataSource(dataSource);
			bean.setQuartzProperties(quartzProps);
		}
		return bean;
	}

	@Bean
	public SchedulerManager schedulerManager(SchedulerFactoryBean schedulerFactory) {
		return new SchedulerManager(schedulerFactory);
	}

}

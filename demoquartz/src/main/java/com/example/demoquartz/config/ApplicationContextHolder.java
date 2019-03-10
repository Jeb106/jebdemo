package com.example.demoquartz.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

/**
 * .
 *
 * @author stormning on 2016/12/23.
 */
public class ApplicationContextHolder implements ApplicationContextAware {

	private static org.springframework.context.ApplicationContext applicationContext;

	ApplicationContextHolder() {
	}

	public static org.springframework.context.ApplicationContext get() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

}

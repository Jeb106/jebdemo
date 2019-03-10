package com.example.demoquartz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author by wolf on 2017/8/21.
 */
public class Env {

	private static ServiceIdProvider serviceIdProvider;

	private static Env INSTANCE;

	private Environment environment;

	protected Env(Environment environment) {
		this.environment = environment;
		INSTANCE = this;
	}

	private static ApplicationContext getApplicationContext() {
		return ApplicationContextHolder.get();
	}

	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	public static Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	@Autowired(required = false)
	public void init(ServiceIdProvider serviceIdProvider) {
		Env.serviceIdProvider = serviceIdProvider;
	}

	public static String getCurrentServiceId() {
		return serviceIdProvider == null ?
				getEnviroment().getProperty("spring.application.name") :
				serviceIdProvider.getCurrentServiceId();
	}

	public static int getServerPort() {
		return NumberUtils.parseNumber(getEnviroment().getProperty("server.port"), Integer.class);
	}

	//获取user.dir的环境变量，eg: /*/your application folder/
	public static String getUserDir() {
		return getEnviroment().getProperty("user.dir");
	}

	public static String getProperty(String key) {
		return getEnviroment().getProperty(key);
	}

	private static Environment getEnviroment() {
		Assert.notNull(INSTANCE, "Env don`t instanced!");
		return INSTANCE.environment;
	}

	/**
	 * 获取所有属性集合
	 * @return
	 */
	public Properties getAllProperties() {
//		Map<String, Object> map = new HashMap();
		Properties properties = new Properties();
		for (Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof MapPropertySource) {
				//get all properties.
//				properties.putAll(((MapPropertySource) propertySource).getSource());

				//get property for each, get ultimate value.
				for (String key : ((MapPropertySource) propertySource).getPropertyNames()) {
					properties.put(key, environment.getProperty(key));
				}
			}
		}

		return properties;
	}

	/**
	 * 获取指定前缀的属性集合
	 *
	 * @param prefix
	 * @return
	 */
	public Properties getPrefixProperties(String prefix) {
		Assert.hasText(prefix, "prefix can`t be null");

		Properties prefixProps = new Properties();
		Properties properties = getAllProperties();
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (key.startsWith(prefix)) {
				prefixProps.put(key, properties.get(key));
			}
		}
		return prefixProps;
	}

}

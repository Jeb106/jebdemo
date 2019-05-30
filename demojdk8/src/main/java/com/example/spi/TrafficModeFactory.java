package com.example.spi;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName：TrafficModeFactory
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-05-27 22:33
 */
@Component
public class TrafficModeFactory implements ApplicationContextAware {

	private static Map<TrafficCode, TrafficMode> trafficModeMap;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Map<String, TrafficMode> map = applicationContext.getBeansOfType(TrafficMode.class);
		trafficModeMap = new HashMap<>();
		map.forEach((key,value)->trafficModeMap.put(value.getTrafficCode(),value));
	}

	public static <T extends TrafficMode> T getTrafficMod(TrafficCode trafficCode) {
		return (T)trafficModeMap.get(trafficCode);
	}
}

package com.example.spi;

import org.springframework.stereotype.Component;

/**
 * @ClassName：TrainMode
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-05-27 22:31
 */
@Component
public class BusMode implements TrafficMode {
	/**
	 * 获取交换同时
	 *
	 * @return
	 */
	@Override
	public TrafficCode getTrafficCode() {
		return TrafficCode.BUS;
	}

	/**
	 * 费用
	 *
	 * @return
	 */
	@Override
	public Integer getFee() {
		return 1000;
	}
}

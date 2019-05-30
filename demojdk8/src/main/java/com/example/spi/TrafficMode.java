package com.example.spi;

/**
 * @ClassName：TrafficMode
 * @description：交通方式接口 有多个实现类
 * @author： huJb
 * @date：2019-05-27 22:26
 */
public interface TrafficMode {
	/**
	 * 获取交换同时
	 * @return
	 */
	TrafficCode getTrafficCode();

	/**
	 * 费用
	 * @return
	 */
	Integer getFee();
}

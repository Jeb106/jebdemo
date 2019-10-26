package com.example.demoquartz.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName：TaskExecute
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-10-10 16:14
 */
@Component
@Slf4j
public class TaskExecute {

	public static TaskExecute instance;

	public TaskExecute() {
		instance = this;
	}

	@Autowired
	private SyncTest syncTest;



	public void syncTest() {
		log.info("start======================"+Thread.currentThread().getName());
		syncTest.syncTest();
		//SyncTest.instance.syncTest();
		log.info("end======================"+Thread.currentThread().getName());
	}


}

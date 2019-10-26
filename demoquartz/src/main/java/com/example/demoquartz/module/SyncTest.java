package com.example.demoquartz.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author jinBiaoHu
 * @date 2018-12-20 15:11
 */
@Slf4j
@Component
public class SyncTest {

	public static SyncTest instance;

	public SyncTest() {
		instance = this;
	}

	@Async
	public void syncTest() {

		log.info("sleep start:"+Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(5);
			log.info("sleep end:"+Thread.currentThread().getName());
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

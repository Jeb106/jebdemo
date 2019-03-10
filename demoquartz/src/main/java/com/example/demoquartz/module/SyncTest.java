package com.example.demoquartz.module;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author jinBiaoHu
 * @date 2018-12-20 15:11
 */
@Component
public class SyncTest {

	@Async
	public void syncTest() {
		System.out.println("sleep start");
		try {
			TimeUnit.SECONDS.sleep(10);
			System.out.println("sleep end");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

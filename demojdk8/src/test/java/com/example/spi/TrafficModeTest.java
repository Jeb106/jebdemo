package com.example.spi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName：TrafficModeTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-05-27 22:45
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrafficModeTest {


    @Test
	public void testTrafficMode(){
		TrafficMode mode = TrafficModeFactory.getTrafficMod(TrafficCode.BUS);
		Assert.assertEquals(mode.getFee().intValue(),1000);

		TrafficMode train = TrafficModeFactory.getTrafficMod(TrafficCode.TRAIN);
		Assert.assertEquals(train.getFee().intValue(),900);

	}
}
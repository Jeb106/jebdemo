package com.example.demothread;

import org.springframework.util.StringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jinBiaoHu
 * @date 2019-01-29 22:09
 */
//生产者
class ProductThread extends  Thread{

	BlockingQueue<String> blockingQueue;
	public ProductThread(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	boolean flag = true;
	AtomicInteger atomicInteger = new AtomicInteger();

	@Override
	public void run() {
		try {
			while (flag) {
				String data = atomicInteger.getAndIncrement() + "";
				boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
				if (offer) {
					System.out.println("ProductThread,生成成功:"+data);
				}else{
					System.out.println("ProductThread,生成失败:"+data);
				}
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stopThread(){
		flag = false;
	}
}

class ConsumerThread extends Thread{
	BlockingQueue<String> blockingQueue;
	public ConsumerThread(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	boolean flag = true;
	@Override
	public void run() {
		try {
			while (flag) {
				String poll = blockingQueue.poll(2, TimeUnit.SECONDS);
				if (StringUtils.isEmpty(poll)) {
					flag = false;
					System.out.println("ConsumerThread,获取数据失败,结束线程");
					return;
				}
				System.out.println("ConsumerThread,获取数据:"+poll);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class BlockQueue {
	public static void main(String[] args) {

		BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
		ConsumerThread consumerThread = new ConsumerThread(blockingQueue);
		ProductThread productThread = new ProductThread(blockingQueue);
		Thread p1 = new Thread(productThread);
		Thread c1 = new Thread(consumerThread);
		p1.start();
		c1.start();
		try {
			Thread.sleep(1000 * 10);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		productThread.stopThread();
	}
}

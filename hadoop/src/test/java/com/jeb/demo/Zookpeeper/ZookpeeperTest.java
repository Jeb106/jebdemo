package com.jeb.demo.Zookpeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName：ZookpeeperTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-07-05 21:42
 */
public class ZookpeeperTest {
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	String connectionStr = "hadoop0:2181";
	ZooKeeper zooKeeper = null;

	@Test
	public void ls() throws KeeperException, InterruptedException {
		List<String> children = zooKeeper.getChildren("/idea", false);
		System.out.println(children);
	}
	@Test
	public void lsAndWatch() throws KeeperException, InterruptedException {
		List<String> children = zooKeeper.getChildren("/idea", new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("path:" + watchedEvent.getPath() + "发生了变化" + watchedEvent.getType());
				try {
					List<String> children = zooKeeper.getChildren("/idea", null);
					System.out.println(children);
					countDownLatch.countDown();
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} {

				}
			}
		});
		System.out.println(children);

		countDownLatch.await();
	}

	@Test
	public void create() throws KeeperException, InterruptedException {
		String s = zooKeeper.create("/curatorLock", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		String s = zooKeeper.create("/idea", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(s);
	}

	@Test
	public void get() throws KeeperException, InterruptedException {
		Stat stat = new Stat();
		byte[] data = zooKeeper.getData("/idea", null, stat);
		System.out.println(new String(data));
		System.out.println(stat.getVersion());
	}


	@Test
	public void set() throws KeeperException, InterruptedException {
		zooKeeper.setData("/idea","helloNew".getBytes(),1);
	}



	// 判断当前节点是否存在
	@Test
	public void ifNodeExists() throws Exception {

		Stat stat = zooKeeper.exists("/idea", false);

		System.out.println(stat==null ? "不存在" : "存在");

	}

	@Test
	public void rmr() throws Exception {

		String path="/idea";

		//先获取当前路径中所有的子node
		List<String> children = zooKeeper.getChildren(path, false);

		//删除所有的子节点
		for (String child : children) {

			zooKeeper.delete(path+"/"+child, -1);

		}

		zooKeeper.delete(path, -1);

	}

	@Test
	public void del() throws KeeperException, InterruptedException {
		zooKeeper.delete("/idea",-1);
	}




	@Before
	public void  init() throws IOException {
		 zooKeeper = new ZooKeeper(connectionStr, 5000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				//System.out.println("公共回掉函数");
			}
		});
	}

	@Test
	public void test() throws IOException, KeeperException, InterruptedException {

//		System.out.println(zooKeeper);
//		System.out.println(zooKeeper.getState());
		Stat stat = new Stat();
//		List<String> children = zooKeeper.getChildren("/", true);
//		System.out.println("child:"+children);

		Watcher watcher = new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {

				System.out.println("stat:"+watchedEvent.getState());
				System.out.println("path:"+watchedEvent.getPath());
				System.out.println("wrapper:"+watchedEvent.getWrapper());
			}
		};
		byte[] zooKeeperData = zooKeeper.getData("/test", watcher, stat);
		System.out.println(zooKeeperData.toString());
		System.out.println("stat:"+stat);
	}


	@After
	public void  afterTest() throws InterruptedException {
		zooKeeper.close();
	}
}

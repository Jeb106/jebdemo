package com.example.demonetty.rpc;

/**
 * 服务消费者
 * @author jinBiaoHu
 * @date 2018-12-26 18:13
 */
public class RpcConsumer {
	public static void main(String[] args) throws Exception {
		HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
		for (int i = 0; i < Integer.MAX_VALUE; i ++) {
			String hello = service.hello("World" + i);
			System.out.println(hello);
			Thread.sleep(1000);
		}
	}
}

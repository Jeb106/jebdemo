package com.example.demonetty.rpc;

/**
 * 服务生产者
 * @author jinBiaoHu
 * @date 2018-12-26 18:13
 */
public class RpcProvider {

	public static void main(String[] args) throws Exception {
		HelloService service = new HelloServiceImpl();
		RpcFramework.export(service, 1234);
	}
}

package com.example.designmodel.proxy.gupao;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jinBiaoHu
 * @date 2019-02-23 23:20
 */
public class Test {
	public static void main(String[] args) throws IOException {
		Jeb jeb = new Jeb();
		jeb.setName("jeb");
		jeb.setAge(11);
		ProxyClass proxyClass = new ProxyClass();
		Persion proxy  = (Persion)proxyClass.getInstance(jeb);
		proxy.findLove();

		//获取字节码
		byte[] proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class[] { Persion.class });
		FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
		fileOutputStream.write(proxy0s);
	}
}

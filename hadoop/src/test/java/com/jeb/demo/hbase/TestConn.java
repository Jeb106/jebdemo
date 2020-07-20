package com.jeb.demo.hbase;

import org.junit.Test;

import java.io.IOException;

public class TestConn {

	@Test
	public void test() throws IOException {
		
		System.out.println(ConnectionUtil.getConn());
		
	}

}

package com.jeb.demo.hbase;

import org.apache.hadoop.hbase.client.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestDataUtil {
	
	private  Connection conn;
	
	@Before
	public void init() throws IOException {
		
		conn=ConnectionUtil.getConn();
		
	}
	
	@After
	public void close() throws IOException {
		
		ConnectionUtil.close(conn);
		
	}

	@Test
	public void testPut() throws IOException {
		
		DataUtil.put(conn, "t1", null, "b1", "cf1", "name", "jack");
		
	}
	
	@Test
	public void testGet() throws IOException {
		
		DataUtil.get(conn, "t1", null, "r1");
		
	}
	
	@Test
	public void testScan() throws IOException {
		
		DataUtil.scan(conn, "ga_src2", null);
		
	}
	
	@Test
	public void testDelete() throws IOException {
		
		DataUtil.delete(conn, "t1", null,"a1");
		
	}
	
	
	

}

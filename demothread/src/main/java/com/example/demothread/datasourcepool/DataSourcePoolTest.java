package com.example.demothread.datasourcepool;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.PoolableConnection;

/**
 * @author jinBiaoHu
 * @date 2019-02-16 21:27
 */
public class DataSourcePoolTest {
	public static void main(String[] args) {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setMaxActive(3);

	}
}

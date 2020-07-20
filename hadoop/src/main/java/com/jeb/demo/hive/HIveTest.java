package com.jeb.demo.hive;

import java.sql.*;

/**
 * @ClassName：HIveTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-07-06 18:19
 */
public class HIveTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//加载驱动
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection connection = DriverManager.getConnection("jdbc:hive2://hadoop0:10000", "hujb", "hujb");
		String sql = "select * from person2";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			System.out.println("name:"+resultSet.getString("name"));
			System.out.println("age:"+resultSet.getString("age"));
		}

	}
}

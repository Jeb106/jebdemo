package com.example.demospring.module.springaop.multipledatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName：DataSourceRepository
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-16 16:18
 */
@Repository
public class DataSourceRepository {

	@Autowired
	private DataSource masterDataSource;

	@Autowired
	private DataSource slaveDataSource;


	public boolean addUser(User user){
		try {
			Connection connectionMaster = masterDataSource.getConnection();
			Connection connectionSlave = slaveDataSource.getConnection();

			PreparedStatement preparedStatementMaster = connectionMaster.prepareStatement("insert into user  values(?,?)");
			PreparedStatement preparedStatementSlave = connectionSlave.prepareStatement("insert into user  values(?,?)");
			preparedStatementMaster.setString(1,"4");
			preparedStatementMaster.setString(2,"jeb");

			preparedStatementSlave.setString(1,"5");
			preparedStatementSlave.setString(2,"tom");


			int r1 = preparedStatementMaster.executeUpdate();
			int r2 = preparedStatementSlave.executeUpdate();
			System.out.println(r1+"_"+r2);



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}

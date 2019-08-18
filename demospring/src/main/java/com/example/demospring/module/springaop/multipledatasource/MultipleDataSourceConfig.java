//package com.example.demospring.module.springaop.multipledatasource;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * @ClassName：MultipleDataSourceConfig
// * @description：多数据配置
// * @author： huJb
// * @date：2019-06-16 15:53
// */
//
//@Configuration
//public class MultipleDataSourceConfig {
//
//	@Bean
//	public DataSource masterDataSource() {
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		DataSource dataSource = dataSourceBuilder
//				.driverClassName("com.mysql.jdbc.Driver")
//				.username("root")
//				.password("root")
//				.url("jdbc:mysql://10.211.55.3:3306/dssg_master")
//				.build();
//		return  dataSource;
//	}
//
//	@Bean
//	public DataSource slaveDataSource() {
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		DataSource dataSource = dataSourceBuilder
//				.driverClassName("com.mysql.jdbc.Driver")
//				.username("root")
//				.password("root")
//				.url("jdbc:mysql://10.211.55.3:3306/dssg_slave")
//				.build();
//		return  dataSource;
//	}
//}

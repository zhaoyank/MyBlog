package com.kaishengit.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {

	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	private static BasicDataSource dataSource = new BasicDataSource();

	static {
		DRIVER = Config.get("jdbc.driver");
		URL = Config.get("jdbc.url");
		USERNAME = Config.get("jdbc.username");
		PASSWORD = Config.get("jdbc.password");

		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);

		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(15);
		dataSource.setMinIdle(5);
		dataSource.setMaxWaitMillis(5);
	}

	public static DataSource getDataSource() {
		return dataSource;
	}
}

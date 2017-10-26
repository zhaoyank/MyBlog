package com.kaishengit.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.exception.DataAccessException;

public class DbHelp {

	// private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	
	public static void update(String sql, Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());

		try {
			runner.update(sql, params);
			// logger.info("执行sql:{}", sql);
		} catch (SQLException e) {
			// logger.error("执行{}异常," + "\n" + "error:{}"+ "\n", sql, e.getMessage());
			throw new DataAccessException("执行" + sql + "异常");
		}
	}

	public static int insert(String sql, Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		int id = 0;

		try {
			id = runner.insert(sql, new ScalarHandler<Long>(), params).intValue();
			// logger.info("执行sql:{}", sql);
		} catch (SQLException e) {
			// logger.error("执行{}异常," + "\n" + "error:{}"+ "\n", sql, e.getMessage());
			throw new DataAccessException("执行" + sql + "异常");
		}
		return id;
	}

	public static <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		T t = null;
		try {
			t = runner.query(sql, rsh, params);
			// logger.info("执行sql:{}", sql);
		} catch (SQLException e) {
			// logger.error("执行{}异常," + "\n" + "error:{}"+ "\n", sql, e.getMessage());
			throw new DataAccessException("执行" + sql + "异常");
		}
		return t;
	}

}

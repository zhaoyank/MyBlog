package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.kaishengit.entity.Notify;
import com.kaishengit.util.DbHelp;

public class NotifyDao {

	public static int count() {
		String sql = "select count(*) from t_notify";
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public static List<Notify> findByPage(int startNo, int pageSize) {
		String sql = "select * from t_notify order by state asc, createTime desc limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Notify.class), startNo, pageSize);
	}

	public static List<Notify> findUnread() {
		String sql = "select * from t_notify where state = 0";
		return DbHelp.query(sql, new BeanListHandler<>(Notify.class));
	}

	public static void save(Notify notify) {
		String sql = "insert into t_notify (content,state) value (?,?)";
		DbHelp.update(sql, notify.getContent(), notify.getState());
	}

	public static Notify findById(String id) {
		String sql = "select * from t_notify where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Notify.class), id);
	}

	public static void update(Notify notify) {
		String sql = "update t_notify set state = ?, readTime = ? where id = ?";
		DbHelp.update(sql, notify.getState(), notify.getReadTime(), notify.getId());
	}

	public static void delReadedNotify() {
		String sql = "delete from t_notify where state = 1";
		DbHelp.update(sql);
	}

}

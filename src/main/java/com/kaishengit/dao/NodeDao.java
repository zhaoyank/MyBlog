package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Node;
import com.kaishengit.util.DbHelp;

public class NodeDao {

	public static List<Node> getAll() {
		String sql = "select * from t_node";
		return DbHelp.query(sql, new BeanListHandler<>(Node.class));
	}

	public static Node findById(int nodeId) {
		String sql = "select * from t_node where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Node.class), nodeId);
	}

	public static List<Node> findByKey(String key, int startNo, int pageSize) {
		String sql = "select * from t_node";
		if(StringUtils.isNotEmpty(key)) {
			sql += " where nodeName like ? limit ?,?";
			key = "%" + key + "%";
			return DbHelp.query(sql, new BeanListHandler<>(Node.class),key, startNo, pageSize);
		}
		sql += " limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Node.class), startNo, pageSize);
	}

	public static int count(String key) {
		String sql = "select count(*) from t_node";
		if(StringUtils.isNoneEmpty(key)) {
			sql += " where nodeName like ?";
			key = "%" + key + "%";
			return DbHelp.query(sql, new ScalarHandler<Long>(), key).intValue();
		}
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public static Node findByName(String nodeName) {
		String sql = "select * from t_node where nodeName = ?";
		return DbHelp.query(sql, new BeanHandler<>(Node.class), nodeName);
	}

	public static void save(Node node) {
		String sql = "insert into t_node (nodeName) values (?)";
		DbHelp.update(sql, node.getNodeName());
	}

	public static void update(String nodeName, String id) {
		String sql = "update t_node set nodeName = ? where id = ?";
		DbHelp.update(sql, nodeName, id);
	}

	public static void delNodeById(String id) {
		String sql = "delete from t_node where id = ?";
		DbHelp.update(sql, id);
	}

}

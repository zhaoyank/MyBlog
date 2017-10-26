package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Reply;
import com.kaishengit.util.DbHelp;

public class ReplyDao {

	public static List<Reply> findByArticleId(int articleId) {
		String sql = "select * from t_reply where articleId = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class), articleId);
	}

	public static int save(Reply reply) {
		String sql = "insert into t_reply (articleId, pid, content, simpleContent, userIp) values (?,?,?,?,?)";
		return DbHelp.insert(sql, reply.getArticleId(), reply.getPid(), reply.getContent(), reply.getSimpleContent(), reply.getUserIp());
	}

	public static List<Reply> findAll() {
		String sql = "select * from t_reply";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class));
	}

	public static List<Reply> findByParams(String keys, int startNo, int pageSize) {
		String sql = "select r.id,r.content,r.simpleContent,r.userIp,r.createTime,r.articleId from t_reply r";
		if(StringUtils.isNotEmpty(keys)) {
			sql += ", t_article a where r.articleId = a.id and a.title like ? limit ?,?";
			keys = "%" + keys + "%";
			return DbHelp.query(sql, new BeanListHandler<>(Reply.class),keys, startNo, pageSize);
		}
		sql += " order by r.createTime desc limit ?,?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class), startNo, pageSize);
	}

	public static int countByParams(String keys) {
		String sql = "select count(*) from t_reply r";
		if(StringUtils.isNotEmpty(keys)) {
			sql += ", t_article a where r.articleId = a.id and a.title like ?";
			keys = "%" + keys + "%";
			return DbHelp.query(sql, new ScalarHandler<Long>(), keys).intValue();
		}
		return DbHelp.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public static Reply findById(String id) {
		String sql = "select * from t_reply where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Reply.class), id);
	}

	public static void del(Reply reply) {
		String sql = "delete from t_reply where id = ?";
		DbHelp.update(sql, reply.getId());
	}

	public static void delByArticleId(int articleId) {
		String sql = "delete from t_reply where articleId = ?";
		DbHelp.update(sql, articleId);
	}

	public static void update(Reply reply) {
		String sql = "update t_reply set content = ? where id = ?";
		DbHelp.update(sql, reply.getContent(), reply.getId());
	}

	public static List<Reply> findByPid(int pid) {
		String sql = "select * from t_reply where pid = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Reply.class), pid);
	}

}

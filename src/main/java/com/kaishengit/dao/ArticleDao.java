package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Article;
import com.kaishengit.util.DbHelp;

public class ArticleDao {

	public static int save(Article article) {
		String sql = "insert into t_article (title,nodeId,content,simpleContent,picture) values (?,?,?,?,?)";
		return DbHelp.insert(sql, article.getTitle(), article.getNodeId(), article.getContent(), article.getSimpleContent(), article.getPicture());
	}

	public static Article findById(int articleId) {
		String sql = "select * from t_article where id = ?";
		return DbHelp.query(sql, new BeanHandler<>(Article.class), articleId);
	}

	public static void update(Article article) {
		String sql = "update t_article set title = ?, nodeId = ?, content = ?, simpleContent = ?, picture = ?, replyNum = ?, scanNum = ?, lastReplyTime = ? where id = ?";
		DbHelp.update(sql, article.getTitle(), article.getNodeId(), article.getContent(), article.getSimpleContent(), article.getPicture(), article.getReplyNum(),
				article.getScanNum(), article.getLastReplyTime(), article.getId());
	}

	public static int countByParams(String nodeId, String labelId, String keys) {
		String sql = "select count(*) from t_article a ";

		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(labelId) && StringUtils.isNumeric(labelId)) {
			sql += ",t_article_label al where a.id = al.aid and al.lid = ?";
			params.add(labelId);
		} else if (StringUtils.isNotEmpty(nodeId) && StringUtils.isNumeric(nodeId)) {
			sql += "where nodeId = ?";
			params.add(nodeId);
		} else if (StringUtils.isNotEmpty(keys)) {
			sql += "where title like ?";
			keys = "%" + keys + "%";
			params.add(keys);
		}
		return DbHelp.query(sql, new ScalarHandler<Long>(), params.toArray()).intValue();
	}

	public static List<Article> findByParams(String nodeId, String labelId, String keys, int startNo, int pageSize) {
		String sql = "select * from t_article a ";

		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotEmpty(labelId) && StringUtils.isNumeric(labelId)) {
			sql += ",t_article_label al where a.id = al.aid and al.lid = ?";
			params.add(labelId);
		} else if (StringUtils.isNotEmpty(nodeId) && StringUtils.isNumeric(nodeId)) {
			sql += "where nodeId = ?";
			params.add(nodeId);
		} else if (StringUtils.isNotEmpty(keys)) {
			sql += "where title like ?";
			keys = "%" + keys + "%";
			params.add(keys);
		}

		sql += " order by postTime desc limit ?,?";
		params.add(startNo);
		params.add(pageSize);

		return DbHelp.query(sql, new BeanListHandler<>(Article.class), params.toArray());
	}

	public static List<Article> findByScanNum() {
		String sql = "select * from t_article order by scanNum desc limit 0,5";
		return DbHelp.query(sql, new BeanListHandler<>(Article.class));
	}

	public static List<Article> findByNodeId(String nodeId) {
		String sql = "select * from t_article where nodeId = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Article.class), nodeId);
	}

	public static void delById(int articleId) {
		String sql = "delete from t_article where id = ?";
		DbHelp.update(sql, articleId);
	}

}

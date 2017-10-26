package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Label;
import com.kaishengit.util.DbHelp;

public class LabelDao {

	public static Label findByName(String labelName) {
		String sql = "select * from t_label where labelName = ?";
		return DbHelp.query(sql, new BeanHandler<>(Label.class), labelName);
	}

	public static int save(Label label) {
		String sql = "insert into t_label (labelName) values (?)";
		return DbHelp.insert(sql, label.getLabelName());
	}

	public static List<Label> findByArticleId(String articleid) {
		String sql = "select l.id , l.labelName from t_label l , t_article_label al where l.id = al.lid and aid = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Label.class), articleid);
	}
	
}

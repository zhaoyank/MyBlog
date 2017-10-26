package com.kaishengit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Label;
import com.kaishengit.util.DbHelp;

public class ArticleLabelDao {

	public static void save(int aid, List<Integer> lids) {
		String sql = "insert into t_article_label (aid, lid) values";
		List<Integer> params = new ArrayList<>();

		for (Integer lid : lids) {
			sql += "(?,?),";
			params.add(aid);
			params.add(lid);
		}

		sql = sql.substring(0, sql.length() - 1);
		DbHelp.update(sql, params.toArray());
	}

	public static List<Label> findByAid(int aid) {
		String sql = "select id,labelName from t_label l,t_article_label al where l.id = al.lid and al.aid = ?";
		return DbHelp.query(sql, new BeanListHandler<>(Label.class), aid);
	}

	public static void delByAid(int id) {
		String sql = "delete from t_article_label where aid = ?";
		DbHelp.update(sql, id);
	}

	public static void patchAdd(int articleId, List<Integer> idList) {
		String sql = "insert into t_article_label (aid,lid) values";
		List<Integer> params = new ArrayList<>();
		for(Integer id : idList) {
			sql += "(?,?),";
			params.add(articleId);
			params.add(id);
		}
		sql = sql.substring(0,sql.length()-1);
		
		DbHelp.update(sql, params.toArray());
	}

}

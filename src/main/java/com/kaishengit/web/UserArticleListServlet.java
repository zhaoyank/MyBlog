package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Article;
import com.kaishengit.entity.Node;
import com.kaishengit.service.ArticleService;
import com.kaishengit.util.Page;

@WebServlet("/user/article/list")
public class UserArticleListServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		String nodeId = req.getParameter("nodeId");
		String labelId = req.getParameter("labelId");
		String keys = req.getParameter("keys");

		if (StringUtils.isNotEmpty(keys)) {
			keys = new String(keys.getBytes("ISO8859-1"), "UTF-8");
		}

		Page<Article> page = ArticleService.fingByParams(p, nodeId, labelId, keys);
		List<Node> nodeList = ArticleService.findAllNode();
		List<Article> scanSort = ArticleService.findByScanNum();

		req.setAttribute("page", page);
		req.setAttribute("nodeList", nodeList);
		req.setAttribute("scanSort", scanSort);

		req.setAttribute("keys", keys);
		forward("user/article_list", req, resp);
	}

}

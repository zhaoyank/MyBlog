package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Node;
import com.kaishengit.entity.ResultJson;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ArticleService;

@WebServlet("/admin/article/add")
public class AdminAticleAddServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Node> nodeList = ArticleService.findAllNode();
		req.setAttribute("nodeList", nodeList);
		forward("admin/article_add", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String title = req.getParameter("title");
		String labels = req.getParameter("label");
		String content = req.getParameter("content");
		String nodeId = req.getParameter("node");

		try {
			int id = ArticleService.saveArticle(title, labels, content, nodeId);
			ResultJson resultJson = new ResultJson(id);

			sendJson(resultJson, resp);
		} catch (ServiceException e) {
			ResultJson resultJson = new ResultJson(e.getMessage());
			sendJson(resultJson, resp);
		}

	}

}

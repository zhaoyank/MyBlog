package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Article;
import com.kaishengit.entity.Label;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.ResultJson;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ArticleService;

@WebServlet("/admin/article/edit")
public class AdminArticleEditServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		Article article = ArticleService.findById(id);
		List<Label> labelList = ArticleService.findLabelListByArticle(id);
		List<Node> nodeList = ArticleService.findAllNode();
		
		req.setAttribute("article", article);
		req.setAttribute("nodeList", nodeList);
		req.setAttribute("labelList", labelList);
		
		forward("admin/article_edit", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String labelNames = req.getParameter("label");
		String content = req.getParameter("content");
		String nodeId = req.getParameter("nodeId");
		ResultJson result = null;
		try{
			ArticleService.editAticle(id,title, labelNames, content, nodeId);
			result = new ResultJson(Integer.parseInt(id));
			sendJson(result,resp);
		} catch (ServiceException e) {
			e.printStackTrace();
			result = new ResultJson(e.getMessage());
			sendJson(result,resp);
		} 
	
	}
	
}

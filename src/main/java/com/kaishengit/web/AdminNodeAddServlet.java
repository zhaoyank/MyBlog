package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Node;
import com.kaishengit.service.ArticleService;

@WebServlet("/admin/node/add")
public class AdminNodeAddServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nodeName = req.getParameter("nodename");
		
		Node node = new Node();
		node.setNodeName(nodeName);
		
		ArticleService.saveNode(node);
		resp.sendRedirect("/admin/node/list");
		
	}
	
}

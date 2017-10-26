package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Node;
import com.kaishengit.service.ArticleService;
import com.kaishengit.util.Page;

@WebServlet("/admin/node/list")
public class AdminNodeListServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String p = req.getParameter("p");
		String key = req.getParameter("key");
		
		if(StringUtils.isNotEmpty(key)) {
			key= new String(key.getBytes("ISO8859-1"),"UTF-8");
		}
		
		Page<Node> page = ArticleService.fingNodeByParams(p, key);
		
		req.setAttribute("key", key);
		req.setAttribute("page", page);
		forward("admin/manage-node", req, resp);
		
	}

}

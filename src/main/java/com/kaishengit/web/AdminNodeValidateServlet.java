package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.service.ArticleService;

@WebServlet("/admin/node/validate")
public class AdminNodeValidateServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nodeName = req.getParameter("nodename");
		if(StringUtils.isNotEmpty(nodeName)) {
			nodeName = new String(nodeName.getBytes("ISO8859-1"),"UTF-8");
		}
		String res = ArticleService.validateNodeName(nodeName);
		sendText(res, resp);
	}
}

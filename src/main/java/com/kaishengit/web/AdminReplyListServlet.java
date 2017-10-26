package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Reply;
import com.kaishengit.service.ArticleService;
import com.kaishengit.util.Page;

@WebServlet("/admin/reply/list")
public class AdminReplyListServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String p = req.getParameter("p");
		String keys = req.getParameter("keys");
		
		if(StringUtils.isNotEmpty(keys)) {
			keys = new String(keys.getBytes("ISO8859-1"), "UTF-8");
		}
		
		Page<Reply> page = ArticleService.findReplyByParams(p, keys);

		req.setAttribute("page", page);
		req.setAttribute("keys", keys);
		forward("admin/manage-commentary", req, resp);
	}

}

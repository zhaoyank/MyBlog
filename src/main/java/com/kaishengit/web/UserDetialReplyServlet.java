package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ArticleService;

@WebServlet("/user/reply")
public class UserDetialReplyServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String articleId = req.getParameter("articleId");
		String replyId = req.getParameter("replyId");
		String content = req.getParameter("editor");
		String userIp = req.getRemoteAddr();

		try {
			int id = ArticleService.saveReply(articleId, replyId, content, userIp);

			resp.sendRedirect("/user/article/detail?id=" + articleId + "#" + id);
		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		}

	}

}

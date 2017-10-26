package com.kaishengit.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Article;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Reply;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ArticleService;
import com.kaishengit.vo.ArticleDetailVo;

@WebServlet("/user/article/detail")
public class UserArticleDetailServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String articleId = req.getParameter("id");

		try {
			Article article = ArticleService.findById(articleId);
			List<Node> nodeList = ArticleService.findAllNode();
			List<Reply> replyList = ArticleService.findReplysByArticleId(article.getId());
			String nodeName = ArticleService.findNodeById(article.getNodeId()).getNodeName();

			ArticleDetailVo vo = new ArticleDetailVo(article, nodeList, replyList, nodeName);

			req.setAttribute("detailVo", vo);
			req.setAttribute("nodeList", nodeList);
			forward("user/article_detail", req, resp);

		} catch (ServiceException e) {
			resp.sendError(404, e.getMessage());
		}

	}

}

package com.kaishengit.vo;

import java.util.List;

import com.kaishengit.entity.Article;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Reply;

public class ArticleDetailVo {

	private Article article;
	private List<Node> nodeList;
	private List<Reply> replyList;
	private String nodeName;

	public ArticleDetailVo() {

	}

	public ArticleDetailVo(Article article, List<Node> nodeList, List<Reply> replyList, String nodeName) {
		this.article = article;
		this.nodeList = nodeList;
		this.replyList = replyList;
		this.nodeName = nodeName;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
}

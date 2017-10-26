package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kaishengit.dao.ArticleDao;
import com.kaishengit.dao.ArticleLabelDao;
import com.kaishengit.dao.LabelDao;
import com.kaishengit.dao.NodeDao;
import com.kaishengit.dao.NotifyDao;
import com.kaishengit.dao.ReplyDao;
import com.kaishengit.entity.Article;
import com.kaishengit.entity.Label;
import com.kaishengit.entity.Node;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.Reply;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.EhcacheUtils;
import com.kaishengit.util.Page;

public class ArticleService {

	public static int saveArticle(String title, String labels, String content, String nodeId) {
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		article.setNodeId(Integer.parseInt(nodeId));

		Document doc = Jsoup.parseBodyFragment(content);
		String simpleContent = doc.select("p").first().toString();
		
		Element picElement = doc.select("img").first();
		if(picElement != null) {
			picElement.attr("width", "128px");
			picElement.attr("height", "128px");
			String picture = picElement.toString();
			
			article.setPicture(picture);
		}
		
		article.setSimpleContent(simpleContent);
		
		int aid = ArticleDao.save(article);

		String[] labelNames = labels.split(",");
		List<Integer> lids = new ArrayList<>();

		for (String labelName : labelNames) {
			Label label = LabelDao.findByName(labelName);
			if (label != null) {
				lids.add(label.getId());
			} else {
				label = new Label();
				label.setLabelName(labelName);

				lids.add(LabelDao.save(label));
			}
		}

		ArticleLabelDao.save(aid, lids);

		return aid;
	}

	public static Article findById(String articleId) throws ServiceException {
		if (StringUtils.isNumeric(articleId)) {
			Article article = ArticleDao.findById(Integer.parseInt(articleId));
			if (article != null) {
				article.setScanNum(article.getScanNum() + 1);
				ArticleDao.update(article);
				return article;
			} else {
				throw new ServiceException("该文章不存在或已被删除");
			}
		} else {
			throw new ServiceException("参数异常");
		}
	}

	public static List<Node> findAllNode() {
		List<Node> nodeList = (List<Node>) EhcacheUtils.get("node", "nodeList");
		if(nodeList == null) {
			nodeList = NodeDao.getAll();
			EhcacheUtils.set("node", "nodeList", nodeList);
		}
		return nodeList;
	}

	public static List<Reply> findReplysByArticleId(int articleId) {
		return ReplyDao.findByArticleId(articleId);
	}

	public static Node findNodeById(int nodeId) {
		Node node = (Node)EhcacheUtils.get("node", nodeId);
		if(node == null) {
			node = NodeDao.findById(nodeId);
			EhcacheUtils.set("node", nodeId, node);
		}
		return node;
	}

	public static int saveReply(String articleId, String replyId, String content, String userIp) throws ServiceException {
		int id = 0;
		if (StringUtils.isNumeric(articleId)) {
			Article article = ArticleDao.findById(Integer.parseInt(articleId));
			if (article != null) {
				Reply reply = new Reply();
				reply.setArticleId(Integer.parseInt(articleId));
				reply.setContent(content);
				
				Document doc = Jsoup.parseBodyFragment(content);
				Element element = doc.select("p").first();
				if(element.text().length() > 20) {
					reply.setSimpleContent(element.text().substring(0, 20) + "...");
				} else {
					reply.setSimpleContent(element.text());
				}
				
				reply.setUserIp(userIp);
				
				if(StringUtils.isNumeric(replyId)) {
					reply.setPid(Integer.parseInt(replyId));
				}
				id = ReplyDao.save(reply);

				Notify notify = new Notify();
				notify.setContent("您的文章<a href=\"/user/article/detail?id=" + articleId + "\">" + article.getTitle() + "</a>有一条新的评论,<a href=\"javascript:;\" class=\"toread\" rel=\"/user/article/detail?id=" + articleId + "#" + id + "\">请查阅</a>");
				NotifyDao.save(notify);
				
				article.setReplyNum(article.getReplyNum() + 1);
				article.setLastReplyTime(new Timestamp(System.currentTimeMillis()));

				ArticleDao.update(article);
				return id;
			} else {
				throw new ServiceException("该文章不存在或已被删除");
			}

		} else {
			throw new ServiceException("参数异常");
		}
		
	}

	public static Page<Article> fingByParams(String p, String nodeId, String labelId, String keys) {
		int pageNo = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;
		
		int countTotal = ArticleDao.countByParams(nodeId, labelId, keys);
		
		Page<Article> page = new Page<>(pageNo, countTotal);
		
		List<Article> articleList = ArticleDao.findByParams(nodeId, labelId, keys, page.getStartNo(),page.getPageSize());
		for (Article art : articleList) {
			Node node = NodeDao.findById(art.getNodeId());
			List<Label> labelList = ArticleLabelDao.findByAid(art.getId());
			art.setNode(node);
			art.setLabelList(labelList);
		}

		page.setItems(articleList);

		return page;
	}

	public static List<Article> findByScanNum() {
		return ArticleDao.findByScanNum();
	}

	public static Page<Node> fingNodeByParams(String p, String key) {
		int pageNo = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;
		
		int countTotal = NodeDao.count(key);
		
		Page<Node> page = new Page<>(pageNo, countTotal);
		
		List<Node> nodeList = NodeDao.findByKey(key, page.getStartNo(),page.getPageSize());

		page.setItems(nodeList);

		return page;
	}

	public static String validateNodeName(String nodeName) {
		Node node = NodeDao.findByName(nodeName);
		if(node != null) {
			return "false";
		}
		return "true";
	}

	public static void saveNode(Node node) {
		NodeDao.save(node);
		EhcacheUtils.removeAll("node");
	}

	public static void updateNode(String nodeName, String id) {
		if(StringUtils.isNumeric(id)) {
			Node node = findNodeById(Integer.parseInt(id));
			if(node == null) {
				throw new ServiceException("该节点不存在");
			} else {
				NodeDao.update(nodeName, id);
				EhcacheUtils.removeAll("node");
			}
		} else {
			throw new ServiceException("参数异常");
		}
	}

	public static void delNodeById(String id) {
		if(StringUtils.isNumeric(id)){
			List<Article> articleList = ArticleDao.findByNodeId(id);
			if(articleList != null && articleList.size() > 0) {
				throw new ServiceException("该节点下有文章，不能删除");
			}
			NodeDao.delNodeById(id);
			EhcacheUtils.removeAll("node");
			
 		} else {
 			throw new ServiceException("参数异常");
 		}
	}

	public static void editAticle(String id, String title, String labelNames, String content, String nodeId) {
		if(StringUtils.isNumeric(nodeId) && StringUtils.isNumeric(id)) {
			
			Article article = new Article();
			article.setId(Integer.parseInt(id));
			article.setTitle(title);
			article.setContent(content);
			article.setNodeId(Integer.parseInt(nodeId));
			
			Document doc = Jsoup.parseBodyFragment(content);
			String simpleContent = doc.select("p").first().toString();
			
			Element picElement = doc.select("img").first();
			if(picElement != null) {
				picElement.attr("width", "128px");
				picElement.attr("height", "128px");
				String picture = picElement.toString();
				
				article.setPicture(picture);
			}
			
			article.setSimpleContent(simpleContent);
			
			ArticleDao.update(article);
			
			ArticleLabelDao.delByAid(article.getId());
			
			String[] names = labelNames.split(",");
			
			List<Integer> idList = new ArrayList<>();
			for(String name : names) {
				Label label = LabelDao.findByName(name);
				if(label != null) {
					idList.add(label.getId());
				} else {
					label = new Label();
					label.setLabelName(name);
					int labelId = LabelDao.save(label);
					idList.add(labelId);
				}
				
			}
			ArticleLabelDao.patchAdd(article.getId(), idList);
			
		} else {
			throw new ServiceException("参数异常");
		}
	}

	public static List<Label> findLabelListByArticle(String id) {
		return LabelDao.findByArticleId(id);
	}

	public static Page<Reply> findReplyByParams(String p, String keys) {
		int pageNo = StringUtils.isNumeric(p) ? Integer.parseInt(p) : 1;
		
		int countTotal = ReplyDao.countByParams(keys);
		Page<Reply> page = new Page<>(pageNo, countTotal);
		
		List<Reply> replyList = ReplyDao.findByParams(keys, page.getStartNo(), page.getPageSize());
		if(replyList != null && replyList.size() > 0) {
			for(Reply reply : replyList) {
				Article article = ArticleDao.findById(reply.getArticleId());
				reply.setArticleTitle(article.getTitle());
			}
			page.setItems(replyList);
		}
		
		return page;
	}

	public static void delReplyById(String id) {
		
		if(StringUtils.isNumeric(id)) {
			Reply reply = ReplyDao.findById(id);
			
			if(reply != null) {
				List<Reply> reReplyList = ReplyDao.findByPid(reply.getId());
				if(reReplyList.size() == 0) {
					ReplyDao.del(reply);
					Article article = ArticleDao.findById(reply.getArticleId());
					
					article.setReplyNum(article.getReplyNum() - 1);
					ArticleDao.update(article);
				} else {
					reply.setContent("<p><span style=\"color:rgb(227,55,55);\">该评论已被删除</span></p>");
					ReplyDao.update(reply);
				}
			} else {
				throw new ServiceException("参数异常");
			}
		} else {
			throw new ServiceException("参数异常");
		}
		
	}

	public static void delArticleById(String id) {

		if(StringUtils.isNumeric(id)){
			int articleId = Integer.parseInt(id);
			
			List<Reply> replyList = ReplyDao.findByArticleId(articleId);
			if(replyList.size() > 0 ){
				ReplyDao.delByArticleId(articleId);
			}
			ArticleDao.delById(articleId);
 		} else {
 			throw new ServiceException("参数异常");
 		}
		
	}

	public static void delReadedNotify() {
		NotifyDao.delReadedNotify();
	}

}

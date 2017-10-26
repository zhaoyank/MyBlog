/*package com.kaishengit.service;

import org.junit.Assert;
import org.junit.Test;

import com.kaishengit.entity.Article;
import com.kaishengit.entity.Node;
import com.kaishengit.util.EhcacheUtils;


public class ArticleServiceTestCase {

	@Test
	public void findByIdTest() {
		Article article = ArticleService.findById("22");
		EhcacheUtils.set("article", "22", article);
		
		Article art = (Article)EhcacheUtils.get("article", "22");
		Assert.assertNotNull(art);
	}
	
	@Test
	public void findNodeByIdTest() {
		Node node = ArticleService.findNodeById(7);
		
		Node node2 = ArticleService.findNodeById(7);
		
		ArticleService.updateNode(node.getNodeName(), node.getId()+"");
		Node node1 = ArticleService.findNodeById(7);
		
		Assert.assertNotNull(node2);
		Assert.assertNotNull(node1);
	}
	
}
*/
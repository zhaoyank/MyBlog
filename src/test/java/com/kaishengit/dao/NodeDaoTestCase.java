/*package com.kaishengit.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.kaishengit.entity.Node;

public class NodeDaoTestCase {

	@Test
	public void saveTest() {
		Node node = new Node();
		node.setNodeName("JavaME");
		
		NodeDao.save(node);
	}
	
	@Test
	public void findByIdTest() {
		Node node = NodeDao.findById(2);
		Assert.assertNotNull(node);
	}
	
	@Test
	public void findByKeyTest() {
		List<Node> nodeList = NodeDao.findByKey("java", 0, 5);
		Assert.assertNotEquals(0, nodeList.size());
	}
	
}*/

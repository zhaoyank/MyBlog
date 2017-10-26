package com.kaishengit.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nodeName;
	private Timestamp createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}

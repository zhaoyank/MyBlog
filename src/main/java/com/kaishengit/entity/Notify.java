package com.kaishengit.entity;

import java.sql.Timestamp;

public class Notify {

	public static final boolean READ_STATE = true;
	public static final boolean UNREAD_STATE = false;
	
	private int id;
	private String content;
	private Timestamp createTime;
	private Boolean state = UNREAD_STATE;
	private Timestamp readTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Timestamp getReadTime() {
		return readTime;
	}

	public void setReadTime(Timestamp readTime) {
		this.readTime = readTime;
	}

}

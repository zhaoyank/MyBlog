package com.kaishengit.entity;

public class ResultJson {

	public static final String JSON_STATE_SUCCESS = "success";
	public static final String JSON_STATE_ERROR = "error";

	private String state;
	private Object data;
	private String message;

	public ResultJson() {

	}

	public ResultJson(Object data) {
		this.state = JSON_STATE_SUCCESS;
		this.data = data;
	}

	public ResultJson(String message) {
		this.state = JSON_STATE_ERROR;
		this.message = message;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getObj() {
		return data;
	}

	public void setObj(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

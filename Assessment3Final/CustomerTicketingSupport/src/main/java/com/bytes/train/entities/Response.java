package com.bytes.train.entities;


public class Response {
	
	private String mssg;
	private Object data;
	private boolean success;
	
	public Response(String mssg, Object data, boolean success) {
		super();
		this.mssg = mssg;
		this.data = data;
		this.success = success;
	}
	
	public String getMssg() {
		return mssg;
	}
	public void setMssg(String mssg) {
		this.mssg = mssg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}

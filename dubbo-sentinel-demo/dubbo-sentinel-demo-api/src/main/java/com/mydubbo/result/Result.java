package com.mydubbo.result;

public class Result <T>{
	private String status = null;
	private Object result = null;
	
	public Result() {
	}
	public Result(Object _result) {
		result = _result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}

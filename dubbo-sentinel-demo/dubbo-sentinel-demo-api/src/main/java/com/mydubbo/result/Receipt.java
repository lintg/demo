package com.mydubbo.result;

public class Receipt <T>{
	private String status = null;
	private Object result = null;
	
	public Receipt() {
	}
	public Receipt(Object _result) {
		result = _result;
	}
	public Receipt(String _status,Object _result) {
		status = _status;
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

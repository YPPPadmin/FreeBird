package com.hhsj.FreeBird.util;

public class JsonResult {
	private String msg;
	private String result;
	private boolean flag;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public JsonResult() {
		super();
	}

	public JsonResult(String msg, String result, boolean flag) {
		super();
		this.msg = msg;
		this.result = result;
		this.flag = flag;
	}

}

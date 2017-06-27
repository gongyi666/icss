package com.lantone.icss.trans.shaoyifu.model.response;

import java.io.Serializable;

/**
 * @Description:
 * @author:CSP
 * @time:2017年6月6日上午12:17:43
 */
public class SyfInquiry implements Serializable {
	private static final long serialVersionUID = 1L;
	private String status;	//交易状态
	private String msg; //交易返回信息（成功，失败等提示信息）
	private String ret; //返回结果标志，默认成功0,失败 1 参数错误 -1 token丢失-2
	private String data; //交易返回数据
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package com.lantone.icss.trans.shaoyifu.model;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月15日
 * 杭州朗通信息技术有限公司
 * @describe 调用接口完整类
 */
public class RequestData<T>  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String authentication;
	private T data;
	private String  empid;
	private String  hospitalCode;
	private String  sourceSystem;
	private String  tradeCode;
	private String  tradeTime;
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

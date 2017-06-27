package com.lantone.icss.trans.shaoyifu.model.response;

import java.io.Serializable;

import com.lantone.core.api.Response;

/**
 * @Description:
 * @author:CSP
 * @time:2017年6月6日上午12:26:20
 */
public class ResponsePatientInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String result;
	private String tradeCode;
	private String  hospitalCode;
	private String  tradeTime;
	private String  message;
	private SyfPatientInfo  data;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SyfPatientInfo getData() {
		return data;
	}
	public void setData(SyfPatientInfo data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

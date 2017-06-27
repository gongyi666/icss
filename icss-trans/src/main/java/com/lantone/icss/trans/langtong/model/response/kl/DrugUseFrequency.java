package com.lantone.icss.trans.langtong.model.response.kl;

import java.io.Serializable;

public class DrugUseFrequency implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String freEnName;
	private String freName;
	private String freNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFreEnName() {
		return freEnName;
	}
	public void setFreEnName(String freEnName) {
		this.freEnName = freEnName;
	}
	public String getFreName() {
		return freName;
	}
	public void setFreName(String freName) {
		this.freName = freName;
	}
	public String getFreNum() {
		return freNum;
	}
	public void setFreNum(String freNum) {
		this.freNum = freNum;
	}
	
}

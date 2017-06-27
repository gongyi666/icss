package com.lantone.icss.api.sys;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 1L;
	//用户名
	private String userName;
	//密码
	private String passWord;
	//登录机构号
	private String unitId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
}

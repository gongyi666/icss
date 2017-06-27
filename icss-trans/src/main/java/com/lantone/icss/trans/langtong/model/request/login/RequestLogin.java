package com.lantone.icss.trans.langtong.model.request.login;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月15日
 * 杭州朗通信息技术有限公司
 * @describe
 */
public class RequestLogin  implements Serializable{

	private static final long serialVersionUID = 1L;
	/***
	 * 用户名
	 */
	private String loginName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 医院编码
	 */
	private String hospitalId;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
}

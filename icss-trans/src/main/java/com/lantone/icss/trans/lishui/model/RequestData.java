package com.lantone.icss.trans.lishui.model;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年2月15日
 * 杭州朗通信息技术有限公司
 * @describe 调用接口完整类
 */
public class RequestData  implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 *交易类型
	 */
	private String tranType;
	/**
	 * 用户代码
	 */
	private String tranKey;
	/**
	 * 职工编号
	 */
	private String stffNo;
	/**
	 * 医院编号
	 */
	private String hospitalId;
	/**
	 * 科室编号
	 */
	private String departId;
	/***
	 * 交易数据
	 */
	private String tranData;
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getTranKey() {
		return tranKey;
	}
	public void setTranKey(String tranKey) {
		this.tranKey = tranKey;
	}
	public String getStffNo() {
		return stffNo;
	}
	public void setStffNo(String stffNo) {
		this.stffNo = stffNo;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getTranData() {
		return tranData;
	}
	public void setTranData(String tranData) {
		this.tranData = tranData;
	}

}

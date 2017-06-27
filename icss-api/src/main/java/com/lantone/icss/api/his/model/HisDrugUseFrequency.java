package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisDrugUseFrequency implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	/**医院编码**/
	private String hospitalCode;
	/**名称**/
	private String name;
	/**英文**/
	private String enName;
	/**次数**/
	private int num;
	/**顺序号**/
	private int orderNo;
	/**备注**/
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}

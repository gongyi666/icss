package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisPartInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String hisPartCode;    //部位编号
	private String hospitalCode;    //医院编码
	private String partName;        //部位名称
	private String remark;           //备注 
	
	public String getHisPartCode() {
		return hisPartCode;
	}
	public void setHisPartCode(String hisPartCode) {
		this.hisPartCode = hisPartCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

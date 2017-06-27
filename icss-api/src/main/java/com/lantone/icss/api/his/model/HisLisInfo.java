package com.lantone.icss.api.his.model;

import java.io.Serializable;

public class HisLisInfo implements Serializable{

	private String hisLisCode;    //his检验编码
	private String hospitalCode;  //医院编码
	private String name;          //lis名称
	private String spell;         //拼音
	private String fiveStroke;    //五笔
	private String remark;        //备注
	
	public String getHisLisCode() {
		return hisLisCode;
	}
	public void setHisLisCode(String hisLisCode) {
		this.hisLisCode = hisLisCode;
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
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getFiveStroke() {
		return fiveStroke;
	}
	public void setFiveStroke(String fiveStroke) {
		this.fiveStroke = fiveStroke;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

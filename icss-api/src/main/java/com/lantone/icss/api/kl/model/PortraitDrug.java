package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class PortraitDrug implements Serializable{
	private Long id;             //主键
	private Long drgId;       //上级Id
	private Long portraitId;   //纵向菜单id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDrgId() {
		return drgId;
	}
	public void setDrgId(Long drgId) {
		this.drgId = drgId;
	}
	public Long getPortraitId() {
		return portraitId;
	}
	public void setPortraitId(Long portraitId) {
		this.portraitId = portraitId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String remark;       //备注
}

package com.lantone.icss.api.kl.model;

import java.io.Serializable;


public class RetrievalInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;              //主键
	private String name;          //名称
	private String spell;         //拼音
	private String status;        //状态
	private String remark;        //备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

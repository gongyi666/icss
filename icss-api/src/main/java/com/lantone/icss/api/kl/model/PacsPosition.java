package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:pacs部位
 * @author : luwg
 * @time : 2017年1月9日 下午1:44:27
 * 
 */
public class PacsPosition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;       //主键
	private Long parentId; //上级id
	private String code;   //部位编码
	private String name;   //部位名称
	private Long standId;  //字典编码id
	private String orderNo; //排序号
	private String status; //状态
	private String remark; //备注
	private String isDefault; //是否默认
	private String spell; //拼音
	private String fiveStroke; //五笔
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStandId() {
		return standId;
	}
	public void setStandId(Long standId) {
		this.standId = standId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
	
}

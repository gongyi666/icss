package com.lantone.icss.api.his.model;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年3月7日
 * 杭州朗通信息技术有限公司
 * @describe 用法
 */
public class HisUsageMode implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	/**医院编号**/
	private String hospitalCode;
	/**名称**/
	private String name;
	/**拼音**/
	private String spell;
	/**五笔码**/
	private String fiveStroke;
	/**状态**/
	private String status;
	/**顺序号**/
	private int orderNo;
	/**备注**/
	private String remark;
	
	private String modCategory;	//给药方式类型
	private String modType;	//处方类型
	private String modSort;	//排序
	public String getModCategory() {
		return modCategory;
	}
	public void setModCategory(String modCategory) {
		this.modCategory = modCategory;
	}
	public String getModType() {
		return modType;
	}
	public void setModType(String modType) {
		this.modType = modType;
	}
	public String getModSort() {
		return modSort;
	}
	public void setModSort(String modSort) {
		this.modSort = modSort;
	}

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
}

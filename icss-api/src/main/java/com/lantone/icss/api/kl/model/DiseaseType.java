package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:疾病类型
 * @author:ztg
 * @time:2017年4月10日 下午3:19:21
 */
public class DiseaseType implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;            //主键
	private String name;        //名称
	private String orderNo;     //类型
	private String status;      //状态
	private String remark;      //备注
	
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	
	

}

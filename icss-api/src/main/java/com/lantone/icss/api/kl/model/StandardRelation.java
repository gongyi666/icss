package com.lantone.icss.api.kl.model;

import java.io.Serializable;

public class StandardRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long inStandardId;
	
	private Long outStandardId;
	
	private String outname;
	
	private Integer outtype;
	
	private String orderNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInStandardId() {
		return inStandardId;
	}

	public void setInStandardId(Long inStandardId) {
		this.inStandardId = inStandardId;
	}

	public Long getOutStandardId() {
		return outStandardId;
	}

	public void setOutStandardId(Long outStandardId) {
		this.outStandardId = outStandardId;
	}

	public String getOutname() {
		return outname;
	}

	public void setOutname(String outname) {
		this.outname = outname;
	}

	public Integer getOuttype() {
		return outtype;
	}

	public void setOuttype(Integer outtype) {
		this.outtype = outtype;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}

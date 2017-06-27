package com.lantone.icss.api.kl;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2016年12月26日
 * 杭州朗通信息技术有限公司
 * @describe 疾病与检查的关系
 */
public class DiseasePhysical implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long diseaseId;
	private Long physicalId;
	private Long phyStandId;
	private Long detailStandId;
	private Long phyDetailId;
	private String orderNo;
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}
	public Long getPhysicalId() {
		return physicalId;
	}
	public void setPhysicalId(Long physicalId) {
		this.physicalId = physicalId;
	}
	public Long getPhyStandId() {
		return phyStandId;
	}
	public void setPhyStandId(Long phyStandId) {
		this.phyStandId = phyStandId;
	}
	public Long getDetailStandId() {
		return detailStandId;
	}
	public void setDetailStandId(Long detailStandId) {
		this.detailStandId = detailStandId;
	}
	public Long getPhyDetailId() {
		return phyDetailId;
	}
	public void setPhyDetailId(Long phyDetailId) {
		this.phyDetailId = phyDetailId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

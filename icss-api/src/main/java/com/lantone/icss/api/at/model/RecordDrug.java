package com.lantone.icss.api.at.model;

import java.io.Serializable;

/**
 * @Description:用药记录
 * @author : luwg
 * @time : 2016年12月20日 上午11:06:08
 * 
 */
public class RecordDrug implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;               //主键
	private Long recordId;         //问诊记录id
	private Long drugId;           //药品id
	private String useDosage;      //剂量
	private String useUnit;        //剂量单位
	private String useUsage;       //用法
	private String useFrequency;   //用药频次
	private String useDays;        //用药天数
	private String orderNo;        //排序号
	private String remark;         //备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Long getDrugId() {
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public String getUseDosage() {
		return useDosage;
	}
	public void setUseDosage(String useDosage) {
		this.useDosage = useDosage;
	}
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	public String getUseUsage() {
		return useUsage;
	}
	public void setUseUsage(String useUsage) {
		this.useUsage = useUsage;
	}
	public String getUseFrequency() {
		return useFrequency;
	}
	public void setUseFrequency(String useFrequency) {
		this.useFrequency = useFrequency;
	}
	public String getUseDays() {
		return useDays;
	}
	public void setUseDays(String useDays) {
		this.useDays = useDays;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

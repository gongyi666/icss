package com.lantone.icss.api.kl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:问题明细
 * @author : luwg
 * @time : 2016年12月14日 下午1:42:15
 * 
 */
public class ContentDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;          	//主键
	private Long standardId;  	//编码id
	private Long contentId;  	//内容id
	private String code;      	//编码
	private String name;      	//名称
	private String orderNo;  	//排序号
	private String relationId; 	//关联contentID
	private String status;    	//状态
	private String remark;    	//备注

	private BigDecimal minValue;	//最小值
	private BigDecimal maxValue;	//最大值
	private String judgeType;		//判断类型（0:本身异常；1:本身正常；2:数字范围；3:计算公式；9:无需判断）
	private String formulaCode;		//前端公式编号
	
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
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

	public BigDecimal getMinValue() {
		return minValue;
	}
	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}
	public BigDecimal getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}
	public String getJudgeType() {
		return judgeType;
	}
	public void setJudgeType(String judgeType) {
		this.judgeType = judgeType;
	}
	public String getFormulaCode() {
		return formulaCode;
	}
	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}
}

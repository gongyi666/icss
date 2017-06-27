package com.lantone.icss.api.kl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:问题内容
 * @author : luwg
 * @time : 2016年12月14日 下午1:37:34
 * 
 */
public class QuestionContent implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;          //主键
	private Long questionId;  //问题id
	private Long standardId;  //编码id
	private String paramCode; //表单名称
	private String code;      //编码
	private String type;      //类型
	private String hasDetail; //是否有下一级
	private String orderNo;   //排序号
	private String status;    //状态
	private String remark;    //备注
	private String labelPrefix; //前置内容
	private String labelSuffix; //后置内容
	private String addLine;     //另起一行

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
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getAddLine() {
		return addLine;
	}
	public void setAddLine(String addLine) {
		this.addLine = addLine;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getHasDetail() {
		return hasDetail;
	}
	public void setHasDetail(String hasDetail) {
		this.hasDetail = hasDetail;
	}
	public String getLabelPrefix() {
		return labelPrefix;
	}
	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}
	public String getLabelSuffix() {
		return labelSuffix;
	}
	public void setLabelSuffix(String labelSuffix) {
		this.labelSuffix = labelSuffix;
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

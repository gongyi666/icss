package com.lantone.icss.api.kl.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:检验项目明细
 * @author : luwg
 * @time : 2017年1月19日 下午1:30:45
 * 
 */
public class LisDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private Long standardId;      //编码id
	private Long lisId;        //检验项目id
	private String code;	   //编码
	private String name;       //名称
	private String spell;      //拼音
	private String fiveStroke; //五笔
	private String labelPrefix;//明细前缀
	private String textName;   //文本框内容
	private String labelSuffix;//明细后缀
	private String status;		//状态
	private String remark;		//备注
	private BigDecimal minValue;	//最小值
	private BigDecimal maxValue;	//最大值

	/**
	 * 类型1：按钮，2：文本框，3：下拉框，4：复选框，5：单选，6：多行文本框 , 7：日期控件，8：下拉多选，9：下拉多选附带文本
	 * column : kl_lis_detail.type
	 */
	private String type;

	/**
	 * 是否有下一级
	 * column : kl_lis_detail.has_detail
	 */
	private String hasDetail;

	/**
	 * 是否另起一行(1:另起一行)
	 * column : kl_lis_detail.add_line
	 */
	private String addLine;
	
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	public Long getLisId() {
		return lisId;
	}
	public void setLisId(Long lisId) {
		this.lisId = lisId;
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
	public String getLabelPrefix() {
		return labelPrefix;
	}
	public void setLabelPrefix(String labelPrefix) {
		this.labelPrefix = labelPrefix;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public String getLabelSuffix() {
		return labelSuffix;
	}
	public void setLabelSuffix(String labelSuffix) {
		this.labelSuffix = labelSuffix;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHasDetail() {
		return hasDetail;
	}

	public void setHasDetail(String hasDetail) {
		this.hasDetail = hasDetail;
	}

	public String getAddLine() {
		return addLine;
	}

	public void setAddLine(String addLine) {
		this.addLine = addLine;
	}
}

package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @Description:器查结果信息
 * @author : luwg
 * @time : 2017年1月19日 下午1:30:45
 * 
 */
public class PacsResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;           //主键
	private Long standardId;      //编码id
	private Long pacsId;        //检验项目id
	private String code;	   //编码
	private String name;       //名称
	private String spell;      //拼音
	private String fiveStroke; //五笔
	private String labelPrefix;//明细前缀
	private String textName;   //文本框内容
	private String labelSuffix;//明细后缀
	private String status;		//状态
	private String remark;		//备注
	
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
	public Long getPacsId() {
		return pacsId;
	}
	public void setPacsId(Long pacsId) {
		this.pacsId = pacsId;
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
	
	
	
}

package com.lantone.icss.api.at.model;

import java.io.Serializable;
import java.util.Date;

public class HistoryCaseOut implements Serializable{

	private static final long serialVersionUID = 1L;
	//就诊序号
	private String id;
	//就诊日期
	private Date visDate;
	//就诊医生
	private String sffId;
	//就诊医生名字
	private String sffName;
	//就诊病人序号
	private String patId;
	//就诊病人姓名
	private String patName;
	//诊断疾病
	private String disName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getVisDate() {
		return visDate;
	}
	public void setVisDate(Date visDate) {
		this.visDate = visDate;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getSffName() {
		return sffName;
	}
	public void setSffName(String sffName) {
		this.sffName = sffName;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
}

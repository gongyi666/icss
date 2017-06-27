package com.lantone.icss.trans.yiqian.model.brxx2005.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description:检查检验诊疗单
 * @author:ztg
 * @time:2017年5月12日 下午12:17:36
 */

public class BrxxExamineDetail implements Serializable {
	private Long ID;				//
	private String visId;			//就诊序号
	private String exaDate;			//开单时间
	private String depId;			//开单科室
	private String sffId;			//开单医生
	private String exaType;			//单据类型（1检验，0检查，2诊疗单）
	private String patId;			//病人序号
	private String examineExaDate;	//检查时间
	private String examineSummary;	//检查摘要
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getVisId() {
		return visId;
	}
	public void setVisId(String visId) {
		this.visId = visId;
	}
	public String getExaDate() {
		return exaDate;
	}
	public void setExaDate(String exaDate) {
		this.exaDate = exaDate;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getExaType() {
		return exaType;
	}
	public void setExaType(String exaType) {
		this.exaType = exaType;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getExamineExaDate() {
		return examineExaDate;
	}
	public void setExamineExaDate(String examineExaDate) {
		this.examineExaDate = examineExaDate;
	}
	public String getExamineSummary() {
		return examineSummary;
	}
	public void setExamineSummary(String examineSummary) {
		this.examineSummary = examineSummary;
	}
	
	
	
	
	
	
	
}

package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 诊疗项目详细
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class ProjectRowRequest {
	@XmlElement(name = "BRSQLX")
	private String brsqlx;
	@XmlElement(name = "SQFLID")
	private String sqflid;
	@XmlElement(name = "ITEM")
	private List<ProjectItem> projectItems;
	public String getBrsqlx() {
		return brsqlx;
	}
	public void setBrsqlx(String brsqlx) {
		this.brsqlx = brsqlx;
	}
	public String getSqflid() {
		return sqflid;
	}
	public void setSqflid(String sqflid) {
		this.sqflid = sqflid;
	}
	public List<ProjectItem> getProjectItems() {
		return projectItems;
	}
	public void setProjectItems(List<ProjectItem> projectItems) {
		this.projectItems = projectItems;
	}
	
}

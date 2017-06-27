package com.lantone.icss.trans.yiqian.model.brxx2005.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class ProjectRequest {
	@XmlElement(name = "ROW")
	private List<ProjectRowRequest> projectRowRequests;

	public List<ProjectRowRequest> getProjectRowRequests() {
		return projectRowRequests;
	}

	public void setProjectRowRequests(List<ProjectRowRequest> projectRowRequests) {
		this.projectRowRequests = projectRowRequests;
	}

	
}

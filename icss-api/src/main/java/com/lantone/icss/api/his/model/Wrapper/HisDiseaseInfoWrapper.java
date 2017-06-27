package com.lantone.icss.api.his.model.Wrapper;

import com.lantone.icss.api.his.model.HisDiseaseInfo;

public class HisDiseaseInfoWrapper extends HisDiseaseInfo{

	private static final long serialVersionUID = 1L;

	
	private String doctorCode;   //医生HIS编码
	private String deptNo;   //部门编码
	
	private String pageNum;
	private String pageSize;
	
	private Long icssDiseaseId;  //icss疾病编码

	
	public String getDoctorCode() {
		return doctorCode;
	}
	public void setDoctorCode(String doctorCode) {
		this.doctorCode = doctorCode;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public Long getIcssDiseaseId() {
		return icssDiseaseId;
	}
	public void setIcssDiseaseId(Long icssDiseaseId) {
		this.icssDiseaseId = icssDiseaseId;
	}
	
	
}

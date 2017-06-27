package com.lantone.icss.api.kl.model.wrapper;

import java.util.List;

import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.model.LisInfo;

public class LisInfoWrapper extends LisInfo{

	private static final long serialVersionUID = 1L;

	private String hospitalCode;			//医院编码
	private String doctorCode;				//医生HIS编码
	private String deptNo;					//部门编码
	private String binType;					//套餐类型
	private String[] itemIds; 				//明细id
	private Long[] diseaseIds; 				//疾病id
	private Long doctorId; 					//医生id
	private Integer size;					//返回条数
	private String inputstr;				//输入内容
	private String detailCode;  			//明细编码
	private String notIds;    				//过滤已选
	private String[] notIdsArr; 			//过滤已选数组
	private int totalCount;                 //明细总条数
	private java.math.BigDecimal totalFee;  //费用
	private Integer age;   					 //年龄过滤
	private Long patientId;					 //患者ID
	private Long type;						 //类型编码
	private List<Long> standardIds;          //推理standardIds

	private String paramCode; //编码code
	private String subName; //子集名称
	
	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public java.math.BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(java.math.BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String[] getNotIdsArr() {
		return notIdsArr;
	}

	public void setNotIdsArr(String[] notIdsArr) {
		this.notIdsArr = notIdsArr;
	}

	public String getNotIds() {
		return notIds;
	}

	public void setNotIds(String notIds) {
		this.notIds = notIds;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	private List<LisDetailWrapper> lisDetails = Lists.newArrayList();
	
	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

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

	public String getBinType() {
		return binType;
	}
	public void setBinType(String binType) {
		this.binType = binType;
	}
	public List<LisDetailWrapper> getLisDetails() {
		return lisDetails;
	}
	public void setLisDetails(List<LisDetailWrapper> lisDetails) {
		this.lisDetails = lisDetails;
	}

	public String[] getItemIds() {
		return itemIds;
	}

	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}

	public Long[] getDiseaseIds() {
		return diseaseIds;
	}

	public void setDiseaseIds(Long[] diseaseIds) {
		this.diseaseIds = diseaseIds;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getInputstr() {
		return inputstr;
	}

	public void setInputstr(String inputstr) {
		this.inputstr = inputstr;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public List<Long> getStandardIds() {
		return standardIds;
	}

	public void setStandardIds(List<Long> standardIds) {
		this.standardIds = standardIds;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
}

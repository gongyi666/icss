package com.lantone.icss.api.kl.model;

import java.io.Serializable;
import java.util.List;

import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;

/**
 * @Description:疾病信息
 * @author : luwg
 * @time : 2016年12月14日 下午1:15:48
 * 
 */
public class DiseaseInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;            //主键
	private Long standardId;    //标准编码id
	private Long parentId;      //父级ID
	private String code;        //编码
	private String hisCode;     //his疾病编码
	private String name;        //名称
	private String shortName;   //简称
	private String spell;       //拼音
	private String fiveStroke;  //五笔
	private String type;        //类型
	private Long sysType;       //系统类别
	private String sexType;     //性别类型
	private Long ageBegin;      //开始年龄（默认0）
	private Long ageEnd;        //结束年龄（默认200）
	private String status;      //状态
	private String remark;      //备注
	private String accasdiag;   //诊断依据确诊规则
	private String isdiag;      //是否确诊
	
	private List<DiagnoseInfoWrapper> diagnoseInfos;
	
	public List<DiagnoseInfoWrapper> getDiagnoseInfos() {
		return diagnoseInfos;
	}
	public void setDiagnoseInfos(List<DiagnoseInfoWrapper> diagnoseInfos) {
		this.diagnoseInfos = diagnoseInfos;
	}
	public Long getSysType() {
		return sysType;
	}
	public void setSysType(Long sysType) {
		this.sysType = sysType;
	}
	public String getSexType() {
		return sexType;
	}
	public void setSexType(String sexType) {
		this.sexType = sexType;
	}
	public Long getAgeBegin() {
		return ageBegin;
	}
	public void setAgeBegin(Long ageBegin) {
		this.ageBegin = ageBegin;
	}
	public Long getAgeEnd() {
		return ageEnd;
	}
	public void setAgeEnd(Long ageEnd) {
		this.ageEnd = ageEnd;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getHisCode() {
		return hisCode;
	}
	public void setHisCode(String hisCode) {
		this.hisCode = hisCode;
	}
	public String getAccasdiag() {
		return accasdiag;
	}
	public void setAccasdiag(String accasdiag) {
		this.accasdiag = accasdiag;
	}
	public String getIsdiag() {
		return isdiag;
	}
	public void setIsdiag(String isdiag) {
		this.isdiag = isdiag;
	}

}

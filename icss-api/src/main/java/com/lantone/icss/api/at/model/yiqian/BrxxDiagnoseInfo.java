package com.lantone.icss.api.at.model.yiqian;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description:就诊信息
 * @author:ztg
 * @time:2017年5月12日 下午12:17:36
 */

public class BrxxDiagnoseInfo implements Serializable {
	private Long ID;				//
	private String vis_id;			//就诊序号
	private String dis_id;			//疾病序号
	private String dia_icd;			//ICD
	private String dia_name;		//诊断名称
	private String dia_summary;		//诊断说明
	private String dia_sign;		//诊断判别（确诊，疑诊）
	private String hospital_id;		//所属机构
	private String dia_time;		//发病时间
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getVis_id() {
		return vis_id;
	}
	public void setVis_id(String vis_id) {
		this.vis_id = vis_id;
	}
	public String getDis_id() {
		return dis_id;
	}
	public void setDis_id(String dis_id) {
		this.dis_id = dis_id;
	}
	public String getDia_icd() {
		return dia_icd;
	}
	public void setDia_icd(String dia_icd) {
		this.dia_icd = dia_icd;
	}
	public String getDia_name() {
		return dia_name;
	}
	public void setDia_name(String dia_name) {
		this.dia_name = dia_name;
	}
	public String getDia_summary() {
		return dia_summary;
	}
	public void setDia_summary(String dia_summary) {
		this.dia_summary = dia_summary;
	}
	public String getDia_sign() {
		return dia_sign;
	}
	public void setDia_sign(String dia_sign) {
		this.dia_sign = dia_sign;
	}
	public String getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}
	public String getDia_time() {
		return dia_time;
	}
	public void setDia_time(String dia_time) {
		this.dia_time = dia_time;
	}
	
	
	
	
	
	
}

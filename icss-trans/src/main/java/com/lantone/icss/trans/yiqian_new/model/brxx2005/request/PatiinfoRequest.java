package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class PatiinfoRequest {
	@XmlElement(name = "ZZJGDM")	
	private String zzjgdm;			//机构代码
	@XmlElement(name = "BRJZHM")
	private String brjzhm;			//病人介质号码
	@XmlElement(name = "BRDAXM")
	private String brdaxm;			//病人姓名
	@XmlElement(name = "BRDAID")
	private String brdaid;			//病人档案ID
	@XmlElement(name = "BRJZID")
	private String brjzid;			//病人介质ID
	@XmlElement(name = "KDKSID")
	private String kdksid;			//开单科室
	@XmlElement(name = "KDYSID")
	private String kdysid;			//开单医生
	@XmlElement(name = "BRJZRQ")
	private String brjzrq;			//就诊日期
	@XmlElement(name = "BRJZXH")
	private Long brjzxh;			//病人就诊序号
	@XmlElement(name = "DYJLID")
	private String dyjlid;			//对应记录序号
	@XmlElement(name = "SCJLLX")
	private String scjllx;			//传入类型
	
	
	public Long getBrjzxh() {
		return brjzxh;
	}
	public void setBrjzxh(Long brjzxh) {
		this.brjzxh = brjzxh;
	}
	public String getZzjgdm() {
		return zzjgdm;
	}
	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}
	public String getBrjzhm() {
		return brjzhm;
	}
	public void setBrjzhm(String brjzhm) {
		this.brjzhm = brjzhm;
	}
	public String getBrdaxm() {
		return brdaxm;
	}
	public void setBrdaxm(String brdaxm) {
		this.brdaxm = brdaxm;
	}
	public String getBrdaid() {
		return brdaid;
	}
	public void setBrdaid(String brdaid) {
		this.brdaid = brdaid;
	}
	public String getBrjzid() {
		return brjzid;
	}
	public void setBrjzid(String brjzid) {
		this.brjzid = brjzid;
	}
	public String getKdksid() {
		return kdksid;
	}
	public void setKdksid(String kdksid) {
		this.kdksid = kdksid;
	}
	public String getKdysid() {
		return kdysid;
	}
	public void setKdysid(String kdysid) {
		this.kdysid = kdysid;
	}
	public String getBrjzrq() {
		return brjzrq;
	}
	public void setBrjzrq(String brjzrq) {
		this.brjzrq = brjzrq;
	}
	public String getDyjlid() {
		return dyjlid;
	}
	public void setDyjlid(String dyjlid) {
		this.dyjlid = dyjlid;
	}
	public String getScjllx() {
		return scjllx;
	}
	public void setScjllx(String scjllx) {
		this.scjllx = scjllx;
	}
	
}

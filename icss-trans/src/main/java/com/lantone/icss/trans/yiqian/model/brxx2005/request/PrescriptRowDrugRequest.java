package com.lantone.icss.trans.yiqian.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description: 用药
 * @author:ztg
 * @time:2017年5月9日 下午2:09:59
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class PrescriptRowDrugRequest {
	
	@XmlElement(name = "CFMXXH")	
	private Long cfmxxh;			//明细序号
	@XmlElement(name = "YPCDID")	
	private Long ypcdid;			//药品产地ID
	@XmlElement(name = "YPXSZH")	
	private Integer ypxszh;			//药品组号
	@XmlElement(name = "CFYPSL")	
	private Integer cfypsl;			//数量
	@XmlElement(name = "GYFSID")	
	private String gyfsid;			//给药方式
	@XmlElement(name = "SYPLID")	
	private String syplid;			//频率
	@XmlElement(name = "SYPLCS")	
	private Integer syplcs;			//频率次数
	@XmlElement(name = "YPDCJL")	
	private Integer ypdcjl;			//单次剂量
	@XmlElement(name = "YPJLDW")	
	private String ypjldw;			//剂量单位
	@XmlElement(name = "YPSYTS")	
	private Integer ypsyts;			//使用天数
	@XmlElement(name = "YSZTSM")	
	private String ysztsm;			//嘱托
	public Long getCfmxxh() {
		return cfmxxh;
	}
	public void setCfmxxh(Long cfmxxh) {
		this.cfmxxh = cfmxxh;
	}
	public Long getYpcdid() {
		return ypcdid;
	}
	public void setYpcdid(Long ypcdid) {
		this.ypcdid = ypcdid;
	}
	public Integer getYpxszh() {
		return ypxszh;
	}
	public void setYpxszh(Integer ypxszh) {
		this.ypxszh = ypxszh;
	}
	public Integer getCfypsl() {
		return cfypsl;
	}
	public void setCfypsl(Integer cfypsl) {
		this.cfypsl = cfypsl;
	}
	public String getGyfsid() {
		return gyfsid;
	}
	public void setGyfsid(String gyfsid) {
		this.gyfsid = gyfsid;
	}
	public String getSyplid() {
		return syplid;
	}
	public void setSyplid(String syplid) {
		this.syplid = syplid;
	}
	public Integer getSyplcs() {
		return syplcs;
	}
	public void setSyplcs(Integer syplcs) {
		this.syplcs = syplcs;
	}
	public Integer getYpdcjl() {
		return ypdcjl;
	}
	public void setYpdcjl(Integer ypdcjl) {
		this.ypdcjl = ypdcjl;
	}
	public String getYpjldw() {
		return ypjldw;
	}
	public void setYpjldw(String ypjldw) {
		this.ypjldw = ypjldw;
	}
	public Integer getYpsyts() {
		return ypsyts;
	}
	public void setYpsyts(Integer ypsyts) {
		this.ypsyts = ypsyts;
	}
	public String getYsztsm() {
		return ysztsm;
	}
	public void setYsztsm(String ysztsm) {
		this.ysztsm = ysztsm;
	}


	
	
}

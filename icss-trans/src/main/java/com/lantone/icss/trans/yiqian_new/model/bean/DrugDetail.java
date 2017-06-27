package com.lantone.icss.trans.yiqian_new.model.bean;

/**
 * @Description:处方明细
 * @author:ztg
 * @time:2017年5月10日 下午3:57:56
 */
public class DrugDetail {
	
	private long cfmxxh;	//明细序号
	private long ypcdid;	//药品产地ID
	private Integer ypxszh;	//药品组号
	private Integer cfypsl;	//数量
	private String gyfsid;	//给药方式
	private String syplid;	//频率
	private Integer syplcs;	//频率次数
	private Integer ypdcjl;	//单次剂量
	private String ypjldw;	//剂量单位
	private Integer ypsyts;	//使用天数
	private String ysztsm;	//嘱托
	public long getCfmxxh() {
		return cfmxxh;
	}
	public void setCfmxxh(long cfmxxh) {
		this.cfmxxh = cfmxxh;
	}
	public long getYpcdid() {
		return ypcdid;
	}
	public void setYpcdid(long ypcdid) {
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

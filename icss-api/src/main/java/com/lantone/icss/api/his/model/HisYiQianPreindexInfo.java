package com.lantone.icss.api.his.model;

import java.io.Serializable;

/**
 * @Description:医乾预检信息
 * @author:ztg
 * @time:2017年5月10日 上午11:40:17
 */
public class HisYiQianPreindexInfo implements Serializable{

	private String yjzbid;		//序号
	private String yjzbmc;		//名称
	private String yjzbdw;    	//单位
	private String zbjcjg;  	//结果
	
	public String getYjzbid() {
		return yjzbid;
	}
	public void setYjzbid(String yjzbid) {
		this.yjzbid = yjzbid;
	}
	public String getYjzbmc() {
		return yjzbmc;
	}
	public void setYjzbmc(String yjzbmc) {
		this.yjzbmc = yjzbmc;
	}
	public String getYjzbdw() {
		return yjzbdw;
	}
	public void setYjzbdw(String yjzbdw) {
		this.yjzbdw = yjzbdw;
	}
	public String getZbjcjg() {
		return zbjcjg;
	}
	public void setZbjcjg(String zbjcjg) {
		this.zbjcjg = zbjcjg;
	}
	
	
	
	
	
}

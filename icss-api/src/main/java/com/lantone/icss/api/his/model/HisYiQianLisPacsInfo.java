package com.lantone.icss.api.his.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:医乾保存化验和器查明细表
 * @author:ztg
 * @time:2017年5月10日 上午11:41:14
 */
public class HisYiQianLisPacsInfo implements Serializable{

	private String hisType;          	//1:化验，2:体检
	private String sqflid;				//如果是化验就是L
	private List<HisYiQianLisPacsDetail> detail; //明细信息
	
	
	

	
	public List<HisYiQianLisPacsDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<HisYiQianLisPacsDetail> detail) {
		this.detail = detail;
	}
	public String getHisType() {
		return hisType;
	}
	public void setHisType(String hisType) {
		this.hisType = hisType;
	}
	public String getSqflid() {
		return sqflid;
	}
	public void setSqflid(String sqflid) {
		this.sqflid = sqflid;
	}
	
	
	
	
}

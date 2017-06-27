package com.lantone.icss.trans.yiqian.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:医乾处方信息
 * @author:ztg
 * @time:2017年5月10日 下午3:57:04
 */
public class DrugInfo implements Serializable{

	private Integer brcflx;		//类型 (1西2成4草)
	private Integer brcfts;    //中药贴数
	private List<DrugDetail> detail;
	
	
	
	
	public List<DrugDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<DrugDetail> detail) {
		this.detail = detail;
	}
	public Integer getBrcflx() {
		return brcflx;
	}
	public void setBrcflx(Integer brcflx) {
		this.brcflx = brcflx;
	}
	public Integer getBrcfts() {
		return brcfts;
	}
	public void setBrcfts(Integer brcfts) {
		this.brcfts = brcfts;
	}
	
	
}

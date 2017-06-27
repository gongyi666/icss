package com.lantone.icss.api.at.model.yiqian;

import java.util.List;

/**
 * @Description:医乾处方主表
 * @author:ztg
 * @time:2017年5月19日 下午5:19:34
 */
public class DrugYiQian {
	private Integer brcflx;		//类型 (1西2成4草)
	private Integer brcfts;    //中药贴数
	private List<DrugDetailYiQian> detail;
	
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
	public List<DrugDetailYiQian> getDetail() {
		return detail;
	}
	public void setDetail(List<DrugDetailYiQian> detail) {
		this.detail = detail;
	}
	
	
}

package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/** 
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日上午11:59:11
 */

@XmlRootElement(name = "data")
public class DutasterideResponse {
	private List<DutasterideDetailResponse> row =null;

	public List<DutasterideDetailResponse> getRow() {
		return row;
	}

	public void setRow(List<DutasterideDetailResponse> row) {
		this.row = row;
	}


}

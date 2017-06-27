package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 *	Description: 获取频率信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日下午3:10:06
 */
@XmlRootElement(name = "data")
public class DiseaseInfoResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<DiseaseInfoDetailResponse> row=null;
	public List<DiseaseInfoDetailResponse> getRow() {
		return row;
	}
	public void setRow(List<DiseaseInfoDetailResponse> row) {
		this.row = row;
	}
	
}

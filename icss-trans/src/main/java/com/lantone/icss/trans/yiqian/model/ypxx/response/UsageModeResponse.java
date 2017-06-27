package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 *	Description: 获取用法信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日下午3:10:06
 */
@XmlRootElement(name = "data")
public class UsageModeResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<UsageModeDetailResponse> row=null;
	public List<UsageModeDetailResponse> getRow() {
		return row;
	}
	public void setRow(List<UsageModeDetailResponse> row) {
		this.row = row;
	}
	
}

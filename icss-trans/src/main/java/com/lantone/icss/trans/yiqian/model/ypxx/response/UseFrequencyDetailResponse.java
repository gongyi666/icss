package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *	Description: 药品频率信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月11日上午11:00:42
 */
@XmlRootElement(name ="row")
public class UseFrequencyDetailResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	//@XmlElement(name = "id")
	private String id = "";	//
	//@XmlElement(name = "freEnName")
	private String freEnName = "";	//频率英文
	//@XmlElement(name = "freName")
	private String freName = "";	//频率名称
	//@XmlElement(name = "freNum")
	private String freNum = "";	//次数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFreEnName() {
		return freEnName;
	}
	public void setFreEnName(String freEnName) {
		this.freEnName = freEnName;
	}
	public String getFreName() {
		return freName;
	}
	public void setFreName(String freName) {
		this.freName = freName;
	}
	public String getFreNum() {
		return freNum;
	}
	public void setFreNum(String freNum) {
		this.freNum = freNum;
	}
	
}

package com.lantone.icss.trans.yiqian.model.ypxx.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 病人信息详细
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlRootElement(name ="data")
public class InventoryDetailResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	//@XmlElement(name = "ret")//返回结果(0:足够;1:不够)
	private String ret = "";//返回信息
	//@XmlElement(name = "msg")
	private String msg = "";
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}

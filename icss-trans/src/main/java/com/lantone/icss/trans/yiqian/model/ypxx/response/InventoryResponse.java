package com.lantone.icss.trans.yiqian.model.ypxx.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/***Title: 
*	Description: 病人信息返回
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "body")
public class InventoryResponse {
	//@XmlElement(name = "states")//返回结果(0:足够;1:不够)
	private String status = "";
	//@XmlElement(name = "totalQuantity")//返回库存数量
	private String totalQuantity = "";
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
}

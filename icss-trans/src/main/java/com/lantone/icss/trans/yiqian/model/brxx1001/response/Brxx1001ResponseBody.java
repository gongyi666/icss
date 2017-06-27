package com.lantone.icss.trans.yiqian.model.brxx1001.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.icss.trans.yiqian.model.ResHead;






/***Title: 
*	Description: 病人信息主体
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Brxx1001ResponseBody{
	ResHead head;
	
	Brxx1001Response body;

	public Brxx1001Response getBody() {
		return body;
	}

	public void setBody(Brxx1001Response body) {
		this.body = body;
	}

	public ResHead getHead() {
		return head;
	}

	public void setHead(ResHead head) {
		this.head = head;
	}


	
}

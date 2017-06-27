package com.lantone.icss.trans.yiqian.model.brxx1002.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.icss.trans.yiqian.model.ResHead;


/***Title: 
*	Description: 返回病人历史就诊记录
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Brxx1002ResponseBody {
	ResHead head;
	Brxx1002Response body;
	public ResHead getHead() {
		return head;
	}
	public void setHead(ResHead head) {
		this.head = head;
	}
	public Brxx1002Response getBody() {
		return body;
	}
	public void setBody(Brxx1002Response body) {
		this.body = body;
	}

	
}

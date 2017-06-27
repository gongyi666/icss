package com.lantone.icss.trans.yiqian.model.ypxx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *	Description: 获取药品用法信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :上午12:19:26
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class UseFrequencyRequest {
		@XmlElement(name = "HOSiPTALID")//医院编码
		private String hosiptalId = "";

		public String getHosiptalId() {
			return hosiptalId;
		}

		public void setHosiptalId(String hosiptalId) {
			this.hosiptalId = hosiptalId;
		}


}

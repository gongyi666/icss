package com.lantone.icss.trans.yiqian.model.ypxx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :上午12:19:26
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class DutasterideRequest {
		@XmlElement(name = "HOSiPTALID")//机构代码
		private String hosiptalId = "";

		@XmlElement(name = "DRGID")//药品化学名ID
		private long drgId;

		public long getDrgId() {
			return drgId;
		}

		public void setDrgId(long drgId) {
			this.drgId = drgId;
		}

		public String getHosiptalId() {
			return hosiptalId;
		}

		public void setHosiptalId(String hosiptalId) {
			this.hosiptalId = hosiptalId;
		}




}

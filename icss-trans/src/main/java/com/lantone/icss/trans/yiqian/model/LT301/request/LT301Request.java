package com.lantone.icss.trans.yiqian.model.LT301.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/***Title: 
*	Description: 获取检查信息
*	Company:杭州朗通信息技术有限公司 
	@author pxz
	@date 2017年5月9日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class LT301Request {
		@XmlElement(name = "hosiptalId")
		private String hosiptalId = "";
		@XmlElement(name = "binId")
		private String binId = "";
		
		public String getBinId() {
			return binId;
		}
		public void setBinId(String binId) {
			this.binId = binId;
		}
		public String getHosiptalId() {
			return hosiptalId;
		}
		public void setHosiptalId(String hosiptalId) {
			this.hosiptalId = hosiptalId;
		}
		
		
		
}

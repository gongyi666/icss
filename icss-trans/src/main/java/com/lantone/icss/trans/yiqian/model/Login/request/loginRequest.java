package com.lantone.icss.trans.yiqian.model.Login.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 获取病人信息
*	Company:杭州朗通信息技术有限公司 
	@author 沈剑峰
	@date 2017年5月8日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class loginRequest {
		@XmlElement(name = "HOSPITALID")
		private String hospitalId = "";
		public String getHospitalId() {
			return hospitalId;
		}
		public void setHospitalId(String hospitalId) {
			this.hospitalId = hospitalId;
		}
			
		
}

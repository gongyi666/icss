package com.lantone.icss.trans.yiqian.model.posandpag.request;

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
public class PosAndPagRequest {
		@XmlElement(name = "HOSPITALID")
		private String hospitalId = "";
		@XmlElement(name = "PARTID")
		private String partId = "";
		@XmlElement(name = "BINIDS")
		private String binIds = "";
		
		public String getHospitalId() {
			return hospitalId;
		}
		public void setHospitalId(String hospitalId) {
			this.hospitalId = hospitalId;
		}
		public String getPartId() {
			return partId;
		}
		public void setPartId(String partId) {
			this.partId = partId;
		}
		public String getBinIds() {
			return binIds;
		}
		public void setBinIds(String binIds) {
			this.binIds = binIds;
		}	
		
		
}

package com.lantone.icss.trans.yiqian.model.posandpag.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 获取当前用户信息
*	Company:杭州朗通信息技术有限公司 
	@author 沈剑峰
	@date 2017年5月8日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class PosAndPagResponse {
		@XmlElement(name = "ID")
		private String ID = "";
		@XmlElement(name = "BINNORMCODE")
		private String binNormCode = "";
		@XmlElement(name = "BINNAME")
		private String binName = "";
		@XmlElement(name = "BINTYPE")
		private String binType = "";
		@XmlElement(name = "CHINASPELL")
		private String chinaSpell = "";
		@XmlElement(name = "FIVESTROKE")
		private String fiveStroke = "";
		@XmlElement(name = "BINSTATE")
		private String binState = "";
		@XmlElement(name = "HOSPITALID")
		private String hospitalId = "";
		@XmlElement(name = "DEPID")
		private String depId = "";
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getBinNormCode() {
			return binNormCode;
		}
		public void setBinNormCode(String binNormCode) {
			this.binNormCode = binNormCode;
		}
		public String getBinName() {
			return binName;
		}
		public void setBinName(String binName) {
			this.binName = binName;
		}
		public String getBinType() {
			return binType;
		}
		public void setBinType(String binType) {
			this.binType = binType;
		}
		public String getChinaSpell() {
			return chinaSpell;
		}
		public void setChinaSpell(String chinaSpell) {
			this.chinaSpell = chinaSpell;
		}
		public String getFiveStroke() {
			return fiveStroke;
		}
		public void setFiveStroke(String fiveStroke) {
			this.fiveStroke = fiveStroke;
		}
		public String getBinState() {
			return binState;
		}
		public void setBinState(String binState) {
			this.binState = binState;
		}
		public String getHospitalId() {
			return hospitalId;
		}
		public void setHospitalId(String hospitalId) {
			this.hospitalId = hospitalId;
		}
		public String getDepId() {
			return depId;
		}
		public void setDepId(String depId) {
			this.depId = depId;
		}
		
}

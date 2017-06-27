package com.lantone.icss.trans.yiqian.model.Login.response;

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
public class loginResponse {
		@XmlElement(name = "HOSPITALID")
		private String hospitalId = "";
		@XmlElement(name = "DEPID")
		private String depId = "";
		@XmlElement(name = "ID")
		private int id = 0;
		@XmlElement(name = "SFFNAME")
		private String sffName = "";
		@XmlElement(name = "SFFSEX")
		private String sffSex = "";
		@XmlElement(name = "SFFCARDTYPE")
		private String sffCardType = "";
		@XmlElement(name = "SFFCARDINFO")
		private String sffCardInfo = "";
		@XmlElement(name = "SFFPROFESSIONAL")
		private String sffProfessional = "";
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public String getSffName() {
			return sffName;
		}
		public void setSffName(String sffName) {
			this.sffName = sffName;
		}
		public String getSffSex() {
			return sffSex;
		}
		public void setSffSex(String sffSex) {
			this.sffSex = sffSex;
		}
		public String getSffCardType() {
			return sffCardType;
		}
		public void setSffCardType(String sffCardType) {
			this.sffCardType = sffCardType;
		}
		public String getSffCardInfo() {
			return sffCardInfo;
		}
		public void setSffCardInfo(String sffCardInfo) {
			this.sffCardInfo = sffCardInfo;
		}
		public String getSffProfessional() {
			return sffProfessional;
		}
		public void setSffProfessional(String sffProfessional) {
			this.sffProfessional = sffProfessional;
		}
		
		
}

package com.lantone.icss.trans.yiqian.model.brxx1001.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 获取病人门诊信息
*	Company:杭州朗通信息技术有限公司 
	@author 孙亚洲
	@date 2016年12月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class Brmzxx1001Request {
		@XmlElement(name = "ZZJGDM")
		private String zzjgdm = "";
		@XmlElement(name = "BRJZHM")
		private String brjzhm = "";
		@XmlElement(name = "BRDAXM")
		private String brdaxm = "";
		@XmlElement(name = "BRSFZH")
		private String brsfzh = "";
		
		@XmlElement(name = "CXKSRQ")
		private Date cxksrq;
		@XmlElement(name = "CXJSRQ")
		private Date csjsrq;
		public String getZzjgdm() {
			return zzjgdm;
		}
		public void setZzjgdm(String zzjgdm) {
			this.zzjgdm = zzjgdm;
		}
		public String getBrjzhm() {
			return brjzhm;
		}
		public void setBrjzhm(String brjzhm) {
			this.brjzhm = brjzhm;
		}
		public String getBrdaxm() {
			return brdaxm;
		}
		public void setBrdaxm(String brdaxm) {
			this.brdaxm = brdaxm;
		}
		public String getBrsfzh() {
			return brsfzh;
		}
		public void setBrsfzh(String brsfzh) {
			this.brsfzh = brsfzh;
		}
		
}

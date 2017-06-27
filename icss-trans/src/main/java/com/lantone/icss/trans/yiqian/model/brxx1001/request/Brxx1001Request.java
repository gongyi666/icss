package com.lantone.icss.trans.yiqian.model.brxx1001.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 获取病人信息
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月31日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class Brxx1001Request {
		@XmlElement(name = "ZZJGDM")
		private String zzjgdm = "";
		@XmlElement(name = "BRJZHM")
		private String brjzhm = "";
		@XmlElement(name = "BRJZLX")
		private String brjzlx = "4";
		@XmlElement(name = "BRDAXM")
		private String brdaxm = "";
		@XmlElement(name = "BRSFZH")
		private String brsfzh = "";
		@XmlElement(name = "BRJZID")
		private String brjzid = "";
		@XmlElement(name = "ZZKSID")
		private String zzksid = "";
		@XmlElement(name = "ZZZGID")
		private String zzzgid = "";
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
		public String getBrjzlx() {
			return brjzlx;
		}
		public void setBrjzlx(String brjzlx) {
			this.brjzlx = brjzlx;
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
		public String getBrjzid() {
			return brjzid;
		}
		public void setBrjzid(String brjzid) {
			this.brjzid = brjzid;
		}
		public String getZzksid() {
			return zzksid;
		}
		public void setZzksid(String zzksid) {
			this.zzksid = zzksid;
		}
		public String getZzzgid() {
			return zzzgid;
		}
		public void setZzzgid(String zzzgid) {
			this.zzzgid = zzzgid;
		}
		
}

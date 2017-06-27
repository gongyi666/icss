package com.lantone.icss.trans.yiqian_new.model.brxx2005.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.core.Constants;
import com.lantone.icss.trans.langtong.model.response.at.HISPatient;
import com.lantone.icss.trans.langtong.model.response.at.HISPubDoctorInfo;
import com.lantone.icss.trans.yiqian.model.ResHead;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class BrxxResponseSearch<T> implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		@XmlElement(name = "status")
		private String status; // 状态
		@XmlElement(name = "startTime")
		private long startTime = Constants.INVALIDATE_VALUE; // 起始时间
		@XmlElement(name = "endTime")
		private long endTime = Constants.INVALIDATE_VALUE; // 结束时间
		@XmlElement(name = "version")
		private String version = "1.0";
		@XmlElement(name = "msg")
		private String msg = Constants.MSG_SUCCESS; // 消息

		private VisitedObject data = null;
		
		
		
		public VisitedObject getData() {
			return data;
		}
		public void setData(VisitedObject data) {
			this.data = data;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public long getStartTime() {
			return startTime;
		}
		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}
		public long getEndTime() {
			return endTime;
		}
		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public int getRet() {
			return ret;
		}
		public void setRet(int ret) {
			this.ret = ret;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public long getTimeConsum() {
			return timeConsum;
		}
		public void setTimeConsum(long timeConsum) {
			this.timeConsum = timeConsum;
		}
		
		
		@XmlElement(name = "ret")
		private int ret = Constants.RET_SUCCESS;
		@XmlElement(name = "token")

		private String token;
		// 耗时
		@XmlElement(name = "timeConsum")
		private long timeConsum;
	
}

package com.lantone.icss.trans.yiqian.model.ypxx.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lantone.core.Constants;

/**
 * 
 *	Description: 获取药品用法信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :2017年5月9日下午11:08:39
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class UsageModeResponseBody<T> implements java.io.Serializable{
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
	
	private UsageModeResponse data=null;
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

	public UsageModeResponse getData() {
		return data;
	}
	public void setData(UsageModeResponse data) {
		this.data = data;
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

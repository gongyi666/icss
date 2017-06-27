package com.lantone.icss.trans.langtong.model.request.at;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 沈剑峰
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class RequestPacs  implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlElement(name = "hospitalId")
	private String hospitalId; //医院编码
	@XmlElement(name = "partId")
	private String partId;  	//部位编码
	@XmlElement(name = "binIds")
	private String[] binIds;    //手段编码

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String[] getBinIds() {
		return binIds;
	}

	public void setBinIds(String[] binIds) {
		this.binIds = binIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	
}

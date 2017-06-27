package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description: 处方信息
 * @author:ztg
 * @time:2017年5月9日 下午2:09:59
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class PrescriptRowRequest {
	@XmlElement(name = "BRCFLX")	
	private String brcflx;			//处方类型(1西2成4草)
	@XmlElement(name = "BRCFTS")
	private String brcfts;			//中药贴数
	@XmlElement(name = "DRUG")		//用药明细
	private List<PrescriptRowDrugRequest> prescriptRowDrugRequest;
	

	
	public List<PrescriptRowDrugRequest> getPrescriptRowDrugRequest() {
		return prescriptRowDrugRequest;
	}
	public void setPrescriptRowDrugRequest(List<PrescriptRowDrugRequest> prescriptRowDrugRequest) {
		this.prescriptRowDrugRequest = prescriptRowDrugRequest;
	}
	public String getBrcflx() {
		return brcflx;
	}
	public void setBrcflx(String brcflx) {
		this.brcflx = brcflx;
	}
	public String getBrcfts() {
		return brcfts;
	}
	public void setBrcfts(String brcfts) {
		this.brcfts = brcfts;
	}
	
	
	
	
	
}

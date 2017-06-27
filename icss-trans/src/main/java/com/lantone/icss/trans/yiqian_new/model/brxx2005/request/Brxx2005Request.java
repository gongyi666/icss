package com.lantone.icss.trans.yiqian_new.model.brxx2005.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/***Title: 
*	Description: 写回疾病电子病历检验检查
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "BODY")
public class Brxx2005Request {
	@XmlElement(name = "PATIINFO")
	private PatiinfoRequest patiinfo;	//病人基本信息
	@XmlElement(name = "HISTORY")
	private HistoryRequest history;		//病历信息
	@XmlElement(name = "DIAGNOSE")
	private DiagnoseRequest diagnose;	//疾病诊断
	@XmlElement(name = "REQUEST")
	private ProjectRequest request;		//检查检验申请单 明细用ITEM
	@XmlElement(name = "PREINDEX")
	private PreindexRequest Preindex;	//明细信息(预检)
	@XmlElement(name = "PRESCRIPT")
	private PrescriptRequest prescriptRequest;	//处方信息

	
	
	public PrescriptRequest getPrescriptRequest() {
		return prescriptRequest;
	}
	public void setPrescriptRequest(PrescriptRequest prescriptRequest) {
		this.prescriptRequest = prescriptRequest;
	}
	public HistoryRequest getHistory() {
		return history;
	}
	public void setHistory(HistoryRequest history) {
		this.history = history;
	}
	public DiagnoseRequest getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(DiagnoseRequest diagnose) {
		this.diagnose = diagnose;
	}
	public ProjectRequest getRequest() {
		return request;
	}
	public void setRequest(ProjectRequest request) {
		this.request = request;
	}
	public PatiinfoRequest getPatiinfo() {
		return patiinfo;
	}
	public void setPatiinfo(PatiinfoRequest patiinfo) {
		this.patiinfo = patiinfo;
	}
	public PreindexRequest getPreindex() {
		return Preindex;
	}
	public void setPreindex(PreindexRequest preindex) {
		Preindex = preindex;
	}
	
}

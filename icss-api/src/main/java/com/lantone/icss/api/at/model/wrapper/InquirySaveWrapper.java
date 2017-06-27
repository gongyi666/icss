package com.lantone.icss.api.at.model.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lantone.icss.api.at.model.InquiryInfo;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.yiqian.DrugYiQian;
import com.lantone.icss.api.his.model.HisYiQianLisPacsInfo;
import com.lantone.icss.api.his.model.HisYiQianPreindexInfo;
import com.lantone.icss.api.kl.model.DrugInfo;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;

public class InquirySaveWrapper implements Serializable{

	private InquiryInfoWrapper info;				//主表信息
	private List<InquiryDetailWrapper> details;		//明细信息
	private PatientInfo toPat;						//病人基础信息
	private List<InquiryDetailWrapper> toHistory;	//病历信息
	private List<DiseaseInfoWrapper> toDiagnose;	//诊断信息
	private List<HisYiQianLisPacsInfo> toRequest;	//化验和器查
	private List<HisYiQianPreindexInfo> toPreindex; //预检字典
	private List<DrugYiQian> toPrescript;			//处方
	public InquiryInfoWrapper getInfo() {
		return info;
	}
	public void setInfo(InquiryInfoWrapper info) {
		this.info = info;
	}
	public List<InquiryDetailWrapper> getDetails() {
		return details;
	}
	public void setDetails(List<InquiryDetailWrapper> details) {
		this.details = details;
	}
	public PatientInfo getToPat() {
		return toPat;
	}
	public void setToPat(PatientInfo toPat) {
		this.toPat = toPat;
	}
	public List<InquiryDetailWrapper> getToHistory() {
		return toHistory;
	}
	public void setToHistory(List<InquiryDetailWrapper> toHistory) {
		this.toHistory = toHistory;
	}
	public List<DiseaseInfoWrapper> getToDiagnose() {
		return toDiagnose;
	}
	public void setToDiagnose(List<DiseaseInfoWrapper> toDiagnose) {
		this.toDiagnose = toDiagnose;
	}
	public List<HisYiQianLisPacsInfo> getToRequest() {
		return toRequest;
	}
	public void setToRequest(List<HisYiQianLisPacsInfo> toRequest) {
		this.toRequest = toRequest;
	}
	public List<HisYiQianPreindexInfo> getToPreindex() {
		return toPreindex;
	}
	public void setToPreindex(List<HisYiQianPreindexInfo> toPreindex) {
		this.toPreindex = toPreindex;
	}
	public List<DrugYiQian> getToPrescript() {
		return toPrescript;
	}
	public void setToPrescript(List<DrugYiQian> toPrescript) {
		this.toPrescript = toPrescript;
	}
		
	


	
}

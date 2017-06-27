package com.lantone.icss.trans.yiqian.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.lantone.icss.trans.langtong.model.request.at.RequestPacs;
import com.lantone.icss.trans.yiqian.model.LT300.request.LT300Request;
import com.lantone.icss.trans.yiqian.model.LT301.request.LT301Request;
import com.lantone.icss.trans.yiqian.model.Login.request.loginRequest;
import com.lantone.icss.trans.yiqian.model.brxx1001.request.Brxx1001Request;
import com.lantone.icss.trans.yiqian.model.brxx1002.request.Brxx1002Request;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.Brxx2005Request;
import com.lantone.icss.trans.yiqian.model.brxx2005.request.BrxxSearchRequest;
import com.lantone.icss.trans.yiqian.model.deptInfo.request.DeptInfoRequest;
import com.lantone.icss.trans.yiqian.model.drugDetail.request.DrugDetailRequest;
import com.lantone.icss.trans.yiqian.model.historyCaseInfo.request.HistoryCaseInfoRequest;
import com.lantone.icss.trans.yiqian.model.lisDetail.request.LisDetailRequest;
import com.lantone.icss.trans.yiqian.model.pacs.request.PacsInfoRequest;
import com.lantone.icss.trans.yiqian.model.part.request.PartInfoRequest;
import com.lantone.icss.trans.yiqian.model.patientInfo.request.PatientInfoRequest;
import com.lantone.icss.trans.yiqian.model.posandpag.request.PosAndPagRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DiseaseInfoRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DutaDictRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.DutasterideRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.InventoryRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.UsageModeRequest;
import com.lantone.icss.trans.yiqian.model.ypxx.request.UseFrequencyRequest;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ROOT")

@XmlSeeAlso({Brxx1001Request.class,Brxx2005Request.class,Brxx1002Request.class,loginRequest.class,DrugDetailRequest.class,PatientInfoRequest.class,LT301Request.class,LT300Request.class,loginRequest.class,RequestPacs.class,DutasterideRequest.class,InventoryRequest.class,PartInfoRequest.class,PacsInfoRequest.class,HistoryCaseInfoRequest.class,LisDetailRequest.class,UsageModeRequest.class,DeptInfoRequest.class,UseFrequencyRequest.class,DiseaseInfoRequest.class,DutaDictRequest.class,BrxxSearchRequest.class})


public class ReqBody<T> {
	private ReqHead HEAD;
	
	private T BODY;



	public ReqHead getHead() {
		return HEAD;
	}

	public void setHead(ReqHead hEAD) {
		HEAD = hEAD;
	}

	public T getBody() {
		return BODY;
	}

	public void setBody(T bODY) {
		BODY = bODY;
	}


}

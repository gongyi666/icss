package com.lantone.icss.trans.chuangye.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.trans.chuangye.model.ReqBody;
import com.lantone.icss.trans.chuangye.model.ReqHead;
import com.lantone.icss.trans.chuangye.model.patientInfo.request.PatientInfoRequest;
import com.lantone.icss.trans.chuangye.model.patientInfo.response.PatientInfoResponseBody;
import com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoap;
import com.lantone.icss.trans.chuangye.webservicetest.CYInterfaceSoapProxy;
import com.lantone.icss.trans.chuangye.model.patientInfo.response.HISPatient;

@Service
public class CYPatientInfoService {
	private static Logger logger = LoggerFactory.getLogger(CYPatientInfoService.class);

	public PatientInfo remotePatientInfo(String patientId) throws RemoteException, ParseException {
		//请求交易头head
		ReqHead head = new ReqHead();
		head.setTranType("ICSS_GET_PATIENT");
		head.setTranKey("ICSS_GET_PATIENT");
		head.setStffNo("");
		head.setHospitalId("");
		head.setDepartId("");
		//body
		PatientInfoRequest request = new PatientInfoRequest();
		request.setPatientId(patientId);
		//将head、body组成一个请求
		ReqBody<PatientInfoRequest> body = new ReqBody<PatientInfoRequest>();
		body.setHead(head);
		body.setBody(request);

		String reqXml = JaxbUtil.convertToXml(body);
		logger.info("请求Xml : " + reqXml);
		CYInterfaceSoap soap = new CYInterfaceSoapProxy();
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.assistTreat(reqXml, holder);
		String resXml = holder.value;
		logger.info("返回 Xml : " + resXml);
		System.out.println(resXml);
		//将返回的xml格式的数据拆包
		PatientInfoResponseBody resBody = (PatientInfoResponseBody) JaxbUtil.converyToJavaBean(resXml,
				PatientInfoResponseBody.class);
		
		PatientInfo patientInfo = new PatientInfo();
		if(resBody!=null){
			if(resBody.getRet()==0){
				HISPatient hISPatien = resBody.getData();
				patientInfo.setId(Long.valueOf(hISPatien.getId()));
				patientInfo.setName(hISPatien.getPatName());
				patientInfo.setSex(hISPatien.getPatSex());
				if (hISPatien.getPatBirthday() != null) {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");//小写的mm表示的是分钟  
					patientInfo.setBirthday(sdf.parse(hISPatien.getPatBirthday()));
				}
				patientInfo.setHisCode(patientId);
				patientInfo.setNatureId(hISPatien.getNatureId());
				patientInfo.setIdNo(hISPatien.getPatIdentityNum());
				patientInfo.setAddress(hISPatien.getPatFamAddress());
				patientInfo.setPostcode(hISPatien.getPatPostcode());
				patientInfo.setContactPhone(hISPatien.getPatContactPhone());
				patientInfo.setContacts(hISPatien.getPatContacts());
				patientInfo.setPhone(hISPatien.getPatPhone());
				patientInfo.setWorkUnit(hISPatien.getPatWorkUnit());
				patientInfo.setOperation(hISPatien.getPatOperation());
				patientInfo.setCountry(hISPatien.getPatCountry());
				patientInfo.setNationality(hISPatien.getPatNationality());
				patientInfo.setMatrimony(hISPatien.getPatMatrimony());
				patientInfo.setHisPrevious(hISPatien.getPatHisPrevious());
				patientInfo.setHisAllergic(hISPatien.getPatHisAllergic());
				patientInfo.setHisFamily(hISPatien.getPatHisFamily());
				patientInfo.setRecordDate(hISPatien.getPatRecordDate());
				patientInfo.setMemGrade(hISPatien.getPatMemGrade());
				patientInfo.setCardNum(hISPatien.getPatCardNum());
				patientInfo.setFeeId(hISPatien.getFeeId());
	
				return patientInfo;
			}
			return null;
		}
		return null;
	}
}

package com.lantone.icss.trans.yiqian_new.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lantone.core.utils.JaxbUtil;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.yiqian_new.model.ReqBody;
import com.lantone.icss.trans.yiqian_new.model.ReqHead;
import com.lantone.icss.trans.yiqian_new.model.patientInfo.request.PatientInfoRequest;
import com.lantone.icss.trans.yiqian_new.model.patientInfo.response.PatientInfoResponseBody;
import com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoap;
import com.lantone.icss.trans.yiqian_new.webservicetest.EtrackInterfaceSoapProxy;
import com.lantone.icss.trans.yiqian_new.model.patientInfo.response.HISPatient;

@Service
public class NewPatientInfoService {
	private static Logger logger = LoggerFactory.getLogger(NewPatientInfoService.class);

	public PatientInfo remotePatientInfo(String patientId) throws RemoteException, ParseException {
		//请求交易头head
		ReqHead head = new ReqHead();
		head.setTranType("LT202");
		head.setTranKey("LT202");
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
		EtrackInterfaceSoap soap = new EtrackInterfaceSoapProxy(InitConfig.get("yiqian.webservice.url"));
		System.out.println(InitConfig.get("yiqian.webservice.url"));
		javax.xml.rpc.holders.StringHolder holder = new javax.xml.rpc.holders.StringHolder();
		soap.assistTreat(reqXml, holder);
		// Test test = new Test();
		String resXml = holder.value;
		// String resXml=test.getBRXX1001Response();
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
				if(hISPatien.getPatSex().equals("男")){
					patientInfo.setSex("1");
				}else if(hISPatien.getPatSex().equals("女")){
					patientInfo.setSex("2");
				}
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

package com.lantone.icss.trans.langtong.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestPatient;
import com.lantone.icss.trans.langtong.model.response.at.HISPatient;
import com.lantone.icss.trans.langtong.model.response.at.ResponsePatient;
import com.lantone.icss.trans.langtong.util.LangTongUtil;


@Controller
@RequestMapping("/langtong/at")
public class PatBasicInfoController {
	private static Logger logger = LoggerFactory.getLogger(PatBasicInfoController.class);
	@ResponseBody
	@RequestMapping("/patient")
	public Response<PatientInfo> PatientInfo(@RequestBody PatientInfoWrapper patientInfoWrapper){
		Response<PatientInfo> response = new Response<PatientInfo>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="202";
		String tranKey="202";
		String stffNo=patientInfoWrapper.getDoctorCode();
		String hospitalId=patientInfoWrapper.getHospitalCode();
		String departId =patientInfoWrapper.getDeptNo();
		
		RequestPatient requestPatient = new RequestPatient();
		requestPatient.setPatientId(patientInfoWrapper.getHisCode());
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestPatient);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponsePatient> httpApi = new HttpApi<ResponsePatient>();
		logger.info("------验证用户-------");
		ResponsePatient responsePatient = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponsePatient.class);
		if(responsePatient.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		HISPatient hispatient = responsePatient.getData();
		PatientInfo patientinfo = putPatientUtil(hispatient);		
		response.setData(patientinfo);
		}else{
			 logger.error("没有该病人信息");
			 return response.failure("没有该病人信息");
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		return response.success();
		
	}
	
	public Date stringToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	private PatientInfo putPatientUtil(HISPatient patient){
		PatientInfo patientInfo = new PatientInfo();
//		patientInfo.setId(patient.getId());
		patientInfo.setHisCode(patient.getId());
//		patientInfo.setHospitalCode();
		patientInfo.setName(patient.getPatName());
		patientInfo.setSex(patient.getPatSex());
		patientInfo.setBirthday(this.stringToDate(patient.getPatBirthday()));
		patientInfo.setIdType("1");
		patientInfo.setIdNo(patient.getPatIdentityNum());
		patientInfo.setAddress(patient.getPatFamAddress());
		patientInfo.setPhone(patient.getPatPhone());
		patientInfo.setIdentityNum(patient.getPatIdentityNum());
		patientInfo.setNatureId(patient.getNatureId());
		patientInfo.setPostcode(patient.getPatPostcode());
		patientInfo.setContactPhone(patient.getPatContactPhone());
		patientInfo.setContacts(patient.getPatContacts());
		patientInfo.setWorkUnit(patient.getPatWorkUnit());
		patientInfo.setOperation(patient.getPatOperation());
		patientInfo.setCountry(patient.getPatCountry());
		patientInfo.setNationality(patient.getPatNationality());
		patientInfo.setMatrimony(patient.getPatMatrimony());
		patientInfo.setHisPrevious(patient.getPatHisPrevious());
		patientInfo.setHisAllergic(patient.getPatHisAllergic());
		patientInfo.setHisFamily(patient.getPatHisFamily());
		patientInfo.setRecordDate(patient.getPatRecordDate());
		patientInfo.setMemGrade(patient.getPatMemGrade());
		patientInfo.setCardNum(patient.getPatCardNum());
		patientInfo.setFeeId(patient.getFeeId());
//		patientInfo.setStatus();
//		patientInfo.setRemark();
		return patientInfo;
	}	
}

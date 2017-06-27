package com.lantone.icss.trans.lishui.controller;

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
import com.lantone.icss.trans.lishui.model.RequestData;
import com.lantone.icss.trans.lishui.model.request.RequestPatient;
import com.lantone.icss.trans.lishui.model.response.LsPatient;
import com.lantone.icss.trans.lishui.model.response.ResponsePatient;
import com.lantone.icss.trans.lishui.util.LangTongUtil;


@Controller
@RequestMapping("/lishui/at")
public class LsPatientInfoController {

	private static Logger logger = LoggerFactory.getLogger(LsPatientInfoController.class);
	@ResponseBody
	@RequestMapping("/patient_info")
	public Response<PatientInfo> getPatientInfo_ls(@RequestBody PatientInfoWrapper patientInfoWrapper){
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
		ResponsePatient responsePatient = httpApi.doPostReplace(InitConfig.get("lishui.his.url"), requestData, ResponsePatient.class);
		if(responsePatient.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		LsPatient hispatient = responsePatient.getData();
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
	
	private PatientInfo putPatientUtil(LsPatient patient){
		PatientInfo patientInfo = new PatientInfo();
//		patientInfo.setId(patient.getId());
		patientInfo.setHisCode(patient.getPatientId());
//		patientInfo.setHospitalCode();
		patientInfo.setName(patient.getPatName());
		patientInfo.setSex(patient.getPatSex());
		if(patient.getPatBirthday()!=null){
			patientInfo.setBirthday((patient.getPatBirthday()));
		}
		patientInfo.setNatureId(patient.getNatureId());
		patientInfo.setIdentityNum(patient.getPatIdentityNum());
		patientInfo.setAddress(patient.getPatFamAddress());
		patientInfo.setPostcode(patient.getPatPostcode());
		patientInfo.setContactPhone(patient.getPatContactPhone());
		patientInfo.setContacts(patient.getPatContacts());
		patientInfo.setPhone(patient.getPatPhone());
		patientInfo.setWorkUnit(patient.getPatWorkUnit());
		patientInfo.setHisPrevious(patient.getPatHisPrevious());
		patientInfo.setHisAllergic(patient.getPatHisAllergic());
		patientInfo.setHisFamily(patient.getPatHisFamily());
		if(patient.getPatRecordDate()!=null){
			patientInfo.setRecordDate(patient.getPatRecordDate().toString());
		}
		return patientInfo;
	}	
}

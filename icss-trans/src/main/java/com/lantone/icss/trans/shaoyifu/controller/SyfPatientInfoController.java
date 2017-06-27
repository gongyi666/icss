package com.lantone.icss.trans.shaoyifu.controller;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.Constants;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.core.utils.mapper.JsonMapper;
import com.lantone.icss.api.at.model.wrapper.PatientInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.controller.PubDoctorInfoController;
import com.lantone.icss.trans.shaoyifu.model.RequestData;
import com.lantone.icss.trans.shaoyifu.model.request.RequestPatientInfo;
import com.lantone.icss.trans.shaoyifu.model.response.ResponsePatientInfo;
import com.lantone.icss.trans.shaoyifu.model.response.SyfPatientInfo;
import com.lantone.icss.trans.shaoyifu.util.LangTongUtil;
import com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoap;
import com.lantone.icss.trans.shaoyifu.webservice.ServiceAlipaySoapProxy;

@Controller
@RequestMapping("/syf/at")
public class SyfPatientInfoController {

	private static Logger logger=LoggerFactory.getLogger(PubDoctorInfoController.class);
	
	/**
	 * @Description:获取患者信息
	 * @author CSP
	 * @time : 2017年6月6日 下午15:30
	 */
	@ResponseBody
	@RequestMapping("/patient_info")
	public Response<PatientInfoWrapper> getDoctorInfoList(@RequestBody PatientInfoWrapper patientInfoWrapper){
		Response<PatientInfoWrapper> response=new Response<PatientInfoWrapper>();
		response.start();
		try {
			String authentication="";
			String empid="76032";
			String hospitalCode="A001";
			String sourceSystem="06";
			String tradeCode="9028";
			String tradeTime="2017-06-06 00:01:34";
			RequestPatientInfo requestPatientInfo=new RequestPatientInfo();
			requestPatientInfo.setPatientId(patientInfoWrapper.getHisCode());
			
			
			RequestData<RequestPatientInfo> requestData = LangTongUtil.getRequestData(authentication,requestPatientInfo,empid,hospitalCode,sourceSystem,tradeCode,tradeTime);
			ServiceAlipaySoap soap = new ServiceAlipaySoapProxy();
			JsonMapper mapper = JsonMapper.nonDefaultMapper();
			String res = soap.apply(mapper.toJson(requestData));
			
			ResponsePatientInfo responsePatientInfo = mapper.fromJson(res, ResponsePatientInfo.class);
			System.out.println("*********************res:" + res);
//			HttpApi<ResponsePatientInfo> api = new HttpApi<ResponsePatientInfo>();
//		
//			ResponsePatientInfo responsePatientInfo=api.doPost(InitConfig.get("shaoyifu.webservice.url"), requestData, ResponsePatientInfo.class);
			if(responsePatientInfo.getResult().equals("0")){
				//封装返回的对象
				SyfPatientInfo syfpatieninfo = responsePatientInfo.getData();
				PatientInfoWrapper patieninfo = patieninfo(syfpatieninfo);
				response.setData(patieninfo);
			}else{
				logger.error("获取患者信息失败！");
				return response.failure("获取患者信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取患者信息失败");
		}
		return response.success();
	}
	
	private PatientInfoWrapper patieninfo(SyfPatientInfo syfpatieninfo)  throws RemoteException, ParseException{
		PatientInfoWrapper returninfo = new PatientInfoWrapper();		
			returninfo.setHisCode(syfpatieninfo.getPatientId());;
			returninfo.setName(syfpatieninfo.getPatName());
			returninfo.setSex(syfpatieninfo.getPatSex().equals("F")?"2":"1");
			if (syfpatieninfo.getPatBirthday() != null) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//小写的mm表示的是分钟  
				returninfo.setBirthday(sdf.parse(syfpatieninfo.getPatBirthday()));
			}
			returninfo.setNatureId(syfpatieninfo.getNatureId());
			returninfo.setIdNo(syfpatieninfo.getPatIdentityNum());
			returninfo.setAddress(syfpatieninfo.getPatFamAddress());
			returninfo.setPostcode(syfpatieninfo.getPatPostcode());
			returninfo.setContactPhone(syfpatieninfo.getPatContactPhone());
			returninfo.setContacts(syfpatieninfo.getPatContacts());
			returninfo.setPhone(syfpatieninfo.getPatPhone());
			returninfo.setWorkUnit(syfpatieninfo.getPatWorkUnit());
			returninfo.setOperation(syfpatieninfo.getPatOperation());
			returninfo.setCountry(syfpatieninfo.getPatCountry());
			returninfo.setNationality(syfpatieninfo.getPatNationality());
			returninfo.setMatrimony(syfpatieninfo.getPatMatrimony());
			returninfo.setHisPrevious(syfpatieninfo.getPatHisPrevious());
			returninfo.setHisAllergic(syfpatieninfo.getPatHisAllergic());
			returninfo.setHisFamily(syfpatieninfo.getPatHisFamily());
			returninfo.setRecordDate(syfpatieninfo.getPatRecordDate());
			returninfo.setMemGrade(syfpatieninfo.getPatMemGrade());
			returninfo.setCardNum(syfpatieninfo.getPatCardNum());
			returninfo.setFeeId(syfpatieninfo.getFeeId());
			
			returninfo.setIdType(Constants.COMMON_STRING_1);
			
			return returninfo;
	}
}

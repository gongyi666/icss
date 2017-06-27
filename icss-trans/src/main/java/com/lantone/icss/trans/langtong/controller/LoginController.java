package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.sys.Login;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.login.RequestLogin;
import com.lantone.icss.trans.langtong.model.response.login.HISLogin;
import com.lantone.icss.trans.langtong.model.response.login.ResponseLogin;
import com.lantone.icss.trans.langtong.util.LangTongUtil;


@Controller
@RequestMapping("/langtong")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@ResponseBody
	@RequestMapping("/login/verification")
	public Response<DoctorInfo> userVerification(@RequestBody Login login){
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="100";
		String tranKey="100";
		String stffNo="";
		String hospitalId="";
		String departId ="";
		
		RequestLogin requestLogin = new RequestLogin();
		requestLogin.setHospitalId(login.getUnitId());
		requestLogin.setLoginName(login.getUserName());
		requestLogin.setPassWord(login.getPassWord());
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestLogin);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponseLogin> httpApi = new HttpApi<ResponseLogin>();
		logger.info("------验证用户-------");
		ResponseLogin responseDoctor = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseLogin.class);
		if(responseDoctor.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		List<HISLogin> hisLogines = responseDoctor.getData();
		HISLogin hisLogin = hisLogines.get(0);
		DoctorInfo doctorInfo = putDoctorUtil(hisLogin);		
		response.setData(doctorInfo);
		}else{
			 logger.error("没有该职员信息");
			 return response.failure("没有该职员信息");
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		return response.success();
		
	}
	
	private DoctorInfo putDoctorUtil(HISLogin login){
		DoctorInfo doctorInfo = new DoctorInfo();
		doctorInfo.setHisCode(login.getHospitalId());
		doctorInfo.setId(Long.valueOf(login.getId()));
		doctorInfo.setName(login.getSffName());
		doctorInfo.setSex(login.getSffSex());
		doctorInfo.setIdType(login.getSffCardType());
		doctorInfo.setIdNo(login.getSffCardInfo());
		doctorInfo.setDeptNo(login.getDepId());
		return doctorInfo;
	}
}

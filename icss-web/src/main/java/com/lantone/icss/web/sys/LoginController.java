package com.lantone.icss.web.sys;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.sys.Login;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.trans.res.ResponseDoctor;

@Controller
@RequestMapping("/sys/login")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ResponseBody
	@RequestMapping("/verification")
	public Response<DoctorInfo> userVerification(Login login){
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try{
			HttpApi<ResponseDoctor> httpApi = new HttpApi<ResponseDoctor>();
			logger.info("------验证用户-------");
			ResponseDoctor responseDoctor = httpApi.doPost(InitConfig.get("login.verification"), login, ResponseDoctor.class);
			if(responseDoctor.getRet()==0){
				response.setData(responseDoctor.getData());
				return response.success();	
			}else{
				logger.error("登录验证出错!");
				return response.failure("登录验证出错!");	
			}
			
		}catch(Exception e){
			logger.error("登录验证出错!");
			return response.failure("登录验证出错!");
		}
		
	}

	@ResponseBody
	@RequestMapping("/loginTest")
	public Response<Map<String, Object>> test(){
		Response<Map<String, Object>> response = new Response<Map<String, Object>>();
		response.start();
		try{
			Login login = new Login();
			login.setUserName("admin");
			login.setPassWord("123");
			login.setUnitId("123131");
			this.userVerification(login);
		}catch(Exception e){
			logger.error("登录验证出错!");
		}
		return null;
		
	}
}

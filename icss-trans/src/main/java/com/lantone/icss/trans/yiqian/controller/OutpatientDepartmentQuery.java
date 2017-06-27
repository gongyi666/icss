/**   
 * @Company: 杭州朗通信息技术有限公司 
 * @Department: 系统软件部 
 * @Description: 朗通智能辅助诊疗系统 
 * @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
 */
package com.lantone.icss.trans.yiqian.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.trans.yiqian.service.YiQianRemoteService;

/**
 * 
 * @Title: PrescriptionQuery.java
 * @Package com.lantone.at.controller
 * @Description:病人基本信息查询
 * @author 孙亚洲
 * @date 2016年12月30日 下午16:53:39
 * @version V1.0
 */
@Controller
@RequestMapping("/yiqian/at/OutpatientDepartment_info")
public class OutpatientDepartmentQuery {
	private static Logger logger = LoggerFactory.getLogger(OutpatientDepartmentQuery.class);

	@Autowired
	YiQianRemoteService service;

	@RequestMapping(value = "/get_OutpatientDepartment_info", method = RequestMethod.POST)
	@ResponseBody
	public Response<List<PatientInfo>> getOutpatientDepartmentInfo(@RequestBody PatientInfo getHisPatientInfo) throws Exception {
		Response<List<PatientInfo>> response = new Response<List<PatientInfo>>();
		response.start();
		try {
			Date startDate,endDate;
			String brdaxm = "";
			String strUTF8 = URLDecoder.decode(brdaxm, "iso-8859-1");
			brdaxm = new String(strUTF8.getBytes("iso-8859-1"), "UTF-8");
			logger.info("------------病人信息接口------------");
			
//			List<PatientInfo> patientInfoes = service.remoteOutpatientDepartmentInfo(getHisPatientInfo.getHospitalCode(),getHisPatientInfo.getName(),
//					getHisPatientInfo.getHisCode(), startDate,endDate);
//			logger.info("------------病人数据返回------------");
//			response.setData(patientInfoes);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}

	// 测试 by syz
	@RequestMapping(value = "/get_patient_info1", method = RequestMethod.POST)
	@ResponseBody
	public Response<List<PatientInfo>> getPatientInfo1() throws Exception {
		Response<String> response = new Response<String>();
		response.start();
		try {
			PatientInfo pi = new PatientInfo();
			pi.setHospitalCode("s");
			pi.setHisCode("y");
			pi.setName("z");
			pi.setIdNo("s");
			pi.setHisCode("u");
			getOutpatientDepartmentInfo(pi);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

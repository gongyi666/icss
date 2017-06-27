/**   
 * @Company: 杭州朗通信息技术有限公司 
 * @Department: 系统软件部 
 * @Description: 朗通智能辅助诊疗系统 
 * @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
 */
package com.lantone.icss.trans.yiqian.controller;

import java.net.URLDecoder;
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
import com.lantone.icss.api.at.model.HisPatientInfo;
import com.lantone.icss.api.at.model.wrapper.RecordInfoWrapper;
import com.lantone.icss.trans.yiqian.service.YiQianRemoteService;

/**
 * 
 * @Title: PatientInfoController.java
 * @Package com.lantone.at.controller
 * @Description:获取病人信息(关联就诊记录,处方,诊断结果)
 * @author 楼辉荣(Fyeman)
 * @date 2015年7月20日 下午16:53:39
 * @version V1.0
 */
@Controller
@RequestMapping("/yiqian/at/record_info")
public class RecordInfoController {
	private static Logger logger = LoggerFactory.getLogger(RecordInfoController.class);

	@Autowired
	YiQianRemoteService service;

	@RequestMapping(value = "/get_record_info", method = RequestMethod.POST)
	@ResponseBody
	public Response<List<RecordInfoWrapper>> getRecordInfo(@RequestBody HisPatientInfo getHisPatientInfo) throws Exception {
		Response<List<RecordInfoWrapper>> response = new Response<List<RecordInfoWrapper>>();
		response.start();
		try {
			String brdaxm = "";
			String strUTF8 = URLDecoder.decode(brdaxm, "iso-8859-1");
			brdaxm= new String(strUTF8.getBytes("iso-8859-1"),"UTF-8");
			logger.info("-----------就诊记录接口------------");
			List<RecordInfoWrapper> patientRecords = service.remotePatientRecords(getHisPatientInfo.getHospitalCode(),getHisPatientInfo.getPatientNo(),getHisPatientInfo.getPatientName());			
			logger.info("------------历史记录数据返回------------");
			response.setData(patientRecords);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}

	// 测试 by syz
	@RequestMapping(value = "/get_record_info1", method = RequestMethod.POST)
	@ResponseBody
	public Response<List<RecordInfoWrapper>> getPatientInfo1() throws Exception {
		Response<String> response = new Response<String>();
		response.start();
		try {
			HisPatientInfo getHisPatientInfo = new HisPatientInfo();
			getHisPatientInfo.setHospitalCode("100603");
			getHisPatientInfo.setPatientNo("W10949472");
			getHisPatientInfo.setDeptNo("");
			getHisPatientInfo.setDoctorNo("ADMIN");
			getHisPatientInfo.setPatientName("朱慧");
			getRecordInfo(getHisPatientInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

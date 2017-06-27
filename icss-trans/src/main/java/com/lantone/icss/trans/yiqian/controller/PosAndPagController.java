/**   
 * @Company: 杭州朗通信息技术有限公司 
 * @Department: 系统软件部 
 * @Description: 朗通智能辅助诊疗系统 
 * @Address: 浙江省杭州市西湖区西斗门路3号 天堂软件园D-7B 
 */
package com.lantone.icss.trans.yiqian.controller;

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
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.api.his.model.HisLisInfo;
import com.lantone.icss.api.his.model.HisPacsInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.PacsStructuring;
import com.lantone.icss.api.kl.model.PacsInfo;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.trans.langtong.model.response.at.HISPacs;
import com.lantone.icss.trans.yiqian.service.PosAndPagService;
import com.lantone.icss.trans.yiqian.service.UserService;

/**
 * 
 * @Title: PatientInfoController.java
 * @Package com.lantone.at.controller
 * @Description:根据部位+ID获取套餐信息
 * @author 沈剑峰
 * @date 2017年5月8日
 */
@Controller
@RequestMapping("/yiqian/at/posandpag_info")
public class PosAndPagController {
	private static Logger logger = LoggerFactory.getLogger(PosAndPagController.class);

	@Autowired
	PosAndPagService service;

	@RequestMapping(value = "/get_posandpag_info")
	@ResponseBody
	public Response<PacsInfo> HisPacsInfo(@RequestBody PacsInfoWrapper pacs){
		Response<PacsInfo> response = new Response<PacsInfo>();
		response.start();
		try {
			logger.info("------------部位------------"+pacs.getPartCode()+"-----------手段--------"+pacs.getApparatusCode());
			logger.info("------------------------");
			PacsInfo HisPacsInfo = service.remotePosAndPagInfo(pacs.getHospitalCode(), pacs.getHisPartCode(), pacs.getHisApparatusCode());
			logger.info("------------套餐数据返回------------");
			response.setData(HisPacsInfo);
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据查看出错!", loadException);
			return response.failure("数据查看出错!");
		}
		return response.success();
	}

	/*// 测试 by syz
	@RequestMapping(value = "/get_patient_info1", method = RequestMethod.POST)
	@ResponseBody
	public Response<DoctorInfo> getDoctorInfo1() throws Exception {
		Response<String> response = new Response<String>();
		response.start();
		try {
			HISLogin gethislogin = new HISLogin();
			gethislogin.setHospitalId(hospitalId);
			getHisPatientInfo.setPatientNo("1215");
			getHisPatientInfo.setDeptNo("");
			getHisPatientInfo.setDoctorNo("ADMIN");
			getPatientInfo(getHisPatientInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
}

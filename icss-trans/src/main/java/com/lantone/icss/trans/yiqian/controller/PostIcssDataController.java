package com.lantone.icss.trans.yiqian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.Constants;
import com.lantone.core.api.Response;
import com.lantone.icss.api.at.model.PostHisInfo;
import com.lantone.icss.trans.yiqian.model.brxx2005.response.Brxx2005ResponseBody;
import com.lantone.icss.trans.yiqian.service.YiQianRemoteService;


/***Title: 
*	Description: 疾病电子病历检验检查写回
*	Company:杭州朗通信息技术有限公司 
	@author 吴文俊
	@date 2016年5月30日
*/
@Controller
@RequestMapping("/yiqian/at/postIcssData")
public class PostIcssDataController {
	private static Logger logger = LoggerFactory.getLogger(PostIcssDataController.class);
	@Autowired
	YiQianRemoteService service;
	@RequestMapping(value = "/post_icss_data", method = RequestMethod.POST)
	@ResponseBody	
	public Response<String> postDeseaseLisPacsAllList(@RequestBody PostHisInfo  postHisInfo) throws Exception {
		Response<String> response = new Response<String>();
		response.start();
		try {
			// 调接口
			logger.info("------------疾病写回接口------------");
			Response<Brxx2005ResponseBody> brxx2005response = new Response<Brxx2005ResponseBody>();
			
			brxx2005response = service.postDeseaseinfoLisPacs(postHisInfo);
			if (Integer.valueOf(brxx2005response.getData().getBrxx2005Respons().getRet()) != Constants.RET_SUCCESS) {
				return response;
			}
		} catch (Exception loadException) {
			loadException.printStackTrace();
			logger.error("数据写入出错!", loadException);
			return response.failure("数据写入出错!");
		}
//		System.out.println(response.success().getMsg());
		return response.success();
	}
//	@RequestMapping(value = "/test", method = RequestMethod.POST)
//	@ResponseBody	
//	public Response<String> test() throws Exception {
//		Response<String> response = new Response<String>();
//		response.start();
//		try {
//			// 调接口
//			logger.info("------------疾病写回接口------------");
//			Response<Brxx2005ResponseBody> brxx2005response = new Response<Brxx2005ResponseBody>();
//			 PostHisInfo  postHisInfo = new PostHisInfo();
//			 List<DiseaseInfo> diseaseList = Lists.newArrayList();
//			 DiseaseInfo diseaseInfo = new DiseaseInfo();
//			 diseaseInfo.setId(1L);
//			 diseaseInfo.setIcd10Code("A01.200");
//			 diseaseInfo.setHisCode("10");
//			 diseaseInfo.setDescription("副伤寒乙");
//			 diseaseList.add(diseaseInfo);
//			 postHisInfo.setDiseaseList(diseaseList);
//			 
//			 PatientRecord patientRecord = new PatientRecord();
//			 patientRecord.setDeptNo("794");
//			 patientRecord.setDoctorNo("ADMIN");
//			 patientRecord.setClinicTime(new Date());
//			 patientRecord.setId(1L);
//			 postHisInfo.setPatientRecord(patientRecord);
//			 
//			 PatientInfo patientInfo = new PatientInfo();
//			 patientInfo.setHospitalId("1006");
//			 patientInfo.setPatientHisNo("1215");
//			 patientInfo.setName("朗通测试");
//			 postHisInfo.setPatientInfo(patientInfo);
//			 postHisInfo.setChiefDesc("糖尿病病史1年，伴无其他明显症状、头晕");
//			 postHisInfo.setPresentDesc("患者于1年前确诊为Ⅰ型糖尿病、Ⅱ型糖尿病，最高血糖达100mmol/L。近期视力正常。伴头晕；伴意识障碍；伴反应迟钝；伴表情淡漠；伴消化不良；伴腹泻；伴便秘；伴血压下降；伴多饮；伴多尿；伴体重减轻；伴恶心；伴呕吐。患者平素服用AA进行降糖治疗，血糖情况控制良好，本次就诊测得空腹血糖为500mmol/L，餐后2h血糖为600mmol/L，无其他明显症状。患者患病以来，神志清楚，食欲可，精神佳，睡眠良好，体重无明显变化，大便正常，小便正常。");
//			 postHisInfo.setPhysicalDesc("发育正常，营养良好，神志清楚，面容安静，自主体位，查体合作。全身皮肤及黏膜无黄染，未见出血点、瘀斑及皮疹。全身淋巴结未及明显肿大。瞳孔正常大小，等大，等圆，对光反射灵敏。");
//			 postHisInfo.setHistoryDesc("患者平素体健，无“肝炎”、“结核”等传染病史。无输血史，无手术史，无过敏史。");
//			 postHisInfo.setMarriageDesc("无烟酒等不良嗜好。");
//			 postHisInfo.setAllergyDesc("");
//			 postHisInfo.setFamilyDesc("父亲体健，母亲体健，兄弟姐妹无类似疾病，无家族遗传病史。");
//			 
//			 postDeseaseLisPacsAllList(postHisInfo);
//			 
//		} catch (Exception loadException) {
//			loadException.printStackTrace();
//			logger.error("数据写入出错!", loadException);
//			return response.failure("数据写入出错!");
//		}
////		System.out.println(response.success().getMsg());
//		return response.success();
//	}
}

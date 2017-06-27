package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.DoctorInfo;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestDrugAgreePre;
import com.lantone.icss.trans.langtong.model.response.kl.DrugChinaSpell;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseDrugAgreePre;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @author 吴文俊
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe 协定处方明细
 */
@Controller
@RequestMapping("/langtong/drug_agree_pre")
public class DrugAgreePreController {
	private static Logger logger = LoggerFactory.getLogger(DrugAgreePreController.class);
	@ResponseBody
	@RequestMapping("/get_drug_agree_pre")
	public Response<DoctorInfo> getDrugAgreePre(String hosiptalId){
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="402";
		String tranKey="402";
		String stffNo="";
		String hospitalId="331023";
		String departId ="";
		String treId="22";
		String hospitalId1="331023";
	
		RequestDrugAgreePre requestDrugAgreePre = new RequestDrugAgreePre();
		requestDrugAgreePre.setHosiptalId(hospitalId1);
		requestDrugAgreePre.setTreId(treId);
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestDrugAgreePre);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponseDrugAgreePre> httpApi = new HttpApi<ResponseDrugAgreePre>();
		logger.info("------获取协定处方-------");
		ResponseDrugAgreePre responseDrugAgreePre = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDrugAgreePre.class);
		if(responseDrugAgreePre.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
			List<DrugChinaSpell> drugAgreePres = responseDrugAgreePre.getData();
		/***
		 * 放入web业务对象
		 */
		return response.success();
		
		}else{
			 return response.failure(responseDrugAgreePre.getMsg());
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
			
	}
}

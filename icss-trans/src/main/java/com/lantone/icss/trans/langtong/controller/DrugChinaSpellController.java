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
import com.lantone.icss.trans.langtong.model.request.kl.RequestChinaSpell;
import com.lantone.icss.trans.langtong.model.response.kl.DrugChinaSpell;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseDrugChinaSpell;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @author 吴文俊
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe 药品中文检索
 */
@Controller
@RequestMapping("/langtong/drugChinaSpell")
public class DrugChinaSpellController {
	private static Logger logger = LoggerFactory.getLogger(DrugChinaSpellController.class);
	@ResponseBody
	@RequestMapping("/get_drug_chinaSpell")
	public Response<DoctorInfo> getDrugStock(String hosiptalId){
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="400";
		String tranKey="400";
		String stffNo="";
		String departId ="";
		String hospitalId=hosiptalId;
		String chinaSpell  ="AMXLJN";
		String fiveStroke="";
		String recipeType="1";
		String hospitalId1="331023";
		RequestChinaSpell requestChinaSpell = new RequestChinaSpell();
		requestChinaSpell.setChinaSpell(chinaSpell);
		requestChinaSpell.setFiveStroke(fiveStroke);
		requestChinaSpell.setHosiptalId(hospitalId1);
		requestChinaSpell.setRecipeType(recipeType);
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestChinaSpell);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponseDrugChinaSpell> httpApi = new HttpApi<ResponseDrugChinaSpell>();
		logger.info("------拼音检索获取药品-------");
		ResponseDrugChinaSpell responseDrugChinaSpells  = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDrugChinaSpell.class);
		if(responseDrugChinaSpells.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		List<DrugChinaSpell> drugChinaSpell = responseDrugChinaSpells.getData();
		/***
		 * 放入web业务对象
		 */
		
		
		//response.setData(doctorInfo);
		}else{
			 logger.error("获取药品失败");
			 return response.failure("获取药品失败");
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		return response.success();
		
	}	
}

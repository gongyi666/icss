package com.lantone.icss.trans.langtong.controller;

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
import com.lantone.icss.trans.langtong.model.request.kl.RequestDrugStock;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseDrugStock;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @author 吴文俊
 * @data   2017年2月22日
 * 杭州朗通信息技术有限公司
 * @describe 药品库存接口
 */
@Controller
@RequestMapping("/langtong/drugStock")
public class DrugStockController {
	private static Logger logger = LoggerFactory.getLogger(DrugStockController.class);
	@ResponseBody
	@RequestMapping("/get_drug_stock")
	public Response<DoctorInfo> getDrugStock(String hosiptalId){
		Response<DoctorInfo> response = new Response<DoctorInfo>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="401";
		String tranKey="401";
		String stffNo="";
		String hospitalId=hosiptalId;
		String departId ="";
		String speId="1";
		String manId="1";
		String hospitalId1="331023";
		String storeId="5001";
		String recipeQuantity="5";
		String drgName="阿莫西林注射液";
		RequestDrugStock requestDrugStock = new RequestDrugStock();
		requestDrugStock.setDrgName(drgName);
		requestDrugStock.setHospitalId(hospitalId1);
		requestDrugStock.setManId(manId);
		requestDrugStock.setRecipeQuantity(recipeQuantity);
		requestDrugStock.setSpeId(speId);
		requestDrugStock.setStoreId(storeId);
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestDrugStock);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponseDrugStock> httpApi = new HttpApi<ResponseDrugStock>();
		logger.info("------验证用户-------");
		ResponseDrugStock responseDrugStock = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseDrugStock.class);
		if(responseDrugStock.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		/***
		 * 放入web业务对象
		 */
		return response.success();
		
		}else{
			 return response.failure(responseDrugStock.getMsg());
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		
	}

	
}

package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestLisInfo;
import com.lantone.icss.trans.langtong.model.response.kl.HISLisInfo;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseLisInfo;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @Description:检验套餐搜索
 * @author : panxz
 * @time : 2017年2月23日 上午13:14:20
 * 测试url
 * http://localhost:8080/icss-trans/langtong/lisInfo/searchLisInfo
 * 入参
 * hospital=331023&spell=XCG&binType=1
 */
@Controller
@RequestMapping("/langtong/lisInfo")
public class searchLisInfoController {

	private static Logger logger = LoggerFactory.getLogger(searchLisInfoController.class);

	/**
	 * @Description:从HIS搜索检验套餐
	 * @author:panxz
	 * @time:2017年2月23日 上午13:14:30
	 
	 */
	@ResponseBody
	@RequestMapping("/searchLisInfo")
	public Response<List<LisInfoWrapper>> searchLisInfo(@RequestBody LisInfoWrapper lisInfo){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			String tranType="303";
			String tranKey="303";
			String stffNo=lisInfo.getDoctorCode();
			String hospitalId = lisInfo.getHospitalCode();
			String departId =lisInfo.getDeptNo();
			
			RequestLisInfo requestLisInfo = new RequestLisInfo();
			requestLisInfo.setHospitalId(hospitalId);
			requestLisInfo.setBinType(lisInfo.getBinType());
			requestLisInfo.setChinaSpell(lisInfo.getInputstr());
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId,requestLisInfo);
			HttpApi<ResponseLisInfo> api = new HttpApi<ResponseLisInfo>();
			ResponseLisInfo responseLisInfo = api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseLisInfo.class);
			if(responseLisInfo.getRet() == 0){
				//封装返回的对象
				List<HISLisInfo> lisList = responseLisInfo.getData();
				List<LisInfoWrapper> lisInfoList = transformLisInfo(lisList);
				response.setData(lisInfoList);
			}else{
				logger.error("检索套餐失败");
				return response.failure("检索套餐失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索套餐失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:将HIS检验套餐，转为ICSS检验套餐
	 * @author:panxz
	 * @time:2017年2月17日 上午16:24:00
	 */
	private List<LisInfoWrapper> transformLisInfo(List<HISLisInfo> lisList){
		List<LisInfoWrapper> returnList = Lists.newArrayList();
		for(HISLisInfo detail : lisList){
			LisInfoWrapper lisDetail = new LisInfoWrapper();
			lisDetail.setCode(detail.getBinNormCode());
			lisDetail.setSpell(detail.getChinaSpell());
			lisDetail.setName(detail.getBinName());
			lisDetail.setStatus(detail.getBinState());
			lisDetail.setFiveStroke(detail.getFiveStroke());
			returnList.add(lisDetail);
		}
		
		return returnList;
	}
}

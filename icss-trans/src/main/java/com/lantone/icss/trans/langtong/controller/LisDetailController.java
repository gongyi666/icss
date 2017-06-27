package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestLisInfo;
import com.lantone.icss.trans.langtong.model.response.kl.HISLisDetail1;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseLisDetail;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * @Description:检验明细项目获取
 * @author : panxz
 * @time : 2017年2月17日 下午14:30:12
 * url:
 * http://localhost:8080/icss-trans/langtong/lisInfo/lisInfoGetLisDetail
 * 入参：
 * id=56
 */
@Controller
@RequestMapping("/langtong/lisInfo")
public class LisDetailController {

	private static Logger logger = LoggerFactory.getLogger(LisDetailController.class);

	/**
	 * @Description:从HIS获取检验明细项目
	 * @author:panxz
	 * @time:2017年2月17日 上午10:36:49
	 *
	 *传入HospitalId
	 */
	@ResponseBody
	@RequestMapping("/getLisDetail/list")
	public Response<List<HisLisDetailWrapper>> getLisDetail(LisInfoWrapper lis){
		Response<List<HisLisDetailWrapper>> response = new Response<List<HisLisDetailWrapper>>();
		response.start();
		try {
			String tranType="103";
			String tranKey="103";
			String stffNo=lis.getDoctorCode();
			String hospitalId=lis.getHospitalCode();
			String departId =lis.getDeptNo();
			//根据检验套餐获取检验 ,根据检验ID得到套餐
			RequestLisInfo requestLisInfo = new RequestLisInfo();
			//requestLisInfo.setStffId(lis.getStffId());
			requestLisInfo.setHospitalId(lis.getHospitalCode());
			//requestLisInfo.setDepartId(lis.getDepartId());
			
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId,requestLisInfo);
			
			HttpApi<ResponseLisDetail> api = new HttpApi<ResponseLisDetail>();
			ResponseLisDetail responseLisDetail = api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseLisDetail.class);
			if(responseLisDetail.getRet() == 0){
				//封装返回的对象
				List<HISLisDetail1> lisList = responseLisDetail.getData();
				List<HisLisDetailWrapper> lisDetailList = transformLisDetail(lisList,lis.getHospitalCode());
				response.setData(lisDetailList);
			}else{
				logger.error("获取检验明细项目失败！");
				return response.failure("获取检验明细项目失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取检验明细项目失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:将HIS检验明细项目，转为ICSS检验明细项目
	 * @author:panxz
	 * @time:2017年2月17日 上午16:24:00
	 */
	private List<HisLisDetailWrapper> transformLisDetail(List<HISLisDetail1> lisList,String hospitalCode){
		List<HisLisDetailWrapper> returnList = Lists.newArrayList();
		for(HISLisDetail1 detail : lisList){
			HisLisDetailWrapper lisDetail = new HisLisDetailWrapper();
			lisDetail.setHisLisCode(detail.getId());
			lisDetail.setName(detail.getItemName());
			lisDetail.setSpell(detail.getChinaSpell());
			lisDetail.setFiveStroke(detail.getFiveStroke());
			lisDetail.setHospitalCode(hospitalCode);
			lisDetail.setRemark(detail.getItemSummary());
			returnList.add(lisDetail);
		}
		
		return returnList;
	}
}

package com.lantone.icss.web.his.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.his.service.HisLisDetailService;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResponseLisDetail;

@Controller
@RequestMapping("/his/his_lis_detail")
public class HisLisDetailController{

	private static Logger logger = LoggerFactory.getLogger(HisLisDetailController.class);
	@Reference
	HisLisDetailService hisLisDetailService;
	/**
	 * @Description:获取检验明细
	 * @author:luwg
	 * @time:2017年2月28日 下午4:38:42
	 */
	@ResponseBody
	@RequestMapping("/get_detail_by_lisid")
	public Response<List<HisLisDetailWrapper>> getDetailByLisId(LisInfoWrapper lis){
		Response<List<HisLisDetailWrapper>> response = new Response<List<HisLisDetailWrapper>>();
		response.start();
		try {
			HttpApi<ResponseLisDetail> api = new HttpApi<ResponseLisDetail>();
			ResponseLisDetail resData = api.doPost(InitConfig.get("get.lis.detail.url"), lis, ResponseLisDetail.class);
			response.setData(resData.getData());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
	/**
	 * @Description:获取HIS检验明细
	 * @author:wwj
	 */
	
	@RequestMapping("/get_his_lis_detail")
	public void getHisLisDetail(LisInfoWrapper lis){
		try{
			HttpApi<ResponseLisDetail> httpApi = new HttpApi<ResponseLisDetail>();
			lis.setHospitalCode("331023");
			logger.info("------------获取部位信息------------");
			ResponseLisDetail responseLisDetails = httpApi.doPost(InitConfig.get("getLisDetails.list"), lis, ResponseLisDetail.class);
			List<HisLisDetailWrapper> lisDetails = responseLisDetails.getData();
			
			System.out.println();
			logger.info("-------------存储部位信息------------");
			try{
				hisLisDetailService.insertByBatch(lisDetails);
				}catch(Exception e){
					logger.error("保存HIS数据失败:"+e);
				}
			System.out.println("");
		}catch(Exception e){
			logger.error("获取His数据失败:"+e);
		}
		
	}
}

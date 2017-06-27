package com.lantone.icss.web.kl.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.Wrapper.HisLisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisDetailWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.service.LisDetailService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResponseLisDetail;


/**
 * @Description: 化验明细
 * @author:ztg  
 * @time:2017年3月24日 上午10:32:02
 */
@Controller
@RequestMapping("/kl/lis_detail")
public class LisDetailController {

	private static Logger logger = LoggerFactory.getLogger(LisDetailController.class);
	
	@Reference
	LisDetailService lisDetailService;
	/**
	 * @Description:根据his套餐获取icss检验明细
	 * @author:luwg
	 * @time:2017年3月6日 下午4:49:26
	 */
	@ResponseBody
	@RequestMapping("/get_lis_detail")
	public Response<List<LisDetailWrapper>> getLisDetail(LisInfoWrapper lis){
		Response<List<LisDetailWrapper>> response = new Response<List<LisDetailWrapper>>();
		response.start();
		try {
			HttpApi<ResponseLisDetail> api = new HttpApi<ResponseLisDetail>();
			ResponseLisDetail resData = api.doPost(InitConfig.get("get.lis.detail.url"), lis, ResponseLisDetail.class);
			List<String> lisDetail = Lists.newArrayList();
			if(CollectionUtils.isNotEmpty(resData.getData())){
				for(HisLisDetailWrapper hisDetail :resData.getData()){
					lisDetail.add(hisDetail.getHisLisCode());
				}
			}
			String[] hisLisDetail = lisDetail.toArray(new String[resData.getData().size()]);
			List<LisDetailWrapper> data = lisDetailService.getLisDetail(hisLisDetail, lis.getHospitalCode());
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取检验明细失败");
		}
		return response;
	}
	
	
	
	/**
	 * @Description: 根据lisId获取明细（已检接口）
	 * @author:ztg
	 * @time:2017年4月5日 下午1:36:41
	 */
	@RequestMapping("/get_by_infoid")
	@ResponseBody
	public Response<List<LisDetailWrapper>> getLisDetailByInfoId(LisInfoWrapper info){
		Response<List<LisDetailWrapper>> response = new Response<List<LisDetailWrapper>>();
		response.start();
		try {
			response.setData(lisDetailService.getLisDetailByInfoId(info));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取检验明细失败");
		}
		return response.success();
	}
	
	
	/**
	 * @Description:推送信息
	 * @author:ztg  
	 * @time:2017年3月24日 上午9:32:50
	 */
	@ResponseBody
	@RequestMapping("/get_detail")
	public Response<List<LisDetailWrapper>> getLisDetailByDoctorId(Long doctorId, Integer size) {
		Response<List<LisDetailWrapper>> response = new Response<List<LisDetailWrapper>>();
		response.start();
		try {
			List<LisDetailWrapper> data = lisDetailService.getLisDetailByDoctorId(doctorId, size);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取明细列表失败");
		}
		return response.success();
	}
	
	
	
	/**
	 * @Description:检索
	 * @author:ztg  
	 * @time:2017年3月23日 下午3:32:47
	 */
	@ResponseBody
	@RequestMapping("/search_detail")
	public Response<List<LisDetailWrapper>> searchLisDetail(String input,Integer size){
		Response<List<LisDetailWrapper>> response = new Response<List<LisDetailWrapper>>();
		response.start();
		try {
			List<LisDetailWrapper> data = lisDetailService.searchLisDetailByInput(input, size);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询失败！");
		}
		return response.success();
	}
}

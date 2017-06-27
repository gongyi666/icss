package com.lantone.icss.web.his.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.icss.api.his.model.Wrapper.HisDiseaseInfoWrapper;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.service.HisDiseaseService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResponseDiseaseInfoList;

/**
 * @Description:his疾病控制层
 * @author : luwg
 * @time : 2017年3月2日 上午10:08:32
 * 
 */
@Controller
@RequestMapping("/his/his_diseaseinfo")
public class HisDiseaseInfoController {
	private static Logger logger = LoggerFactory.getLogger(HisDiseaseInfoController.class);
	@Reference
	HisDiseaseService hisDiseaseService;
	
	/**
	 * @Description:从HIS获取疾病库信息并返回给前端
	 * @author:CSP
	 * @time:2017年3月1日 下午13:45:33
	 * 
	 */
	@ResponseBody
	@RequestMapping("/get_diseaseinfolist_his")
	public Response<List<HisDiseaseInfoWrapper>> getDiseaseInfoList(HisDiseaseInfoWrapper diseaseinfo){
		Response<List<HisDiseaseInfoWrapper>> response=new Response<List<HisDiseaseInfoWrapper>>();
		response.start();
		
		try {
			HttpApi<ResponseDiseaseInfoList> api = new HttpApi<ResponseDiseaseInfoList>();
			// 根据hospitalCode从his获取所有疾病库信息
			ResponseDiseaseInfoList responseDiseaseInfoList = api.doPost(InitConfig.get("getdiseaseinfo.list"), diseaseinfo,
					ResponseDiseaseInfoList.class);
			if (responseDiseaseInfoList.getRet() == 0) {
				List<HisDiseaseInfoWrapper> diseaseInfoList = responseDiseaseInfoList.getData();
				if (diseaseInfoList.size() > 0) {
					// 根据hospitalCode从icss获取疾病库信息
					Integer diseaseinfoCount = hisDiseaseService.getCount(diseaseinfo);
					if (diseaseinfoCount> 0) {//判断，如果ICSS有数据，先删再插,如果没有数据，直接插入从his取得的数据
						//根据hospitalCode删除icss原有数据
						hisDiseaseService.delAndinsertDiseaseinfo(diseaseInfoList,Long.parseLong(diseaseinfo.getHospitalCode()));
						
					} else {
						hisDiseaseService.insertDiseaseinfoList(diseaseInfoList);

					}
				}
				response.setData(responseDiseaseInfoList.getData());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("获取全部疾病库信息失败!");
			return response.failure("获取全部疾病库信息失败!");
		}

		return response;
	}

	
	/**
	 * @Description:检索HIS疾病信息
	 * @author:luwg
	 * @time:2017年3月2日 上午10:12:00
	 */
	@ResponseBody
	@RequestMapping("/search_his_diseases")
	public Response<List<HisDiseaseInfoWrapper>> searchHisDiseases(String inputstr, Integer size,String hospitalCode){
		Response<List<HisDiseaseInfoWrapper>> response = new Response<List<HisDiseaseInfoWrapper>>();
		response.start();
		try {
			List<HisDiseaseInfoWrapper> data = hisDiseaseService.searchHisDiseases(inputstr, size, hospitalCode);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("检索失败！");
		}
		return response;
	}
	
	
	/**
	 * @Description:获取hisMappingDisease
	 * @author:ztg
	 * @time:2017年4月20日 上午11:52:14
	 */
	@ResponseBody
	@RequestMapping("/get_mapping_disease")
	public Response<List<HisDiseaseInfoWrapper>> getHisMappingDisease(HisDiseaseInfoWrapper hisDiseaseInfoWrapper){
		Response<List<HisDiseaseInfoWrapper>> response = new Response<List<HisDiseaseInfoWrapper>>();
		response.start();
		try {
			List<HisDiseaseInfoWrapper> data = hisDiseaseService.getHisMappingDisease(hisDiseaseInfoWrapper);
			response.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("查询hisMappingDisease失败！");
		}
		return response;
	}
}

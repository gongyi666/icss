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
import com.lantone.icss.api.his.model.HisApparatusInfo;
import com.lantone.icss.api.his.model.Wrapper.HisApparatusInfoWrapper;
import com.lantone.icss.api.his.service.HisApparatusInfoService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResopnseHisApparatusInfo;

/**
 * @author 吴文俊
 * @data   2017年3月9日
 * 杭州朗通信息技术有限公司
 * @describe 检查项目
 */
@Controller
@RequestMapping("his/apparatusinfo")
public class HisApparatusInfoController {
	private static Logger logger = LoggerFactory.getLogger(HisApparatusInfoController.class);
	@Reference
	HisApparatusInfoService hisApparatusInfoService;
	@ResponseBody
	@RequestMapping("/get_his_apparatusinfo")
	public Response<List<HisApparatusInfo>> getHisApparatusInfo(HisApparatusInfoWrapper  wrapper){
		Response<List<HisApparatusInfo>> response = new Response<List<HisApparatusInfo>>();
		response.start();
		try{
		HttpApi<ResopnseHisApparatusInfo> api = new HttpApi<ResopnseHisApparatusInfo>();
		ResopnseHisApparatusInfo resData = api.doPost(InitConfig.get("get.his.apparatusInfo.list"), wrapper, ResopnseHisApparatusInfo.class);
		try{
			List<HisApparatusInfo> hisApparatusInfoes =resData.getData();
			hisApparatusInfoService.insertAllData(hisApparatusInfoes,wrapper.getHospitalCode());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存器械数据失败"+e);
			return response.failure("保存器械数据失败"+e);
		}
		
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取His数据失败"+e);
			return response.failure("获取His数据失败"+e);
		}

		return response.success();
	}
}

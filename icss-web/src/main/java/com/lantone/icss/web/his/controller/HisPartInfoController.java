package com.lantone.icss.web.his.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisPartInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPartInfoWrapper;
import com.lantone.icss.api.his.service.HisPartInfoService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.trans.res.ResponseHisPartInfo;

/**
 * @author 吴文俊
 * @data   2017年3月2日
 * 杭州朗通信息技术有限公司
 * @describe 检查部位信息获取
 */
@Controller
@RequestMapping("/his/his_partinfo")
public class HisPartInfoController {
	private static Logger logger = LoggerFactory.getLogger(HisPartInfoController.class);
	@Reference
	HisPartInfoService hisPartInfoService;
	/**
	 * @Description:检查部位信息获取
	 */
	@RequestMapping("/get_his_partinfo")
	public void getHisPartInfo(HisPartInfoWrapper hisPartInfoWrapper){
		try{
			HttpApi<ResponseHisPartInfo> httpApi = new HttpApi<ResponseHisPartInfo>();
			logger.info("------------获取部位信息------------");
			ResponseHisPartInfo responseHisPartInfoes = httpApi.doPost(InitConfig.get("gethispartinfo.list"), hisPartInfoWrapper, ResponseHisPartInfo.class);
			List<HisPartInfo> hisPartInfoes = responseHisPartInfoes.getData();
			logger.info("-------------存储部位信息------------");
			try{
				hisPartInfoService.insertPartInfoes(hisPartInfoes,hisPartInfoWrapper.getHospitalCode());
				}catch(Exception e){
					logger.error("保存HIS数据失败:"+e);
				}
			System.out.println("");
			
		}catch(Exception e){
			logger.error("获取部位失败:"+e);
		}
	}
}

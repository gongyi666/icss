package com.lantone.icss.trans.langtong.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisPartInfo;
import com.lantone.icss.api.his.model.Wrapper.HisPartInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestPart;
import com.lantone.icss.trans.langtong.model.response.at.HISPart;
import com.lantone.icss.trans.langtong.model.response.at.ResponsePart;
import com.lantone.icss.trans.langtong.util.LangTongUtil;


/**
 * @author 
 * @data   2017年3月2日
 * 杭州朗通信息技术有限公司
 * @describe 获取his部位信息
 */
@Controller
@RequestMapping("/langtong")
public class PartInfoController {
	private static Logger logger = LoggerFactory.getLogger(PartInfoController.class);
	@ResponseBody
	@RequestMapping("/get_partinfo/list")
	public Response<List<HisPartInfo>> HisPartInfo(@RequestBody HisPartInfoWrapper HisPartInfoWrapper){
		Response<List<HisPartInfo>> response = new Response<List<HisPartInfo>>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="107";
		String tranKey="107";
		String stffNo="";
		String hospitalId="";
		String departId ="";
		
		RequestPart requestPart = new RequestPart();
		requestPart.setHospitalId(HisPartInfoWrapper.getHospitalCode());
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestPart);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponsePart> httpApi = new HttpApi<ResponsePart>();
		ResponsePart responsePart = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponsePart.class);
		if(responsePart.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		List<HISPart> hisParts = new ArrayList<HISPart>();
		hisParts = responsePart.getData();
		List<HisPartInfo> hispartinfo = putPartUtil(hisParts,HisPartInfoWrapper.getHospitalCode());		
		response.setData(hispartinfo);
		}else{
			 logger.error("没有该部位信息");
			 return response.failure("没有该部位信息");
		}
		}catch(Exception e){
			 logger.error("调用HIS接口失败");
			 return response.failure("调用HIS接口失败");
		 }
				
		return response.success();
		
	}
		
	private List<HisPartInfo> putPartUtil(List<HISPart> hispart,String hospitalCode){
		List<HisPartInfo> partInfo = new ArrayList<HisPartInfo>();
		for (int i = 0 ;i < hispart.size(); i++){
			HisPartInfo hi = new HisPartInfo();
			hi.setHisPartCode(hispart.get(i).getDicCode());
			hi.setPartName(hispart.get(i).getDicName());
			hi.setRemark(hispart.get(i).getDicSummary());
			hi.setHospitalCode(hospitalCode);
			partInfo.add(hi);
		}
		return partInfo;
	}	
}

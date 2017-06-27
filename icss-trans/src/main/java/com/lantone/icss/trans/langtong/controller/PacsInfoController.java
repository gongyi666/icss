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
import com.lantone.icss.api.his.model.HisApparatusInfo;
import com.lantone.icss.api.his.model.Wrapper.HisApparatusInfoWrapper;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestPacs;
import com.lantone.icss.trans.langtong.model.response.at.HISPacs;
import com.lantone.icss.trans.langtong.model.response.at.ResponsePacs;
import com.lantone.icss.trans.langtong.util.LangTongUtil;


/**
 * @author 吴文俊
 * @data   2017年3月9日
 * 杭州朗通信息技术有限公司
 * @describe 检查套餐获取
 */
@Controller
@RequestMapping("/langtong")
public class PacsInfoController {
	private static Logger logger = LoggerFactory.getLogger(PacsInfoController.class);
	@ResponseBody
	@RequestMapping("/his/apparatusInfo")
	public Response<List<HisApparatusInfo>> HisPartInfo(@RequestBody HisApparatusInfoWrapper hisApparatusInfoWrapper){
		Response<List<HisApparatusInfo>> response = new Response<List<HisApparatusInfo>>();
		response.start();
		try{
		/***
		 * 组装接口对象
		 */

		String tranType="302";
		String tranKey="302";
		String stffNo="";
		String hospitalId=hisApparatusInfoWrapper.getHospitalCode();
		String departId ="";
		
		RequestPacs requestPacs = new RequestPacs();
		requestPacs.setHospitalId(hisApparatusInfoWrapper.getHospitalCode());
		RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestPacs);
		/**
		 * 调用HIS接口
		 * */
		HttpApi<ResponsePacs> httpApi = new HttpApi<ResponsePacs>();
		ResponsePacs responsePacs = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponsePacs.class);
		if(responsePacs.getRet()==0){//返回数据
		/**
		 * 组装返回对象
		 */
		List<HISPacs> hisPacss = new ArrayList<HISPacs>();
		hisPacss = responsePacs.getData();
		List<HisApparatusInfo> hisApparatusInfoes = putPacsUtil(hisPacss,hospitalId);		
		response.setData(hisApparatusInfoes);
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
		
	private List<HisApparatusInfo> putPacsUtil(List<HISPacs> hispacs,String hospitalId){
		List<HisApparatusInfo> pacsInfoes = new ArrayList<HisApparatusInfo>();
		for (HISPacs hisPacs:hispacs){
			if(!"0".equals(hisPacs.getBinType())||hisPacs.getBinType()==null){
				continue;
			}
			else{
				HisApparatusInfo hisApparatusInfo =new HisApparatusInfo();
				hisApparatusInfo.setApparatusCode(hisPacs.getBinNormCode());
				hisApparatusInfo.setApparatusName(hisPacs.getBinName());
				hisApparatusInfo.setHospitalCode(hospitalId);
				pacsInfoes.add(hisApparatusInfo);
			}

			
		}
		return pacsInfoes;
	}	
	
	
	
	/**
	 * @Description:获取his器查套餐
	 * @author:ztg
	 * @time:2017年3月31日 下午4:50:44
	 */
	@ResponseBody
	@RequestMapping("/his/pacs")
	public Response<List<HisPacsStructuringWrapper>> HisPacsInfo(@RequestBody PacsInfoWrapper pacs){
		Response<List<HisPacsStructuringWrapper>> response = new Response<List<HisPacsStructuringWrapper>>();
		response.start();
		try{
			/***
			 * 组装接口对象
			 */
	
			String tranType="306";
			String tranKey="306";
			String stffNo="";
			String hospitalId=pacs.getHospitalCode();
			String departId ="";
			
			RequestPacs requestPacs = new RequestPacs();
			requestPacs.setHospitalId(hospitalId);
			requestPacs.setPartId(pacs.getHisPartCode());
			requestPacs.setBinIds(pacs.getHisApparatusCode());
			RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestPacs);
			/**
			 * 调用HIS接口
			 * */
			HttpApi<ResponsePacs> httpApi = new HttpApi<ResponsePacs>();
			ResponsePacs responsePacs = httpApi.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponsePacs.class);
			if(responsePacs.getRet()==0){//返回数据
				/**
				 * 组装返回对象
				 */
				List<HISPacs> hisPacss = new ArrayList<HISPacs>();
				hisPacss = responsePacs.getData();
				List<HisPacsStructuringWrapper> result = putPacs(hisPacss);		
				response.setData(result);
			}else{
				 logger.error("没有该器查信息");
				 return response.failure("没有该器查信息");
			}
			}catch(Exception e){
				 logger.error("调用HIS接口失败");
				 return response.failure("调用HIS接口失败");
			 }
				
		return response.success();
	}
	
	
	private List<HisPacsStructuringWrapper> putPacs(List<HISPacs> hispacs){
		List<HisPacsStructuringWrapper> pacsInfoes = new ArrayList<HisPacsStructuringWrapper>();
		for (HISPacs hisPacs:hispacs){
			if(!"0".equals(hisPacs.getBinType())||hisPacs.getBinType()==null){
				continue;
			}
			else{
				HisPacsStructuringWrapper pacsInfo =new HisPacsStructuringWrapper();
				pacsInfo.setHisApparatusCode(hisPacs.getId());//手段编码
				pacsInfo.setStructuringName(hisPacs.getBinName()); //结构化名称
				pacsInfoes.add(pacsInfo);
				break; //需求变更，只返回一条
			}
		}
		return pacsInfoes;
	}	
}

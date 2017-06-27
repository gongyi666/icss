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
import com.lantone.icss.api.his.model.Wrapper.HisDiseaseInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestPubDiseaseInfo;
import com.lantone.icss.trans.langtong.model.response.kl.HISPubDiseaseInfo;
import com.lantone.icss.trans.langtong.model.response.kl.ResponsePubDiseaseInfo;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

/**
 * 
 * 疾病库获取接口
 * @author CSP
 *
 */
@Controller
@RequestMapping("/langtong")
public class PubDiseaseInfoController {
	private static Logger logger=LoggerFactory.getLogger(PubDiseaseInfoController.class);
	
	@ResponseBody
	@RequestMapping("/getdiseaseinfo/list")
	public Response<List<HisDiseaseInfoWrapper>> getAlldiseaseInfo(@RequestBody HisDiseaseInfoWrapper hisDiseaseInfo){
		Response<List<HisDiseaseInfoWrapper>> response=new Response<List<HisDiseaseInfoWrapper>>();
		response.start();

		try {
			String tranType="104";
			String tranKey="100";
			String stffNo=hisDiseaseInfo.getDoctorCode();
			String hospitalId=hisDiseaseInfo.getHospitalCode();
			String departId =hisDiseaseInfo.getDeptNo();
			Integer PageNum=1;
			Integer PageSize=100;
			int num=100;
			RequestPubDiseaseInfo requestPubDiseaseInfo=new RequestPubDiseaseInfo();
			requestPubDiseaseInfo.setHospitalId(hisDiseaseInfo.getHospitalCode());
			List<HISPubDiseaseInfo> hisPubDiseaseInfos=Lists.newArrayList();

			for(int i=0;i>=0;i++){
				
				requestPubDiseaseInfo.setPageNum(PageNum.toString());
				requestPubDiseaseInfo.setPageSize(PageSize.toString());
				if(num>=100){
						RequestData requestData = LangTongUtil.getRequestData(tranType,tranKey,stffNo,hospitalId,departId,requestPubDiseaseInfo);
						PageNum=PageNum+1;
						HttpApi<ResponsePubDiseaseInfo> api=new HttpApi<ResponsePubDiseaseInfo>();
						ResponsePubDiseaseInfo responsePubDiseaseInfo=api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponsePubDiseaseInfo.class);
							List<HISPubDiseaseInfo> pubDiseaseInfoList=responsePubDiseaseInfo.getData().getList();
							num=pubDiseaseInfoList.size();
							for(HISPubDiseaseInfo hisdis:pubDiseaseInfoList){
								HISPubDiseaseInfo HisDiseaseinfo_temp=new HISPubDiseaseInfo();
								HisDiseaseinfo_temp.setId(hisdis.getId());
								HisDiseaseinfo_temp.setDisIcd(hisdis.getDisIcd());
								HisDiseaseinfo_temp.setDisName(hisdis.getDisName());
								HisDiseaseinfo_temp.setChinaSpell(hisdis.getChinaSpell());
								HisDiseaseinfo_temp.setFiveStroke(hisdis.getFiveStroke());
								HisDiseaseinfo_temp.setDisState(hisdis.getDisState());
								HisDiseaseinfo_temp.setDisSummary(hisdis.getDisSummary());
								hisPubDiseaseInfos.add(HisDiseaseinfo_temp);
							
							}
							
				}else{
					break;
				}
			}
			if(hisPubDiseaseInfos.size() >= 0){
				//封装返回的对象
				List<HisDiseaseInfoWrapper> allDiseaseInfoList=allDiseaseInfoDetail(hisPubDiseaseInfos,hospitalId);
				
				response.setData(allDiseaseInfoList);				
			}else{
				logger.error("获取全部疾病库信息失败！");
				return response.failure("获取全部疾病库信息失败");
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取全部疾病库信息失败");
		}
		return response.success();
	}
	
	private List<HisDiseaseInfoWrapper> allDiseaseInfoDetail(List<HISPubDiseaseInfo> pubDiseaseInfoList,String hospitalId){
		List<HisDiseaseInfoWrapper> returnList = Lists.newArrayList();
		for(HISPubDiseaseInfo hisdis : pubDiseaseInfoList){
			HisDiseaseInfoWrapper alldiseaseInfo=new HisDiseaseInfoWrapper();
			alldiseaseInfo.setHisDiseaseCode(hisdis.getId());	//his疾病编码
			alldiseaseInfo.setHospitalCode(hospitalId);	//医院编码
//			alldiseaseInfo.setParentCode(parentCode);	//上级编码
			alldiseaseInfo.setCode(hisdis.getDisIcd());		//icd10编码
			alldiseaseInfo.setName(hisdis.getDisName());	//名称
//			alldiseaseInfo.setShortName(shortName);		//简称
			alldiseaseInfo.setSpell(hisdis.getChinaSpell());	//拼音
			alldiseaseInfo.setFiveStroke(hisdis.getFiveStroke());	//五笔
			if("0".equals(hisdis.getDisState())){		 //状态（1：有效，0：无效）		
				alldiseaseInfo.setStatus("1");
			}else if("1".equals(hisdis.getDisState())){
				alldiseaseInfo.setStatus("0");
			}
			alldiseaseInfo.setRemark(hisdis.getDisSummary());	//备注
			returnList.add(alldiseaseInfo);
		}
		return returnList;
	}
}

package com.lantone.icss.web.kl.cache;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.service.RedisService;
import com.lantone.icss.api.kl.model.DiagnoseInfo;
import com.lantone.icss.api.kl.model.DiseaseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiagnoseInfoWrapper;
import com.lantone.icss.api.kl.service.DiagnoseInfoService;
import com.lantone.icss.api.kl.service.DiseaseInfoService;
import com.lantone.icss.web.kl.cds.IcssInput;
import com.lantone.icss.web.kl.cds.JsonRedisSeriazilerUtils;
@Component
public class DiseaseInfoWithDiagnoseInfoCache {
	
	private static Logger logger = LoggerFactory.getLogger(DiseaseInfoWithDiagnoseInfoCache.class);
	
	@Autowired
	private RedisService<String,List<DiagnoseInfoWrapper>> diseaseInfoWithDiagnoseInfoCache;
	@Reference
	private DiseaseInfoService diseaseInfoService;
	@Reference
	private DiagnoseInfoService diagnoseInfoService;
	
	private static final String DIS_WITH_DIA = "DIS_WITH_DIA";
	
	private static final Long STATUS = 1l;
	
	private String diseaseId = "";
	
	public void flushDiseaseInfoWithDiagnoseInfo(){
		try{
		    List<DiseaseInfo>  diseaseInfos = diseaseInfoService.selectAllList(STATUS);
		for(DiseaseInfo diseaseInfo : diseaseInfos){
			diseaseId = String.valueOf(diseaseInfo.getId());
			List<DiagnoseInfoWrapper> diagnoseInfos = diagnoseInfoService.getByDiseaseId(diseaseInfo.getId());
			addDiseaseInfoWithDiagnoseInfo(diseaseId,diagnoseInfos);
		}
		}catch(Exception e){
			logger.error("加载疾病诊断依据失败");
		}
	}

	public List<DiagnoseInfoWrapper> get(String diseaseId) {
		List<DiagnoseInfoWrapper> diagnoseInfos = diseaseInfoWithDiagnoseInfoCache.get(DIS_WITH_DIA+diseaseId);
		if(diagnoseInfos == null){
		   diagnoseInfos = diagnoseInfoService.getByDiseaseId(Long.valueOf(diseaseId));
		   addDiseaseInfoWithDiagnoseInfo(diseaseId,diagnoseInfos);
		}
		return diagnoseInfos;
	}
	
	public void addDiseaseInfoWithDiagnoseInfo(String diseaseId, List<DiagnoseInfoWrapper> diagnoseInfos) {
		diseaseInfoWithDiagnoseInfoCache.add(DIS_WITH_DIA+diseaseId, diagnoseInfos);
	}

	public void delete(String diseaseId){
		diseaseInfoWithDiagnoseInfoCache.delete(DIS_WITH_DIA+diseaseId);
	}
}

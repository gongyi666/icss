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
public class DiagnoseInfoWithDiseaseInfoCache {
	
	private static Logger logger = LoggerFactory.getLogger(DiagnoseInfoWithDiseaseInfoCache.class);
	
	@Autowired
	private RedisService<String,List<DiseaseInfo>> diagnoseInfoWithDiseaseInfoCache;
	@Reference
	private DiseaseInfoService diseaseInfoService;
	@Reference
	private DiagnoseInfoService diagnoseInfoService;
	
	private static final String DIA_WITH_DIS = "DIA_WITH_DIS";
	
	private static final Long STATUS = 1l;
	
	private String diseaseId = "";
	
	public void flushDiseaseInfoWithDiagnoseInfo(){
		try{
		    List<DiagnoseInfo>  diagnoseInfos = diagnoseInfoService.selectAllList(STATUS);
		for(DiagnoseInfo diagnoseInfo : diagnoseInfos){
			List<DiseaseInfo> diseaseInfos = diseaseInfoService.getDiseaseByDiagnoseId(diagnoseInfo.getId());
			addDiagnoseInfoWithDiseaseInfo(diagnoseInfo.getCode(),diseaseInfos);
		}
		}catch(Exception e){
			logger.error("加载疾病诊断依据失败");
		}
	}

	public List<DiseaseInfo> get(String code) {
		List<DiseaseInfo> diseaseInfos = diagnoseInfoWithDiseaseInfoCache.get(DIA_WITH_DIS+code);
		if(diseaseInfos == null){
			diseaseInfos = diseaseInfoService.getDiseaseByDiagnoseCode(code);
		   addDiagnoseInfoWithDiseaseInfo(diseaseId,diseaseInfos);
		}
		return diseaseInfos;
	}
	
	public void addDiagnoseInfoWithDiseaseInfo(String code, List<DiseaseInfo> diseaseInfos) {
		diagnoseInfoWithDiseaseInfoCache.add(DIA_WITH_DIS+code, diseaseInfos);
	}

	public void delete(String code){
		diagnoseInfoWithDiseaseInfoCache.delete(DIA_WITH_DIS+code);
	}
}

package com.lantone.icss.api.kl.service;
import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.DrugCommon;
import com.lantone.icss.api.kl.model.wrapper.DrugCommonWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
/**
 * @Description:
 * @author : zhengms
 * @time : 2016年12月15日 下午1:38:05
 * 
 */
public interface DrugCommonService extends BaseService<DrugCommon,DrugCommonWrapper,Long>{
	
	GroupDrugDetailWrapper getDrugCommonInfo(Map<String,Object>map);
	List<GroupDrugDetailWrapper>getGroupDrugDetailInfo (Map<String,Object> map);
}

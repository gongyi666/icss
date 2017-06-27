package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.DrugCommon;
import com.lantone.icss.api.kl.model.wrapper.DrugCommonWrapper;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;

public interface DrugCommomMapper extends EntityMapper<DrugCommon,DrugCommonWrapper,Long> {
	/**
	 * @Description:根据化学名称的药品ID获得默认常用药品
	 * @author:zhengms
	 * @time:2016年12月15日 下午1:42:14
	 */
	GroupDrugDetailWrapper getDrugCommonInfo(Map<String,Object> map);
	List<GroupDrugDetailWrapper>getGroupDrugDetailInfo (Map<String,Object> map);
}

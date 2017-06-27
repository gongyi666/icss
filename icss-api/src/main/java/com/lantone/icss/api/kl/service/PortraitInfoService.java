package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.PortraitDrug;
import com.lantone.icss.api.kl.model.PortraitInfo;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWrapper;


public interface PortraitInfoService extends BaseService<PortraitInfo, PortraitInfoWrapper, Long>{ 
	List<PortraitInfoWithDrugWrapper> getPortraitInfoById(Long portraitId);
	void insertPortraitDrug (List<PortraitDrug> list);
	 void deletePortraitInfoDrugBatchBatch(List<PortraitDrug> list);
	 void deletePortraitInfoDrug(List<PortraitDrug> list);
}

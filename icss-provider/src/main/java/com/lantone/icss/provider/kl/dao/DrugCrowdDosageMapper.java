package com.lantone.icss.provider.kl.dao;
import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisDrugUseFrequency;
import com.lantone.icss.api.his.model.Wrapper.HisUsageModeWrapper;
import com.lantone.icss.api.kl.model.DrugCrowdDosage;
import com.lantone.icss.api.kl.model.wrapper.DrugCrowdDosageWrapper;
public interface DrugCrowdDosageMapper extends EntityMapper<DrugCrowdDosage,DrugCrowdDosageWrapper,Long>{
    List<DrugCrowdDosageWrapper>getDrugCrowdDosageWrapperInfo(Map<String,Object>map);
    List<DrugCrowdDosageWrapper>getListDrugCrowdDosageWrapper(Map<String,Object>map);
    List<HisUsageModeWrapper>getUsageModeList(Map<String,Object>map);
    List<HisDrugUseFrequency>getUseFrequencyList(Map<String,Object>map);
}

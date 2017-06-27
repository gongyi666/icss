package com.lantone.icss.provider.his.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisDiseaseInfo;
import com.lantone.icss.api.his.model.Wrapper.HisDiseaseInfoWrapper;

public interface HisDiseaseMapper extends EntityMapper<HisDiseaseInfo, HisDiseaseInfoWrapper, Long>{

	HisDiseaseInfoWrapper selectHisWarpperByDiseaseId(@Param("icssId")Long icssId);
	
	/**
	 * @Description:检索his疾病信息
	 * @author:luwg
	 * @time:2017年3月2日 上午10:21:53
	 */
	public List<HisDiseaseInfoWrapper> searchHisDiseases(Map<String,Object> paramMap);

	
	/**
	 * @Description:根据医院编号获取疾病库信息
	 * @author:CSP
	 * @time:2017年3月1日 下午14:25:15
	 */
	public List<HisDiseaseInfoWrapper>  getDiseaseinfoByHospitalList(HisDiseaseInfoWrapper hisDiseaseInfoWrapper);
	
	/**
	 * @Description:根据医院编号获取疾病库信息条数
	 * @author:CSP
	 * @time:2017年3月1日 下午14:25:15
	 */
	public Integer  getCount(HisDiseaseInfoWrapper hisDiseaseInfoWrapper);
	
	/**
	 * @Description:根据医院编号批量删除疾病库信息
	 * @author:CSP
	 * @time:2017年3月1日 下午14:25:15
	 */
	public void  deleteDiseaseinfoList(Long hospitalCode);
	
	/**
	 * @Description:根据医院编号批量插入疾病库信息
	 * @author:CSP
	 * @time:2017年3月1日 下午14:25:15
	 */
	public void  insertDiseaseinfo(HisDiseaseInfoWrapper hisDiseaseInfoWrapper);

	
	/**
	 * @Description:获取HisMappingDisease
	 * @author:ztg
	 * @time:2017年4月20日 上午11:34:46
	 */
	public List<HisDiseaseInfoWrapper>  getHisMappingDisease(HisDiseaseInfoWrapper hisDiseaseInfoWrapper);
	
}

package com.lantone.icss.api.his.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisDiseaseInfo;
import com.lantone.icss.api.his.model.Wrapper.HisDiseaseInfoWrapper;

public interface HisDiseaseService extends BaseService<HisDiseaseInfo, HisDiseaseInfoWrapper, Long>{
	/**
	 * 根据icss疾病id获取his疾病
	 * @param id
	 */
	HisDiseaseInfoWrapper selectHisWarpperByDiseaseId(Long icssId);
	
	/**
	 * @Description:检索His疾病信息
	 * @author:luwg
	 * @time:2017年3月2日 上午10:13:46
	 */
	public List<HisDiseaseInfoWrapper> searchHisDiseases(String inputstr,Integer size,String hospitalCode);
	
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
	 * @Description:根据医院编号批量插入疾病库信息
	 * @author:CSP
	 * @time:2017年3月2日 下午15:25:15
	 */
	public void  insertDiseaseinfoList(List<HisDiseaseInfoWrapper> diseaseInfoList);

	
	/**
	 * @Description:根据医院编号批量删除并从新插入疾病库信息
	 * @author:CSP
	 * @time:2017年3月2日 下午15:25:15
	 */
	public void  delAndinsertDiseaseinfo(List<HisDiseaseInfoWrapper> diseaseInfoList,Long hospitalCode);

	/**
	 * @Description:获取HisMappingDisease
	 * @author:ztg
	 * @time:2017年4月20日 上午11:34:46
	 */
	public List<HisDiseaseInfoWrapper>  getHisMappingDisease(HisDiseaseInfoWrapper hisDiseaseInfoWrapper);
	
	
}

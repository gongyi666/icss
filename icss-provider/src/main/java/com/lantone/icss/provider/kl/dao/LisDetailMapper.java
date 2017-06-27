package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.LisDetail;
import com.lantone.icss.api.kl.model.wrapper.LisDetailWrapper;

public interface LisDetailMapper extends EntityMapper<LisDetail, LisDetailWrapper, Long>{

	/**
	 * @Description:根据lisId获取lis明细信息
	 * @author:luwg
	 * @time:2017年1月19日 下午4:18:50
	 */
	public List<LisDetailWrapper> getLisDetailByLisId(Long lisId);
	
	/**
	 * @Description:根据疾病获取检验明细
	 * @author:luwg
	 * @time:2017年2月28日 上午10:09:16
	 */
	public List<LisDetailWrapper> getLisDetailByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:根据his套餐获取icss检验明细
	 * @author:luwg
	 * @time:2017年3月6日 下午5:01:46
	 */
	public List<LisDetailWrapper> getLisDetail(Map<String,Object> paramMap);

	/**
	 * @Description: 根据code获取检验明细
	 * @author:ztg  
	 * @time:2017年3月23日 下午2:39:31
	 */
	public LisDetailWrapper getLisDetailByCode(Map<String,Object> paramMap);

	/**
	 * @Description:根据code查询明细
	 * @author:ztg  
	 * @time:2017年3月24日 上午11:35:35
	 */
	//public List<LisDetailWrapper> getLisDetailByInfo(LisProjectWrapper lisProject);
	
	/**
	 * @Description:根据套餐查询明细
	 * @author:ztg  
	 * @time:2017年3月24日 上午11:35:35
	 */
	//public List<LisDetailWrapper> getLisDetailByDetail(LisProjectWrapper lisProject);
	
	
	/**
	 * @Description: 根据频次获取lisDetail
	 * @author:ztg  
	 * @time:2017年3月23日 下午1:20:11
	 */
	public List<LisDetailWrapper> getLisDetailByDoctorId(Map map);
	
	
	/**
	 * @Description: 检索
	 * @author:ztg  
	 * @time:2017年3月24日 下午5:25:12
	 */
	public List<LisDetailWrapper> searchLisDetail(Map map);
	
	
	/**
	 * @Description:根据类型获取明细
	 * @author:ztg  
	 * @time:2017年3月27日 上午9:20:54
	 */
	public List<LisDetailWrapper> getLisDetailByType(Long type);
	
	
	/**
	 * @Description:根据lisId获取明细信息
	 * @author:ztg
	 * @time:2017年4月5日 下午1:47:31
	 */
	public List<LisDetailWrapper> getLisDetailByInfoId(Long lisId);
	
}

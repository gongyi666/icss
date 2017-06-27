package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;


import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.LisInfo;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;

public interface LisInfoMapper extends EntityMapper<LisInfo, LisInfoWrapper, Long>{

	/**
	 * @Description:根据疾病获取lis信息
	 * @author:luwg
	 * @time:2017年1月9日 下午1:21:14
	 */
	public List<LisInfoWrapper> getLisByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:检索lis信息
	 * @author:luwg
	 * @time:2017年1月9日 下午2:36:47
	 */
	public List<LisInfoWrapper> getLisByInput(LisInfoWrapper info);
	
	/**
	 * @Description:获取默认的检验项
	 * @author:luwg
	 * @time:2017年1月18日 下午1:59:16
	 */
	public List<LisInfoWrapper> getCommonLis(Map<String,Object> paramMap);
	
	/**
	 * @Description:根据检验类型获取检验项目
	 * @author:luwg
	 * @time:2017年1月19日 下午1:25:53
	 */
	public List<LisInfoWrapper> getLisByType(Long lisTypeId);
	
	
	/**
	 * @Description: 根据code获取套餐信息
	 * @author:ztg  
	 * @time:2017年3月23日 下午12:21:22
	 */
	public LisInfoWrapper getLisProject(Map map);
	
	
	/**
	 * @Description:根据lisId和医院code获取hisDetailCode
	 * @author:ztg
	 * @time:2017年4月5日 上午9:40:35
	 */
	public String[] getHisDetailCodeByInfo(LisInfoWrapper info);
	 public  LisInfoWrapper getHisListCodeByInfo(LisInfoWrapper info);
	
	/**
	 * @Description:根据医生使用频次获取
	 * @author:ztg
	 * @time:2017年4月7日 下午2:38:55
	 */
	public List<LisInfoWrapper> getLisInfoByDoctorId(LisInfoWrapper info); 
	
	/**
	 * @Description:高频推送
	 * @author:ztg
	 * @time:2017年6月4日 下午1:16:30
	 */
	public List<LisInfoWrapper> getHighFrequencyPush(LisInfoWrapper info); 
	
	/**
	 * @Description:根据ID检索子项信息
	 * @author:csp
	 * @time:2017年6月1日 下午4:38:55
	 */
	public List<LisInfoWrapper> getLisByRetrieval(RetrievalInfoWrapper info); 
	
	/**
	 * @Description: 根据纵向id获取明细
	 * @author:ztg
	 * @time:2017年4月18日 下午7:14:29
	 */
	public List<LisInfoWrapper> getLisInfoByPortrait(Long id); 
	
	public List<LisInfoWrapper> getLisInfosByCode(List<String> codes);
}

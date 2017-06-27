package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.SubitemInfo;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;

public interface SubitemInfoMapper extends EntityMapper<SubitemInfo, SubitemInfoWrapper, Long>{

	
	/**
	 * @Description:获取子项信息(多个参数)
	 * @author:ztg
	 * @time:2017年6月9日 下午2:28:50
	 */
	public List<SubitemInfoWrapper> getSubitemByParam(SubitemInfoWrapper param);
	
	/**
	 * @Description:获取子项信息
	 * @author:luwg
	 * @time:2017年2月24日 下午2:25:47
	 */
	public List<SubitemInfoWrapper> getSubitemByPortrait(Long portraitId);
	
	/**
	 * @Description:检索子项信息
	 * @author:luwg
	 * @time:2017年2月24日 下午3:48:20
	 */
	public List<SubitemInfoWrapper> searchSubitemInfo(Map<String,Object> paramMap);
	
	/**
	 * @Description:获取初始化子项信息
	 * @author:luwg
	 * @time:2017年2月27日 上午10:47:24
	 */
	public List<SubitemInfoWrapper>  getInitSubitemInfo(Map<String,Object> paramMap);
	
	
	/**
	 * @Description:获取疾病对应的子项
	 * @author:luwg
	 * @time:2017年2月27日 下午1:36:22
	 */
	public List<SubitemInfoWrapper> getSubitemByDiseaseId(Map<String,Object> paramMap);
	
	/**
	 * @Description:获取特殊子项
	 * @author:luwg
	 * @time:2017年3月3日 上午9:26:42
	 */
	public List<SubitemInfoWrapper> getSpecialSubitem(@Param("type") String type);
	
	/**
	 * @Description:多项检索子项信息
	 * @author:csp
	 * @time:2017年4月28日 上午10:37:33
	 */
	public List<SubitemInfoWrapper> searchSubitemInfoByRetrivalinfo(Map<String,Object> paramMap);
	
	/**
	 * @Description:根据ID检索子项信息
	 * @author:csp
	 * @time:2017年4月28日 上午10:37:33
	 */
	public List<SubitemInfoWrapper> getSubitemInfoByRetrieval(RetrievalInfoWrapper info);
	
	/**
	 * @Description:根据standardId检索子项信息
	 * @author wuwy
	 * @date 2017年5月4日
	 * @param standardId
	 * @param size
	 * @return
	 */
	public List<SubitemInfoWrapper> getSubitemInfoByStandardId(Map<String,Object> map);
	
	/**
	 * @Description:根据ID检索子项信息
	 * @author:csp
	 * @time:2017年5月16日 上午19:04:33
	 */
	public SubitemInfoWrapper getSubitemInfoById(Map<String,Object> map);

	
	public List<SubitemInfoWrapper> getsubitemInfosByCode(List<String> codes);

	
	/**
	 * @Description:高频推送，只推送频次>2
	 * @author:ztg
	 * @time:2017年6月1日 下午4:30:02
	 */
	public List<SubitemInfoWrapper>  highFrequencyPush(Map<String,Object> paramMap);
	

}

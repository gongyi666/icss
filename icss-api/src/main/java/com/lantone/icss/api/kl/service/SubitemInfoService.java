package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.kl.model.SubitemInfo;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;

public interface SubitemInfoService extends BaseService<SubitemInfo, SubitemInfoWrapper, Long>{

	/**
	 * @Description:检索子项信息
	 * @author:luwg
	 * @time:2017年2月24日 下午3:43:25
	 */
	public List<SubitemInfoWrapper> searchSubitemInfo(String inputstr,Integer size, String type);
	
	/**
	 * @Description:获取既往史信息
	 * @author:luwg
	 * @time:2017年2月27日 上午9:28:42
	 */
	public List<SubitemInfoWrapper> getInitSubitemInfo(Long[] diseaseIds,Long doctorId,Integer size,String sexType,Integer age,String deptNo,String hospitalCode,String type, String notIds, String notCodes,String inputstr,List<Long> standardIds);
	
	/**
	 * @Description: 常用接口(高频+常见)
	 * @author:ztg
	 * @time:2017年6月2日 下午2:40:30
	 */
	public List<SubitemInfoWrapper> getUsual(Long[] diseaseIds,Long doctorId,Integer size,String sexType,Integer age,String deptNo,String hospitalCode,String type, String notIds, String notCodes,String inputstr,List<Long> standardIds);
	
	/**
	 * @description:根据standardId检索子项信息
	 * @author wuwy
	 * @date 2017年5月4日
	 */
	public List<SubitemInfoWrapper> getSubitemInfoByStandardId(Long standardId,Integer size);
	
	/**
	 * @Description:根据ID检索子项信息
	 * @author:csp
	 * @time:2017年5月16日 上午19:04:33
	 */
	public SubitemInfoWrapper getSubitemInfoById(Long id);
	
	public List<SubitemInfoWrapper> getsubitemInfosByCode(List<String> codes);
}

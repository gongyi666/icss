package com.lantone.icss.provider.his.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.HisDeptInfo;
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;

public interface HisDeptInfoMapper extends EntityMapper<HisDeptInfo, HisDeptInfoWrapper, Long>{
	/**
	 * @Description:根据医院编号获取科室信息
	 * @author:CSP
	 * @time:2017年2月28日 下午17:30:15
	 */
	public List<HisDeptInfoWrapper>  getDeptByHospitalList(HisDeptInfoWrapper hisDeptInfoWrapper);
	
	/**
	 * @Description:根据医院编号批量删除科室信息
	 * @author:CSP
	 * @time:2017年2月28日 下午17:30:15
	 */
	public void  deleteDeptList(Long hospitalCode);
	
	/**
	 * @Description:根据医院编号批量插入科室信息
	 * @author:CSP
	 * @time:2017年2月28日 下午17:30:15
	 */
	public void  insertDept(HisDeptInfoWrapper hisDeptInfoWrapper);
}

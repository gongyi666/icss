package com.lantone.icss.api.his.service;

import java.util.List;

import com.lantone.core.service.BaseService;
import com.lantone.icss.api.his.model.HisDeptInfo;
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;

public interface HisDeptInfoService extends BaseService<HisDeptInfo, HisDeptInfoWrapper, Long>{
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
	
	/**
	 * @Description:根据医院编号批量插入科室信息
	 * @author:CSP
	 * @time:2017年2月28日 下午17:30:15
	 */
	public void  insertDeptList(List<HisDeptInfoWrapper> deptInfoList);
	
	/**
	 * @Description:根据医院编号批量删除和插入科室信息
	 * @author:CSP
	 * @time:2017年2月28日 下午17:30:15
	 */
	public void  delAndinsertDept(List<HisDeptInfoWrapper> deptInfoList,Long hospitalCode);
}

package com.lantone.icss.provider.his.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.his.model.HisDeptInfo;
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;
import com.lantone.icss.api.his.service.HisDeptInfoService;
import com.lantone.icss.provider.his.dao.HisDeptInfoMapper;

@Service
public class HisDeptInfoServiceImpl extends BaseServiceImpl<HisDeptInfo, HisDeptInfoWrapper, Long> implements HisDeptInfoService{

	@Autowired
	HisDeptInfoMapper hisDeptInfoMapper;
	
	@Override
	public List<HisDeptInfoWrapper> getDeptByHospitalList(HisDeptInfoWrapper hisDeptInfoWrapper) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(hisDeptInfoWrapper.getHospitalCode())){
			List<HisDeptInfoWrapper> deptInfoList=hisDeptInfoMapper.getDeptByHospitalList(hisDeptInfoWrapper);
			return deptInfoList;
		}
		return null;
	}

	@Override
	public void deleteDeptList(Long hospitalCode) {
		// TODO Auto-generated method stub
		hisDeptInfoMapper.deleteDeptList(hospitalCode);
	}

	@Override
	public void insertDept(HisDeptInfoWrapper hisDeptInfoWrapper) {
		// TODO Auto-generated method stub
		hisDeptInfoMapper.insertDept(hisDeptInfoWrapper);
	}

	@Override
	public void insertDeptList(List<HisDeptInfoWrapper> deptInfoList) {
		// TODO Auto-generated method stub
		for (HisDeptInfoWrapper h : deptInfoList) {
			if (h.getStatus() == null) {// 如果从his得到为null,则手动赋值为1
				h.setStatus("1");
			}
			hisDeptInfoMapper.insertDept(h);
		}
	}
	
	@Override
	public void delAndinsertDept(List<HisDeptInfoWrapper> deptInfoList, Long hospitalCode) {
		// TODO Auto-generated method stub
		if (hospitalCode != null) {
			hisDeptInfoMapper.deleteDeptList(hospitalCode);
			HisDeptInfoWrapper hisd = new HisDeptInfoWrapper();
			List<HisDeptInfoWrapper> hisdCount = hisDeptInfoMapper.getDeptByHospitalList(hisd);
			if (hisdCount.size() <= 0) {
				for (HisDeptInfoWrapper h : deptInfoList) {
					if (h.getStatus() == null) {// 如果从his得到为null,则手动赋值为1
						h.setStatus("1");
					}
					hisDeptInfoMapper.insertDept(h);
				}
			}
		}
	}


}

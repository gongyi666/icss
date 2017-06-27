package com.lantone.icss.provider.kl.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.GroupDrugDetail;
import com.lantone.icss.api.kl.model.wrapper.GroupDrugDetailWrapper;
import com.lantone.icss.api.kl.service.GroupDrugDetailService;
import com.lantone.icss.provider.kl.dao.GroupDrugDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class GroupDrugDetailServiceImpl extends BaseServiceImpl<GroupDrugDetail, GroupDrugDetailWrapper, Long> implements GroupDrugDetailService {
	
	@Autowired
	private GroupDrugDetailMapper groupDrugDetailMapper;

	@Autowired
	private void setEntityMapper() {
		super.setEntityMapper(groupDrugDetailMapper);
	}

}

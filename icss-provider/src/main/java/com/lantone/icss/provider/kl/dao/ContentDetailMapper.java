package com.lantone.icss.provider.kl.dao;

import java.util.List;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.ContentDetail;
import com.lantone.icss.api.kl.model.wrapper.ContentDetailWrapper;

public interface ContentDetailMapper extends EntityMapper<ContentDetail, ContentDetailWrapper, Long>{

	/**
	 * @Description:根据内容id获取内容明细
	 * @author:luwg
	 * @time:2016年12月15日 上午11:28:22
	 */
	public List<ContentDetailWrapper> getContentDetailByContentId(Long contentId);
}

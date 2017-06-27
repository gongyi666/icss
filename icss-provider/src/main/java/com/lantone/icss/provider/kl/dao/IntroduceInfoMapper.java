package com.lantone.icss.provider.kl.dao;
import java.util.Map;
import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.kl.model.IntroduceInfo;
import com.lantone.icss.api.kl.model.wrapper.IntroduceInfoWrapper;

public interface IntroduceInfoMapper extends EntityMapper<IntroduceInfo, IntroduceInfoWrapper, Long> {

	/**
	 * @Description:通过id和类型获取information
	 * @author:ztg
	 * @time:2017年4月6日 下午3:57:04
	 */
	public IntroduceInfoWrapper getByitemIdAndType(Map map);
}
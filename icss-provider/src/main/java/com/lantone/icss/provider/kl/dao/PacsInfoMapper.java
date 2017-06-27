package com.lantone.icss.provider.kl.dao;

import java.util.List;
import java.util.Map;
import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.PacsStructuring;
import com.lantone.icss.api.kl.model.PacsInfo;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.RetrievalInfoWrapper;

public interface PacsInfoMapper extends EntityMapper<PacsInfo, PacsInfoWrapper, Long>{

	/**
	 * @Description:根据部位id获取Pacs
	 * @author:luwg
	 * @time:2017年1月9日 下午2:05:29
	 */
	public List<PacsInfoWrapper> getPacsByPositionId(Long positionId);
	

	/**
	 * @Description:检索
	 * @author:ztg
	 * @time:2017年6月14日 下午2:58:07
	 */
	public List<PacsInfoWrapper> searchPacs(PacsInfoWrapper info);
	
	
	/**
	 * @Description:根据疾病获取检验信息
	 * @author:luwg
	 * @time:2017年1月20日 下午1:57:01
	 */
	public List<PacsInfoWrapper> getPacsByDiseaseId(Long diseaseId);
	
	/**
	 * @Description:获取医生常用的检查项目
	 * @author:luwg
	 * @time:2017年1月22日 下午2:17:16
	 */
	public List<PacsInfoWrapper> getCommonPacs(Long doctorId);
	
	/**
	 * @Description:获取医生常用的检查项目
	 * @author:luwg
	 * @time:2017年3月3日 下午2:59:34
	 */
	public List<PacsInfoWrapper> getPacsByDoctor(Map<String,Object> paramMap);
	
	/**
	 * @Description:常用=高频+常见
	 * @author:ztg
	 * @time:2017年6月4日 下午2:36:46
	 */
	public List<PacsInfoWrapper> getHighFrequencyPush(Map<String,Object> paramMap);
	
	/**
	 * @Description:获取医生常用的检查项目
	 * @author:csp
	 * @time:2017年6月2日 下午11:12:34
	 */
	public List<PacsInfoWrapper> getPacsByRetrieval(RetrievalInfoWrapper info);
	
	/**
	 * @Description:获取his检查信息
	 * @author:luwg
	 * @time:2017年3月9日 下午3:32:55
	 */
	public List<HisPacsStructuringWrapper> getHisPacsInfo(PacsStructuring structuring);
	
	/**
	 * @Description:获取his检查信息
	 * @author:luwg
	 * @time:2017年3月12日 上午10:33:29
	 */
	public List<HisPacsStructuringWrapper> getHisPacs(Map<String,Object> paramMap);


	/**
	 * @Description: 获取hisMapping列表
	 * @author:ztg
	 * @time:2017年3月31日 下午4:32:49
	 */
	public List<PacsInfoWrapper> getHisPacsByPartAndAppar(PacsInfoWrapper info);
	
	
	/**
	 * @Description: 根据纵向id获取明细
	 * @author:ztg
	 * @time:2017年4月18日 下午7:14:29
	 */
	public List<PacsInfoWrapper> getPacsInfoByPortrait(Long id);

	
	/**
	 * @Description:根据id获取信息
	 * @author:ztg
	 * @time:2017年4月18日 下午7:14:29
	 */
	public PacsInfoWrapper selectByPrimaryKey(Long id); 
	
	
	public List<PacsInfoWrapper> getPacsInfosByCode(List<String> codes);
	
	public List<PacsInfoWrapper> getPacsBystandardIds(List<Long> standardIds);
	
}

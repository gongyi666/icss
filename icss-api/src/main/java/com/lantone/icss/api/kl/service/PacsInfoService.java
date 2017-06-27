package com.lantone.icss.api.kl.service;

import java.util.List;
import java.util.Map;

import com.lantone.core.api.Response;
import com.lantone.core.service.BaseService;
import com.lantone.icss.api.data.PacsData;
import com.lantone.icss.api.his.model.Wrapper.HisPacsStructuringWrapper;
import com.lantone.icss.api.kl.model.PacsInfo;
import com.lantone.icss.api.kl.model.wrapper.PacsApparatusWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;

/**
 * @Description:pacs服务
 * @author : luwg
 * @time : 2017年1月9日 下午1:43:08
 * 
 */
public interface PacsInfoService extends BaseService<PacsInfo, PacsInfoWrapper, Long>{


	/**
	 * @Description:检索pacs信息可过滤
	 * @author:ztg
	 * @time:2017年6月14日 下午2:55:16
	 */
	public List<PacsInfoWrapper> searchPacs(PacsInfoWrapper info);
	
	/**
	 * @Description:获取检查内容
	 * @author:luwg
	 * @time:2017年3月2日 下午3:24:06
	 */
	public Map<String,Object> getPacsContent(PacsInfoWrapper pacs);
	
	/**
	 * @Description:获取检查内容
	 * @author:luwg
	 * @time:2017年3月8日 下午1:29:21
	 */
	public Map<String,Object> getPacsContent(String apparatusCode,String partCode);
	
	/**
	 * @Description:选择检查内容
	 * @author:luwg
	 * @time:2017年3月7日 下午1:49:30
	 */
	public Map<String,Object> choosePacsContent(String apparatusCode,String partCode,String organCode);
	
	/**
	 * @Description:加载检查信息
	 * @author:luwg
	 * @time:2017年3月3日 下午2:34:15
	 */
	public List<PacsInfoWrapper> initPacsInfo(Long[] diseaseIds,Long doctorId,Integer size,String notIds,String sexType,Integer age,String deptNo,String hospitalCode, String inputstr, List<Long> standardIds);
	
	
	/**
	 * @Description:常用=高频+常见
	 * @author:ztg
	 * @time:2017年6月4日 下午2:24:30
	 */
	public List<PacsInfoWrapper> getUsual(Long[] diseaseIds,Long doctorId,Integer size,String notIds,String sexType,Integer age,String deptNo,String hospitalCode, String inputstr, List<Long> standardIds);
	
	/**
	 * @Description:生成结构化
	 * @author:luwg
	 * @time:2017年3月9日 下午3:25:53
	 */
	public List<HisPacsStructuringWrapper> generateStructuring(HisPacsStructuringWrapper pacs);
	
	/**
	 * @Description:检查结构化信息
	 * @author:luwg
	 * @time:2017年3月12日 上午9:46:55
	 */
	public List<HisPacsStructuringWrapper> postPacsStructing(List<PacsData> pacsData);

	/**
	 * @Description:返回his套餐
	 * @author:ztg
	 * @time:2017年3月31日 下午4:02:36
	 */
	public PacsInfoWrapper getHisPacs(PacsInfoWrapper info);
	
	
	/**
	 * @Description:根据部位id获取手段
	 * @author:ztg
	 * @time:2017年4月19日 上午10:38:17
	 */
	public List<PacsApparatusWrapper> getByPartId(Long partId);
	
	
	/**
	 * @Description: 根据Id获取部位所对应的手段
	 * @author:ztg
	 * @time:2017年4月19日 下午2:13:11
	 */
	public Map<String,Object> getByPacsInfoId(Long id);
	
	public List<PacsInfoWrapper> getPacsInfosByCode(List<String> codes);
	
}

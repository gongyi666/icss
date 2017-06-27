package com.lantone.icss.trans.langtong.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.kl.RequestBillItem;
import com.lantone.icss.trans.langtong.model.response.kl.HISLisInfo;
import com.lantone.icss.trans.langtong.util.LangTongUtil;
import com.lantone.icss.trans.langtong.model.response.kl.ResponseLisInfo;

/**
 * @Description:当前检验套餐返回
 * @author : panxz
 * @time : 2017年2月22日 上午11:44:20
 *测试url:
 * http://localhost:8080/icss-trans/langtong/lisInfo/getCurrentLisInfo
 * 入参:
 * hospital=331023&id=[1232,1666,1668,1535]或者1232,1666,1668,1535
 */
@Controller
@RequestMapping("/langtong/lisInfo")
public class CurrentLisInfo {


	private static Logger logger = LoggerFactory.getLogger(CurrentLisInfo.class);

	/**
	 * @Description:从HIS获取当前检验套餐
	 * @author:panxz
	 * @time:2017年2月22日 上午11:44:20
	 
	 */
	@ResponseBody
	@RequestMapping("/getCurrentLisInfo")
	public Response<List<LisInfoWrapper>> getLisInfo(@RequestBody LisInfoWrapper lis){
		Response<List<LisInfoWrapper>> response = new Response<List<LisInfoWrapper>>();
		response.start();
		try {
			String tranType="300";
			String tranKey="300";
			String stffNo=lis.getDoctorCode();
			String hospitalId = lis.getHospitalCode();
			String departId =lis.getDeptNo();
			//String tranData="{hospitalId:"+hospital+",itemIds':["+id+"]}";
			RequestBillItem billItems=new RequestBillItem();
			billItems.setHospitalId(lis.getHospitalCode());
			billItems.setItemIds(lis.getItemIds());
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId,billItems);
			System.out.println(requestData);
			HttpApi<ResponseLisInfo> api = new HttpApi<ResponseLisInfo>();
			ResponseLisInfo responseLisInfo = api.doPostReplace(InitConfig.get("langtong.his.url"), requestData, ResponseLisInfo.class);
			if(responseLisInfo.getRet() == 0){
				//封装返回的对象
				List<HISLisInfo> lisList = responseLisInfo.getData();
				if(lisList != null) {
					List<LisInfoWrapper> lisInfoList = transformLisInfo(lisList);
					response.setData(lisInfoList);
				}
			}else{
				logger.error("获取检验项目明细对应套餐失败");
				return response.failure("获取检验项目明细对应套餐失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取检验项目明细对应套餐失败");
		}
		return response.success();
	}
	
	/**
	 * @Description:将HIS检验套餐，转为ICSS检验套餐
	 * @author:panxz
	 * @time:2017年2月17日 上午16:24:00
	 */
	private List<LisInfoWrapper> transformLisInfo(List<HISLisInfo> lisList){
		List<LisInfoWrapper> returnList = Lists.newArrayList();
		for(HISLisInfo detail : lisList){
			LisInfoWrapper lisDetail = new LisInfoWrapper();
			lisDetail.setCode(detail.getID());
			lisDetail.setSpell(detail.getChinaSpell());
			lisDetail.setName(detail.getBinName());
			lisDetail.setStatus(detail.getBinState());
			lisDetail.setFiveStroke(detail.getFiveStroke());
			lisDetail.setTotalCount(detail.getTotalCount());
			lisDetail.setTotalFee(detail.getTotalFee());
			returnList.add(lisDetail);
		}
		
		return returnList;
	}
}

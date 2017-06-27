package com.lantone.icss.web.his.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.HisLisPacsByBlsTypeInfo;
import com.lantone.icss.api.his.model.Wrapper.HisLisPacsByBlsTypeInfoWrapper;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResponseLisPacsByBlsType;

/**
 * @author 吴文俊
 * @data 2017年3月7日 杭州朗通信息技术有限公司
 * @describe 检验检查按分类获取项目
 */
@Controller
@RequestMapping("/his/his_lisOrPacs_detail")
public class HisLisPacsByBlsTypeController {
	private static Logger logger = LoggerFactory.getLogger(HisLisPacsByBlsTypeController.class);

	@RequestMapping("/get_his_lis_pacs_By_type")
	public Response<List<HisLisPacsByBlsTypeInfo>> getHisOrLisByBlsType(HisLisPacsByBlsTypeInfoWrapper wrapper) {
		Response<List<HisLisPacsByBlsTypeInfo>> response = new Response<List<HisLisPacsByBlsTypeInfo>>();
		response.start();
		try {
			HttpApi<ResponseLisPacsByBlsType> api = new HttpApi<ResponseLisPacsByBlsType>();
			ResponseLisPacsByBlsType resData = api.doPost(InitConfig.get("get.lis.pacs.bytype.url"), wrapper, ResponseLisPacsByBlsType.class);
			response.setData(resData.getData());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return response.failure("获取失败");
		}
		return response.success();
	}
}

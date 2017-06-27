package com.lantone.icss.trans.shaoyifu.util;

import com.lantone.core.utils.mapper.JsonMapper;
import com.lantone.icss.trans.shaoyifu.model.RequestData;

/**
 * @author 吴文俊
 * @data   2017年2月16日
 * 杭州朗通信息技术有限公司
 * @describe 工具类
 */
public class  LangTongUtil {

	public static <T> RequestData getRequestData(String authentication, T jsonObject,String empid,String hospitalCode,String sourceSystem,String tradeCode,String tradeTime) {

		RequestData<T> requestData =  new RequestData<T>();
		requestData.setAuthentication(authentication);
		requestData.setData(jsonObject);
		requestData.setEmpid(empid);
		requestData.setHospitalCode(hospitalCode);
		requestData.setSourceSystem(sourceSystem);
		requestData.setTradeCode(tradeCode);
		requestData.setTradeTime(tradeTime);
		return requestData;
	}

}

package com.lantone.icss.trans.langtong.util;

import com.lantone.core.utils.mapper.JsonMapper;
import com.lantone.icss.trans.langtong.model.RequestData;

/**
 * @author 吴文俊
 * @data   2017年2月16日
 * 杭州朗通信息技术有限公司
 * @describe 工具类
 */
public class  LangTongUtil {

	public static <T> RequestData getRequestData(String tranType, String tranKey, String stffNo, String hospitalId, String departId, T 	jsonObject) {

		RequestData requestData =  new RequestData();
		requestData.setTranKey(tranKey);
		requestData.setTranType(tranType);	
		requestData.setStffNo(stffNo);
		requestData.setHospitalId(hospitalId);
		requestData.setDepartId(departId);
		JsonMapper jsonMapper = new JsonMapper();
		requestData.setTranData(jsonMapper.toJson(jsonObject));
		return requestData;
	}

}

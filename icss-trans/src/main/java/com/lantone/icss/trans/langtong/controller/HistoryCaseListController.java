package com.lantone.icss.trans.langtong.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseInWrapper;
import com.lantone.icss.api.at.model.wrapper.HistoryCaseOutWrapper;
import com.lantone.icss.trans.common.listen.InitConfig;
import com.lantone.icss.trans.langtong.model.RequestData;
import com.lantone.icss.trans.langtong.model.request.at.RequestHistoryCase;
import com.lantone.icss.trans.langtong.model.response.at.HistoryCase;
import com.lantone.icss.trans.langtong.model.response.at.ResponseHistoryCase;
import com.lantone.icss.trans.langtong.util.LangTongUtil;

@Controller
@RequestMapping("/langtong")
public class HistoryCaseListController {

	private static Logger logger = LoggerFactory.getLogger(HistoryCaseListController.class);

	@ResponseBody
	@RequestMapping("/history_case/list")
	public Response<List<HistoryCaseOutWrapper>> getHistoryCaseList(
			@RequestBody HistoryCaseInWrapper historyCaseInWrapper) {
		Response<List<HistoryCaseOutWrapper>> response = new Response<List<HistoryCaseOutWrapper>>();
		response.start();
		try {
			/***
			 * 组装接口对象
			 */
			String tranType = "203";
			String tranKey = "203";
			String stffNo = "";
			String hospitalId = "";
			String departId = "";

			RequestHistoryCase requestHistoryCase = new RequestHistoryCase();
			requestHistoryCase.setPageNum(historyCaseInWrapper.getPageNum());
			requestHistoryCase.setPageSize(historyCaseInWrapper.getPageSize());
			requestHistoryCase.setPatientId(historyCaseInWrapper.getPatientId());
			RequestData requestData = LangTongUtil.getRequestData(tranType, tranKey, stffNo, hospitalId, departId,
					requestHistoryCase);
			/**
			 * 调用HIS接口
			 */
			HttpApi<ResponseHistoryCase> httpApi = new HttpApi<ResponseHistoryCase>();
			ResponseHistoryCase responseHistoryCase = httpApi.doPostReplace(InitConfig.get("langtong.his.url"),
					requestData, ResponseHistoryCase.class);
			if (responseHistoryCase.getRet() == 0) {// 返回数据
				/**
				 * 组装返回对象
				 */
				List<HistoryCase> historyCase = new ArrayList<HistoryCase>();
				historyCase = responseHistoryCase.getData().getList();
				List<HistoryCaseOutWrapper> historyCaseOutWrapper = putHistoryCaseUtil(historyCase);
				response.setData(historyCaseOutWrapper);
			} else {
				logger.error("获取待诊列表失败");
				return response.failure("获取待诊列表失败");
			}
		} catch (Exception e) {
			logger.error("调用HIS接口失败");
			return response.failure("调用HIS接口失败");
		}

		return response.success();
	}

	private List<HistoryCaseOutWrapper> putHistoryCaseUtil(List<HistoryCase> historyCase) {
		List<HistoryCaseOutWrapper> historyCaseOutWrapper = new ArrayList<HistoryCaseOutWrapper>();

		/*
		 * for(WaitingListInfo wl : waitingListInfo){ WaitingListInWrapper ww =
		 * new WaitingListInWrapper(); ww.setId(wl.getId());
		 * 
		 * 
		 * 
		 * waitingListInWrapper.add(ww); }
		 */
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < historyCase.size(); i++) {
			HistoryCaseOutWrapper hw = new HistoryCaseOutWrapper();
			hw.setId(historyCase.get(i).getId());
			try {
				hw.setVisDate(sdf.parse(historyCase.get(i).getVisDate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hw.setSffId(historyCase.get(i).getSffId());
			hw.setSffName(historyCase.get(i).getSffName());
			hw.setPatId(historyCase.get(i).getPatId());
			hw.setPatName(historyCase.get(i).getPatName());
			hw.setDisName(historyCase.get(i).getDisName());
			historyCaseOutWrapper.add(hw);
		}
		return historyCaseOutWrapper;
	}
}
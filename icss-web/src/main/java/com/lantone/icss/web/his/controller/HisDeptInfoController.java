package com.lantone.icss.web.his.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lantone.core.api.Response;
import com.lantone.core.utils.http.HttpApi;
import com.lantone.icss.api.his.model.Wrapper.HisDeptInfoWrapper;
import com.lantone.icss.api.his.service.HisDeptInfoService;
import com.lantone.icss.web.common.listen.InitConfig;
import com.lantone.icss.web.his.trans.res.ResponseDeptInfoList;

/**
 * @Description:科室信息控制层
 * @author CSP
 * @time : 2017年2月28日 下午14:28:33
 *
 */
@Controller
@RequestMapping("/his/deptinfo")
public class HisDeptInfoController {
	private static Logger logger = LoggerFactory.getLogger(HisDeptInfoController.class);

	@Reference
	HisDeptInfoService hisDeptInfoService;

	/**
	 * @Description:从HIS获取科室信息并返回给前端
	 * @author:CSP
	 * @time:2017年2月28日 下午14:28:33
	 * 
	 */
	@ResponseBody
	@RequestMapping("/get_deptinfolist_his")
	public Response<List<HisDeptInfoWrapper>> getDeptInfoList(HisDeptInfoWrapper deptinfo) {
		Response<List<HisDeptInfoWrapper>> response = new Response<List<HisDeptInfoWrapper>>();
		response.start();
		try {
			HttpApi<ResponseDeptInfoList> api = new HttpApi<ResponseDeptInfoList>();
			// 根据hospitalCode从his获取所有科室信息
			ResponseDeptInfoList responseDeptInfoList = api.doPost(InitConfig.get("getdeptinfo.list"), deptinfo,
					ResponseDeptInfoList.class);
			if (responseDeptInfoList.getRet() == 0) {
				List<HisDeptInfoWrapper> deptInfoList = responseDeptInfoList.getData();
				if (deptInfoList.size() > 0) {
					// 根据hospitalCode从icss获取科室信息
					List<HisDeptInfoWrapper> deptInfoWrapperList = hisDeptInfoService.getDeptByHospitalList(deptinfo);
					if (deptInfoWrapperList.size() > 0) {//判断，如果ICSS有数据，先删再插,如果没有数据，直接插入从his取得的数据
						//根据hospitalCode删除icss原有数据
						hisDeptInfoService.delAndinsertDept(deptInfoList, Long.parseLong(deptinfo.getHospitalCode()));
//						hisDeptInfoService.deleteDeptList(Long.parseLong(deptinfo.getHospitalCode()));
//						for (HisDeptInfoWrapper h : deptInfoList) {
//							if (h.getStatus() == null) {// 如果从his得到为null,则手动赋值为1
//								h.setStatus("1");
//							}
//							hisDeptInfoService.insertDept(h);
//						}
					} else {
						hisDeptInfoService.insertDeptList(deptInfoList);
//						for (HisDeptInfoWrapper h : deptInfoList) {
//							if (h.getStatus() == null) {// 如果从his得到为null,则手动赋值为1
//								h.setStatus("1");
//							}
//							hisDeptInfoService.insertDept(h);
//						}
					}
				}
				response.setData(responseDeptInfoList.getData());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("获取全部科室信息失败!");
			return response.failure("获取全部科室信息失败!");
		}

		return response;
	}

}

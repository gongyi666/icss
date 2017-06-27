package com.lantone.icss.provider.at.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.at.model.InquiryInfo;
import com.lantone.icss.api.at.model.PatientInfo;
import com.lantone.icss.api.at.model.wrapper.DoctorHabitCountWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryDetailWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryInfoWrapper;
import com.lantone.icss.api.at.model.wrapper.InquiryLogWrapper;
import com.lantone.icss.api.at.model.wrapper.InquirySaveWrapper;
import com.lantone.icss.api.at.service.InquiryInfoService;
import com.lantone.icss.provider.at.dao.DoctorHabitCountMapper;
import com.lantone.icss.provider.at.dao.InquiryDetailMapper;
import com.lantone.icss.provider.at.dao.InquiryInfoMapper;
import com.lantone.icss.provider.at.dao.InquiryLogMapper;

import net.sf.json.JSONArray;

@Service
public class InquiryInfoServiceImpl extends BaseServiceImpl<InquiryInfo, InquiryInfoWrapper, Long> implements InquiryInfoService{

	@Autowired
	InquiryInfoMapper inquiryInfoMapper;
	@Autowired
	InquiryDetailMapper inquiryDetailMapper;
	@Autowired
	DoctorHabitCountMapper doctorHabitCountMapper;
	@Autowired
	InquiryLogMapper inquiryLogMapper;
	
	@Override
	public Long insert(InquiryInfoWrapper info) {
		Long id = null;
		JSONArray jArray= JSONArray.fromObject(info.getDetailStr());
		List<InquiryDetailWrapper> detailList  = JSONArray.toList(jArray,InquiryDetailWrapper.class);
		if(info.getId() == null || info.getId().toString() == "" || info.getId() == -1) {
			inquiryInfoMapper.insertInfo(info);//添加主表，返回主键值
			id = info.getId();//不能合并写
		} else {
			id = info.getId();
			InquiryInfoWrapper bean = inquiryInfoMapper.selectByPrimaryKey(id);
			if(bean != null) { //删除原先的问诊记录表
				//1、添加日志 (如果更新)
				//addLog(info, detailList);
				//2、更新主表
				inquiryInfoMapper.updateInfo(info);
				//3、删除原明细表
				inquiryDetailMapper.delDetailByQuiryId(info.getId());
			}
		} 
		
		//添加明细表
		List<InquiryDetailWrapper> dbDetail =  inquiryDetailMapper.findByInquiryId(info.getId());
		if(detailList != null && detailList.size() > 0) {
			int i = 1;
			for(InquiryDetailWrapper detail: detailList) {
				detail.setOrderNo(i++);
				detail.setInquiryId(id);
				setProperty(dbDetail, detail, info.getDoctorName()); //设置问诊时间和医生姓名
				saveOrUpdateHabit(info,detail); //更新频次
			}
			inquiryDetailMapper.insertDetail(detailList); //添加明细
		}
		return id;
	}

	/**
	 * @Description:添加日志
	 * @author:ztg
	 * @time:2017年5月24日 上午10:19:56
	 */
	public void addLog(InquiryInfoWrapper info, List<InquiryDetailWrapper> details) {
		List<InquiryDetailWrapper> dbDetail =  inquiryDetailMapper.findByInquiryId(info.getId());//数据库信息
		List<InquiryLogWrapper> insertLogs = new ArrayList<InquiryLogWrapper>();
		
		InquiryLogWrapper logWrapper = new InquiryLogWrapper();
		logWrapper.setInquiryId(info.getId());
		
		InquiryDetailWrapper searchBean = new InquiryDetailWrapper();
		searchBean.setInquiryId(info.getId());
		List<InquiryDetailWrapper> resultDetail = inquiryDetailMapper.index(searchBean);
		
		if(CollectionUtils.isNotEmpty(resultDetail)) {
			//更新日志
			for(InquiryDetailWrapper db: dbDetail) {
				boolean flag = false;
				for(InquiryDetailWrapper detail: details) { //itemId、type相同，itemDescribe不同
					if(detail.getType().intValue() == db.getType().intValue()
							&& detail.getItemId().longValue() == db.getItemId().longValue()
							&& !detail.getItemDescribe().equals(db.getItemDescribe())) {
						flag = true;
						break;
					}
				}
				if(flag) {
					InquiryLogWrapper addBean = creatBean(db, "1");
					if(addBean != null) {
						insertLogs.add(addBean);
					}
				}
				
			}
			
			//删除日志
			for(InquiryDetailWrapper db: dbDetail) {
				boolean flag = false;
				for(InquiryDetailWrapper detail: details) {
					if(detail.getType().intValue() == db.getType().intValue()
							&& detail.getItemId().longValue() == db.getItemId().longValue()) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					InquiryLogWrapper addBean = creatBean(db, "2");
					if(addBean != null) {
						insertLogs.add(addBean);
					}
				}
			}
		}
			
		if(CollectionUtils.isNotEmpty(insertLogs)) {
			inquiryLogMapper.insertLog(insertLogs);
		}
	}
	
	//设置对象属性
	public void setProperty(List<InquiryDetailWrapper> dbDetail, InquiryDetailWrapper detail, String doctorName ) {
		InquiryDetailWrapper flag = null;
		//内容没有更改：时间和医生姓名取原先的值，有更改：更新时间和医生姓名
		for(InquiryDetailWrapper bean : dbDetail) {
			if(detail.getItemId().longValue() == bean.getItemId().longValue() 
					&& detail.getType().intValue() ==bean.getType().intValue()
					&& detail.getItemDescribe().equals(bean.getItemDescribe())) {
				flag = new InquiryDetailWrapper();
				flag.setClinicTime(bean.getClinicTime());
				flag.setDoctorName(bean.getDoctorName());
				break;
			}
		}
		if(flag != null) {
			detail.setClinicTime(flag.getClinicTime());
			detail.setDoctorName(flag.getDoctorName());
		} else {
			detail.setClinicTime(new Date());
			detail.setDoctorName(doctorName);
		}
	}
	
	//创建日志对象
	public InquiryLogWrapper creatBean(InquiryDetailWrapper bean, String operation) {
		if(bean == null) {
			return null;
		}
		InquiryLogWrapper addBean = new InquiryLogWrapper();
		addBean.setDoctorName(bean.getDoctorName());
		addBean.setInquiryId(bean.getInquiryId());
		addBean.setItemDescribe(bean.getItemDescribe());
		addBean.setItemId(bean.getItemId());
		addBean.setRemark(bean.getRemark());
		addBean.setType(bean.getType());
		addBean.setClinicTime(bean.getClinicTime());
		addBean.setOperation(operation); //2表示删除
		return addBean;
	}
	
	@Override
	public List<InquiryInfoWrapper> index(InquiryInfoWrapper info) {
		List<InquiryInfoWrapper> infoList = inquiryInfoMapper.index(info);
		if(infoList != null && info.getDetailFlag() != null && info.getDetailFlag() == 1) { //查询明细
			for(InquiryInfoWrapper bean: infoList) {
				bean.setDetails(inquiryDetailMapper.findByInquiryId(bean.getId()));
				//添加日志信息
				InquiryLogWrapper searchBean = new InquiryLogWrapper();
				searchBean.setInquiryId(info.getId());
				bean.setLogs(inquiryLogMapper.index(searchBean));
			}
		}
		return infoList;
	}

	@Override
	public Map<String, Object> getDiseaseAndDept(InquiryInfoWrapper info) {
		Map resultMap = new HashMap<String, Object>();
		resultMap.put("diseaseResult", inquiryInfoMapper.getNameAndId(info)); //获取病名和id(去重)
		resultMap.put("deptResult", inquiryInfoMapper.getDeptAndCode(info));  //获取科室名称和Code(去重)
		return resultMap;
	}

	@Override
	public void updateInfo(InquiryInfoWrapper info) {
		inquiryInfoMapper.updateInfo(info);
	}

	@Override
	public Long insert(InquirySaveWrapper data) {
		Long id = null;
		InquiryInfoWrapper info = data.getInfo();//主表信息
		
		if(info.getId() == null || info.getId().toString() == "" || info.getId() == -1) {
			inquiryInfoMapper.insertInfo(info);//添加主表，返回主键值
			id = info.getId();//不能合并写
		} else {
			id = info.getId();
			InquiryInfoWrapper bean = inquiryInfoMapper.selectByPrimaryKey(id);
			if(bean != null) { //删除原先的问诊记录表
				//1、更新主表
				inquiryInfoMapper.updateInfo(info);
				//2、删除原明细表
				inquiryDetailMapper.delDetailByQuiryId(info.getId());
			}
		} 
		List<InquiryDetailWrapper> detailList = data.getDetails();
		if(detailList != null && detailList.size() > 0) {
			int i = 1;
			for(InquiryDetailWrapper detail: detailList) {
				detail.setOrderNo(i++);
				detail.setInquiryId(id);
				saveOrUpdateHabit(info,detail); //更新频次
			}
			inquiryDetailMapper.insertDetail(detailList); //添加明细
		}
		return id;
	}
	
	
	/**
	 * @Description: 新增或更新频次
	 * @author:ztg
	 * @time:2017年5月22日 下午1:18:07
	 */
	public void saveOrUpdateHabit(InquiryInfoWrapper info, InquiryDetailWrapper detail) {
		DoctorHabitCountWrapper pc = new DoctorHabitCountWrapper();
		pc.setDoctorId(Long.parseLong(info.getDoctorId()));
		pc.setHabitId(detail.getItemId() == null ? "":detail.getItemId().toString());
		pc.setType(detail.getType() == null ? "": detail.getType().toString());
		DoctorHabitCountWrapper habitBean = doctorHabitCountMapper.getDoctorHabitCount(pc);
		if(habitBean == null) { //新增
			pc.setCountNum(1L);
			pc.setName(detail.getItemName());
			doctorHabitCountMapper.insertDoctorHabitCount(pc);
		} else {
			pc.setCountNum(habitBean.getCountNum() + 1L);
			doctorHabitCountMapper.updateDoctorHabitCount(pc);
		}
	}

	@Override
	public String getLangtongInquiry(PatientInfo info) {
		String resultMsg = "";
		StringBuffer sb = new StringBuffer();
		
		InquiryInfoWrapper inquiryInfoWrapper = new InquiryInfoWrapper();
		inquiryInfoWrapper.setPatientId(String.valueOf(info.getId()));
		List<InquiryInfoWrapper> resultInfo = inquiryInfoMapper.index(inquiryInfoWrapper);
		
		if(CollectionUtils.isNotEmpty(resultInfo)) {
			InquiryDetailWrapper detailBean = new InquiryDetailWrapper();
			detailBean.setInquiryId(resultInfo.get(0).getId());
			List<InquiryDetailWrapper> detailResult = inquiryDetailMapper.index(detailBean);
			
			//1:症状，2既往史，3其他史，4体征，5化验，6器查，7诊断，8治疗，9现病史
			sb.append("0;|0;|");
			if(CollectionUtils.isNotEmpty(detailResult)) {
				for(InquiryDetailWrapper bean: detailResult) {
					if(bean.getType() == 1) { //主诉
						sb.append(bean.getItemDescribe()+";");
					}
				}
				sb.append("|");
			}
			
			if(CollectionUtils.isNotEmpty(detailResult)) {
				for(InquiryDetailWrapper bean: detailResult) {
					if(bean.getType() == 9) { //现病史
						sb.append(bean.getItemDescribe()+";");
					}
				}
				sb.append("|");
			}
		
			if(CollectionUtils.isNotEmpty(detailResult)) {
				for(int i = 2; i <= 8; i++) {
					for(InquiryDetailWrapper bean: detailResult) {
						if(bean.getType() == i) { 
							if(i == 2 || i == 3) {
								String desc = bean.getItemDescribe();
								sb.append(desc.substring(0, desc.lastIndexOf("$title$")) + ";");
							} else {
								sb.append(bean.getItemDescribe() + ";");
							}
						}
					}
					sb.append("|");
				}
			}
			
			resultMsg = sb.toString();
			resultMsg = resultMsg.substring(0, resultMsg.length()-1);
		}
		return resultMsg;
	}

}

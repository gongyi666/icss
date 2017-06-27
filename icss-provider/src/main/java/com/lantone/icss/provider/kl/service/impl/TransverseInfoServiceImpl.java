package com.lantone.icss.provider.kl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lantone.core.service.BaseServiceImpl;
import com.lantone.icss.api.kl.model.TransverseInfo;
import com.lantone.icss.api.kl.model.wrapper.DiseaseInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.DrugInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.LisInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.PacsInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDiseaseWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithLisWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWithPacsWrapper;
import com.lantone.icss.api.kl.model.wrapper.PortraitInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.SubitemInfoWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDiseaseWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithDrugWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithLisWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWithPacsWrapper;
import com.lantone.icss.api.kl.model.wrapper.TransverseInfoWrapper;
import com.lantone.icss.api.kl.service.TransverseInfoService;
import com.lantone.icss.provider.kl.dao.DiseaseInfoMapper;
import com.lantone.icss.provider.kl.dao.DrugInfoMapper;
import com.lantone.icss.provider.kl.dao.LisInfoMapper;
import com.lantone.icss.provider.kl.dao.PacsInfoMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoWithDiseaseMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoWithDrugMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoWithLisMapper;
import com.lantone.icss.provider.kl.dao.PortraitInfoWithPacsMapper;
import com.lantone.icss.provider.kl.dao.SubitemInfoMapper;
import com.lantone.icss.provider.kl.dao.TransverseInfoMapper;

/**
 * @Description:横向菜单服务时间
 * @author : luwg
 * @time : 2017年2月24日 上午10:42:12
 * 
 */
@Service
public class TransverseInfoServiceImpl extends
		BaseServiceImpl<TransverseInfo, TransverseInfoWrapper, Long> implements
		TransverseInfoService {

	@Autowired
	TransverseInfoMapper transverseInfoMapper;
	@Autowired
	PortraitInfoMapper portraitInfoMapper;
	@Autowired
	SubitemInfoMapper subitemInfoMapper;
	@Autowired
	DrugInfoMapper drugInfoMapper;
	@Autowired
	PortraitInfoWithDrugMapper portraitInfoWithDrugMapper;
	@Autowired
	PortraitInfoWithLisMapper portraitInfoWithLisMapper;
	@Autowired
	PortraitInfoWithPacsMapper portraitInfoWithPacsMapper;
	@Autowired
	PortraitInfoWithDiseaseMapper portraitInfoWithDiseaseMapper;
	@Autowired
	LisInfoMapper lisInfoMapper;
	@Autowired
	PacsInfoMapper pacsInfoMapper;
	@Autowired
	DiseaseInfoMapper diseaseInfoMapper;

	@Override
	public List<TransverseInfoWrapper> getTransverseByType(
			SubitemInfoWrapper param) {
		List<TransverseInfoWrapper> returnInfo = transverseInfoMapper
				.getTransverseInfoByType(param.getType());
		for (TransverseInfoWrapper transverse : returnInfo) {
			List<PortraitInfoWrapper> portraitList = portraitInfoMapper
					.getPortraitByTransverse(transverse.getId());
			for (PortraitInfoWrapper portrait : portraitList) {
				recursionPortrait(portrait, param);
			}
			transverse.setPortraitList(portraitList);
		}
		return returnInfo;
	}

	/**
	 * @Description:递归查询及其子项
	 * @author:luwg
	 * @time:2017年2月24日 下午2:14:45
	 */
	private void recursionPortrait(PortraitInfoWrapper portrait,
			SubitemInfoWrapper param) {
		if (!"1".equals(portrait.getEndLevel())) {
			List<PortraitInfoWrapper> childList = portraitInfoMapper
					.getPortraitByParent(portrait.getId());
			for (PortraitInfoWrapper child : childList) {
				recursionPortrait(child, param);
			}
			portrait.setPortraitList(childList);
		} else {
			param.setPortraitId(portrait.getId().toString());
			List<SubitemInfoWrapper> subitemList = subitemInfoMapper
					.getSubitemByParam(param);
			portrait.setSubitemList(subitemList);
		}
	}

	@Override
	public List<TransverseInfoWithDrugWrapper> getTransverseWithDrug(String type) {
		List<TransverseInfoWithDrugWrapper> transList = transverseInfoMapper
				.getTransverseInfoWithDrug(type);
		for (TransverseInfoWithDrugWrapper trans : transList) {
			List<PortraitInfoWithDrugWrapper> portraitlist = portraitInfoWithDrugMapper
					.getPortraitWithDrugByTransverse(trans.getId());
			trans.setPortraitList(portraitlist);
			/*
			 * int index=0; for(PortraitInfoWithDrugWrapper portrait :
			 * portraitlist) { if(index<=0) {
			 * recursionPortraitWithDrug(portrait); } else{
			 * recursionPortraitWith(portrait); } index++; }
			 */
			for (PortraitInfoWithDrugWrapper portrait : portraitlist) {

				

				recursionPortraitWith(portrait);
				recursionPortraitWithDrug(portrait);
			}

			/*
			 * for(PortraitInfoWithDrugWrapper portrait : portraitlist) {
			 * recursionPortraitWithDrag(portrait); }
			 */
		
		}
		return transList;
	}

	public void recursionPortraitWithDrug(PortraitInfoWithDrugWrapper portrait) {
		//if (!"1".equals(portrait.getEndLevel())) {
		//List<PortraitInfoWithDrugWrapper> portList = portraitInfoWithDrugMapper
			//		.getPortraitWithDrugByParent(portrait.getId());
			//portrait.setPortraitList(portList);
		//} else {
			List<DrugInfoWrapper> drugList = drugInfoMapper
					.getDrugInfoByPortraitId(portrait.getId());
			portrait.setDrugList(drugList);
		//}
	}

	public void recursionPortraitWithLis(PortraitInfoWithLisWrapper portrait) {
		if (!"1".equals(portrait.getEndLevel())) {
			List<PortraitInfoWithLisWrapper> portList = portraitInfoWithLisMapper
					.getPortraitWithLisByParent(portrait.getId());
			for (PortraitInfoWithLisWrapper child : portList) {
				recursionPortraitWithLis(child);
			}
			portrait.setPortraitList(portList);
		} else {
			List<LisInfoWrapper> detailList = lisInfoMapper
					.getLisInfoByPortrait(portrait.getId());
			portrait.setDetailList(detailList);
		}
	}

	public void recursionPortraitWithPacs(PortraitInfoWithPacsWrapper portrait) {
		if (!"1".equals(portrait.getEndLevel())) {
			List<PortraitInfoWithPacsWrapper> portList = portraitInfoWithPacsMapper
					.getPortraitWithPacsByParent(portrait.getId());
			for (PortraitInfoWithPacsWrapper child : portList) {
				recursionPortraitWithPacs(child);
			}
			portrait.setPortraitList(portList);
		} else {
			List<PacsInfoWrapper> detailList = pacsInfoMapper
					.getPacsInfoByPortrait(portrait.getId());
			portrait.setDetailList(detailList);
		}
	}

	public void recursionPortraitWithDisease(
			PortraitInfoWithDiseaseWrapper portrait) {
		if (!"1".equals(portrait.getEndLevel())) {
			List<PortraitInfoWithDiseaseWrapper> portList = portraitInfoWithDiseaseMapper
					.getPortraitWithDiseaseByParent(portrait.getId());
			for (PortraitInfoWithDiseaseWrapper child : portList) {
				recursionPortraitWithDisease(child);
			}
			portrait.setPortraitList(portList);
		} else {
			List<DiseaseInfoWrapper> detailList = diseaseInfoMapper
					.getDiseaseInfoByPortrait(portrait.getId());
			portrait.setDetailList(detailList);
		}
	}

	@Override
	public List<TransverseInfoWithLisWrapper> getTransverseWithLis(String type) {
		List<TransverseInfoWithLisWrapper> returnInfo = transverseInfoMapper
				.getTransverseInfoWithLis(type);
		for (TransverseInfoWithLisWrapper transverse : returnInfo) {
			List<PortraitInfoWithLisWrapper> portraitList = portraitInfoWithLisMapper
					.getPortraitWithLisByTransverse(transverse.getId());
			for (PortraitInfoWithLisWrapper portrait : portraitList) {
				recursionPortraitWithLis(portrait);
			}
			transverse.setPortraitList(portraitList);
		}
		return returnInfo;
	}

	@Override
	public List<TransverseInfoWithPacsWrapper> getTransverseWithPacs(String type) {
		List<TransverseInfoWithPacsWrapper> returnInfo = transverseInfoMapper
				.getTransverseInfoWithPacs(type);
		for (TransverseInfoWithPacsWrapper transverse : returnInfo) {
			List<PortraitInfoWithPacsWrapper> portraitList = portraitInfoWithPacsMapper
					.getPortraitWithPacsByTransverse(transverse.getId());
			for (PortraitInfoWithPacsWrapper portrait : portraitList) {
				recursionPortraitWithPacs(portrait);
			}
			transverse.setPortraitList(portraitList);
		}
		return returnInfo;
	}

	@Override
	public List<TransverseInfoWithDiseaseWrapper> getTransverseWithDisease(
			String type) {
		List<TransverseInfoWithDiseaseWrapper> returnInfo = transverseInfoMapper
				.getTransverseInfoWithDisease(type);
		for (TransverseInfoWithDiseaseWrapper transverse : returnInfo) {
			List<PortraitInfoWithDiseaseWrapper> portraitList = portraitInfoWithDiseaseMapper
					.getPortraitWithDiseaseByTransverse(transverse.getId());
			for (PortraitInfoWithDiseaseWrapper portrait : portraitList) {
				recursionPortraitWithDisease(portrait);
			}
			transverse.setPortraitList(portraitList);
		}
		return returnInfo;
	}

	public void recursionPortraitWith(PortraitInfoWithDrugWrapper portrait) {
		//if (!"1".equals(portrait.getEndLevel())) {
			List<PortraitInfoWithDrugWrapper> portList = portraitInfoWithDrugMapper
					.getPortraitWithDrugByParent(portrait.getId());
			portrait.setPortraitList(portList);
			for(PortraitInfoWithDrugWrapper pro:portList)
			{
				recursionPortraitWithDrug(portrait);
				recursionPortraitWith(pro);
			}
			
		//}
	}

	@Override
	public PortraitInfoWithDrugWrapper getPortraitInfoById(Long portraitId) {
		// TODO Auto-generated method stub
		PortraitInfoWithDrugWrapper portraitInfoWrapper = portraitInfoWithDrugMapper
				.getPortraitById(portraitId);
		recursionPortraitWithDrug(portraitInfoWrapper);
		return portraitInfoWrapper;
	}

}

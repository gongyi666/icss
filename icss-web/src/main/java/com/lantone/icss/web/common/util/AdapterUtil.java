package com.lantone.icss.web.common.util;

import java.util.List;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.lantone.icss.api.kl.DiseasePhysical;
import com.lantone.icss.api.kl.model.KeyWord;
import com.lantone.icss.api.kl.model.PopularKnowledge;
import com.lantone.icss.api.kl.model.ReasonBasic;
import com.lantone.icss.api.kl.service.DiseaseInfoService;
import com.lantone.icss.api.kl.service.PopularKnowledgeService;

/**
 * @author 吴文俊
 * @data 2016年12月15日 杭州朗通信息技术有限公司
 * @describe 推理适配器
 */
@Component
public class AdapterUtil {

	@Reference
	PopularKnowledgeService popularKnowledgeService;
	@Reference
	DiseaseInfoService diseaseInfoService;

	public List<Long> run(String[] newData, KeyWord keyWord) {
		List<Long> diagnosisStandardIds = Lists.newArrayList();
		/**
		 * 高血压适配
		 */
		if (keyWord.getId().longValue() == 14L) {
			List<Integer> ssy = Lists.newArrayList();
			List<Integer> szy = Lists.newArrayList();
			for (int i = 0; i < newData.length; i++) {
				if ("[".equals(newData[i])) {
					ssy.add(Integer.valueOf(i));
				}
				if ("]".equals(newData[i])) {
					szy.add(Integer.valueOf(i));
				}
			}
			String ssyStr = "";
			String szyStr = "";

			for (int i = ((Integer) ssy.get(0)).intValue() + 1; i < ((Integer) szy.get(0)).intValue(); i++) {
				ssyStr = ssyStr + newData[i];
			}
			for (int i = ((Integer) ssy.get(1)).intValue() + 1; i < ((Integer) szy.get(1)).intValue(); i++) {
				szyStr = szyStr + newData[i];
			}
			int ssyNumber = Integer.valueOf(ssyStr).intValue();
			int szyNumber = Integer.valueOf(szyStr).intValue();
			/**
			 * 高血压指标
			 */
			if ((ssyNumber / szyNumber >= 140 / 90)) {
				ReasonBasic reasonBasic = new ReasonBasic();
				reasonBasic.setNoun(keyWord.getId());
				reasonBasic.setDiscribeWordId(Long.valueOf(158L));
				Long diagnosisStandardId = mateBasis(reasonBasic);
				if (diagnosisStandardId > 0) {
					diagnosisStandardIds.add(mateBasis(reasonBasic));
				}
			}
			/**
			 * 一级高血压指标
			 */
			if ((ssyNumber / szyNumber >= 140 / 90) && (ssyNumber / szyNumber < 159 / 99)) {
				ReasonBasic reasonBasic = new ReasonBasic();
				reasonBasic.setNoun(keyWord.getId());
				reasonBasic.setDiscribeWordId(Long.valueOf(195L));
				Long diagnosisStandardId = mateBasis(reasonBasic);
				if (diagnosisStandardId > 0) {
					diagnosisStandardIds.add(mateBasis(reasonBasic));
				}
			}
			/**
			 * 二级高血压指标
			 */
			if ((ssyNumber / szyNumber >= 159 / 99) && (ssyNumber / szyNumber < 179 / 109)) {
				ReasonBasic reasonBasic = new ReasonBasic();
				reasonBasic.setNoun(keyWord.getId());
				reasonBasic.setDiscribeWordId(Long.valueOf(195L));
				Long diagnosisStandardId = mateBasis(reasonBasic);
				if (diagnosisStandardId > 0) {
					diagnosisStandardIds.add(mateBasis(reasonBasic));
				}
			}
			/**
			 * 三级高血压指标
			 */
			if ((ssyNumber / szyNumber >= 180 / 110)) {
				ReasonBasic reasonBasic = new ReasonBasic();
				reasonBasic.setNoun(keyWord.getId());
				reasonBasic.setDiscribeWordId(Long.valueOf(195L));
				Long diagnosisStandardId = mateBasis(reasonBasic);
				if (diagnosisStandardId > 0) {
					diagnosisStandardIds.add(mateBasis(reasonBasic));
				}
			}

		}
		/**
		 * 体温适配
		 */
		if (keyWord.getId().longValue() == 9L) {
			List<Integer> tw1 = Lists.newArrayList();
			List<Integer> tw2 = Lists.newArrayList();
			for (int i = 0; i < newData.length; i++) {
				if ("[".equals(newData[i])) {
					tw1.add(Integer.valueOf(i));
				}
				if ("]".equals(newData[i])) {
					tw2.add(Integer.valueOf(i));
				}
			}
			String twStr = "";

			for (int i = ((Integer) tw1.get(0)).intValue() + 1; i < ((Integer) tw2.get(0)).intValue(); i++) {
				twStr = twStr + newData[i];
			}
			int twNumber = Integer.valueOf(twStr).intValue();
			if (twNumber >= 37.5D) {
				ReasonBasic reasonBasic = new ReasonBasic();
				reasonBasic.setNoun(keyWord.getId());
				reasonBasic.setDiscribeWordId(Long.valueOf(158L));
				Long diagnosisStandardId = mateBasis(reasonBasic);
				if (diagnosisStandardId > 0) {
					diagnosisStandardIds.add(mateBasis(reasonBasic));
				}
			}
		}
		return diagnosisStandardIds;
	}

	public Long mateBasis(ReasonBasic reasonBasic) {
		Long diagnosisStandardId = Long.valueOf(0L);
		PopularKnowledge popularKnowledge = this.popularKnowledgeService.selectPopularKnowledgeByName(reasonBasic.getNoun(), reasonBasic.getDiscribeWordId());
		// if (popularKnowledge != null) {
		// diagnosisStandardId = popularKnowledge.getDiagnosisStandardId();
		// }
		return diagnosisStandardId;
	}


	public DiseasePhysical inputDiseasePhysicalData(KeyWord keyWord, DiseasePhysical diseasePhysical) {
		PopularKnowledge popularKnowledge = this.popularKnowledgeService.selectPopularKnowledgeByKeyWordId(keyWord.getId());
		if (popularKnowledge != null) {
			if ("2".equals(popularKnowledge.getType())) {// 体征部位
				diseasePhysical.setDetailStandId(popularKnowledge.getStandId());
			}
			else{
				diseasePhysical.setPhyStandId(popularKnowledge.getStandId());
			}
	}
		return diseasePhysical;
	}
	

}
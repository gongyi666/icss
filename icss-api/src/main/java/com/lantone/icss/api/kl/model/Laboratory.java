package com.lantone.icss.api.kl.model;

/**
 * @author 吴文俊
 * @data   2016年12月22日
 * 杭州朗通信息技术有限公司
 * @describe 推理所用的实验室检查类
 */
public class Laboratory {
	/**
	 * 实验室检查名称
	 */
	Long laboratoryStandId;
	/**
	 * 实验室检查结果
	 */
	Long laboratoryStandResultId;
	public Long getLaboratoryStandId() {
		return laboratoryStandId;
	}
	public void setLaboratoryStandId(Long laboratoryStandId) {
		this.laboratoryStandId = laboratoryStandId;
	}
	public Long getLaboratoryStandResultId() {
		return laboratoryStandResultId;
	}
	public void setLaboratoryStandResultId(Long laboratoryStandResultId) {
		this.laboratoryStandResultId = laboratoryStandResultId;
	}
	
}

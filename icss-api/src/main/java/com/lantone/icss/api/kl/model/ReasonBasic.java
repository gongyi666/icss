package com.lantone.icss.api.kl.model;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2016年12月14日
 * 杭州朗通信息技术有限公司
 * @describe 进入推理的基类
 */
public class ReasonBasic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**名词id*/
	Long noun;
	/**描述词id*/
	Long discribeWordId;
	public Long getNoun() {
		return noun;
	}
	public void setNoun(Long noun) {
		this.noun = noun;
	}
	public Long getDiscribeWordId() {
		return discribeWordId;
	}
	public void setDiscribeWordId(Long discribeWordId) {
		this.discribeWordId = discribeWordId;
	}
}

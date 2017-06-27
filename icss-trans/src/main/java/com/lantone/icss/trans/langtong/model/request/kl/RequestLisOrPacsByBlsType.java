package com.lantone.icss.trans.langtong.model.request.kl;

import java.io.Serializable;

/**
 * @author 吴文俊
 * @data   2017年3月7日
 * 杭州朗通信息技术有限公司
 * @describe 根据项目分类标记获取LisPacs
 */
public class RequestLisOrPacsByBlsType  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hosiptalId;
	private String blsType;
	public String getHosiptalId() {
		return hosiptalId;
	}
	public void setHosiptalId(String hosiptalId) {
		this.hosiptalId = hosiptalId;
	}
	public String getBlsType() {
		return blsType;
	}
	public void setBlsType(String blsType) {
		this.blsType = blsType;
	}
	
	
}

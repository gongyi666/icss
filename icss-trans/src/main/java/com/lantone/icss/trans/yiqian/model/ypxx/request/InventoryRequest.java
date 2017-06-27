package com.lantone.icss.trans.yiqian.model.ypxx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *	Description: 获取药品信息
 *	Company:杭州朗通信息技术有限公司 
 *	@author : CSP
 *	@time :上午12:19:26
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class InventoryRequest {
		@XmlElement(name = "SPEID")//规格ID
		private String speId = "";
		
		@XmlElement(name = "MANID")//生产厂商ID
		private String manId = "";
		
		@XmlElement(name = "HOSIPTALID")//机构
		private String hosiptalId = "";

		@XmlElement(name = "STOREID")//药房ID
		private String storeId = "";
		
		@XmlElement(name = "RECIPEQUANTITY")//开单数量
		private String recipeQuantity = "";

		public String getSpeId() {
			return speId;
		}

		public void setSpeId(String speId) {
			this.speId = speId;
		}

		public String getManId() {
			return manId;
		}

		public void setManId(String manId) {
			this.manId = manId;
		}

		public String getHosiptalId() {
			return hosiptalId;
		}

		public void setHosiptalId(String hosiptalId) {
			this.hosiptalId = hosiptalId;
		}

		public String getStoreId() {
			return storeId;
		}

		public void setStoreId(String storeId) {
			this.storeId = storeId;
		}

		public String getRecipeQuantity() {
			return recipeQuantity;
		}

		public void setRecipeQuantity(String recipeQuantity) {
			this.recipeQuantity = recipeQuantity;
		}
	
		
}

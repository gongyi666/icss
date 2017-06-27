package com.lantone.icss.trans.yiqian.model.LT300.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/***Title: 
*	Description: 当前套餐返回入参
*	Company:杭州朗通信息技术有限公司 
	@author pxz
	@date 2017年5月10日
*/
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class LT300Request {
		@XmlElement(name = "hosiptalId")
		private String hosiptalId = "";
		@XmlElement(name = "itemIds")
		private String itemIds[] = null;
		
		
		public String getHosiptalId() {
			return hosiptalId;
		}
		public void setHosiptalId(String hosiptalId) {
			this.hosiptalId = hosiptalId;
		}
		public String[] getItemIds() {
			return itemIds;
		}
		public void setItemIds(String[] itemIds) {
			this.itemIds = itemIds;
		}
		
		
		
}
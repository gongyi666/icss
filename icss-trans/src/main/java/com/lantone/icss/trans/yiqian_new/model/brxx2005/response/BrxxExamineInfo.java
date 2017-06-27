package com.lantone.icss.trans.yiqian_new.model.brxx2005.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description:检查检验诊疗单明细
 * @author:ztg
 * @time:2017年5月12日 下午12:17:36
 */

public class BrxxExamineInfo implements Serializable {
	private String exdNum;			//序号
	private String itemId;			//检查项目ID
	private String itemName;		//检查项目名称
	private String exdPrice;		//单价
	private String exdQuantity;		//数量
	private String sffId;			//执行医生
	private String depId;			//执行科室
	private String itemUnit;		//单位
	
	private  List<BrxxExamineDetail> row;
	
	
	public List<BrxxExamineDetail> getRow() {
		return row;
	}
	public void setRow(List<BrxxExamineDetail> row) {
		this.row = row;
	}
	public String getExdNum() {
		return exdNum;
	}
	public void setExdNum(String exdNum) {
		this.exdNum = exdNum;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getExdPrice() {
		return exdPrice;
	}
	public void setExdPrice(String exdPrice) {
		this.exdPrice = exdPrice;
	}
	public String getExdQuantity() {
		return exdQuantity;
	}
	public void setExdQuantity(String exdQuantity) {
		this.exdQuantity = exdQuantity;
	}
	public String getSffId() {
		return sffId;
	}
	public void setSffId(String sffId) {
		this.sffId = sffId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	
	
	
	
	
	
	
}

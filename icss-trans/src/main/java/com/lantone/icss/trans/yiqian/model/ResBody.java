package com.lantone.icss.trans.yiqian.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class ResBody<T> {
	T response;

	public T getResquest() {
		return response;
	}

	public void setResquest(T response) {
		this.response = response;
	}
}

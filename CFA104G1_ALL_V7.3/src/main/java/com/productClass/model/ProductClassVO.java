package com.productClass.model;

import java.io.Serializable;

public class ProductClassVO implements Serializable{
	private Integer pcNo;
	private String pcName;
	
	public ProductClassVO() {
	}

	public ProductClassVO(Integer pcNo, String pcName) {
		super();
		this.pcNo = pcNo;
		this.pcName = pcName;
	}

	public Integer getPcNo() {
		return pcNo;
	}

	public void setPcNo(Integer pcNo) {
		this.pcNo = pcNo;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

}

package com.product.model;

import java.io.Serializable;

public class ProductVO implements Serializable{
	private Integer piNo;
	private String piName;
	private Integer pcNo;
	private String piContent;
	private Integer piPri;
	private Integer piStock;
	private String piSta;
	
	public ProductVO() {
	}

	public ProductVO(Integer pi_no, String pi_name, Integer pc_no, String pi_content, Integer pi_pri, Integer pi_stock,
			String pi_sta) {
		super();
		this.piNo = pi_no;
		this.piName = pi_name;
		this.pcNo = pc_no;
		this.piContent = pi_content;
		this.piPri = pi_pri;
		this.piStock = pi_stock;
		this.piSta = pi_sta;
	}

	public Integer getPiNo() {
		return piNo;
	}

	public void setPiNo(Integer pi_no) {
		this.piNo = pi_no;
	}

	public String getPiName() {
		return piName;
	}

	public void setPiName(String pi_name) {
		this.piName = pi_name;
	}

	public Integer getPcNo() {
		return pcNo;
	}

	public void setPcNo(Integer pc_no) {
		this.pcNo = pc_no;
	}

	public String getPiContent() {
		return piContent;
	}

	public void setPiContent(String pi_content) {
		this.piContent = pi_content;
	}

	public Integer getPiPri() {
		return piPri;
	}

	public void setPiPri(Integer pi_pri) {
		this.piPri = pi_pri;
	}

	public Integer getPiStock() {
		return piStock;
	}

	public void setPiStock(Integer pi_stock) {
		this.piStock = pi_stock;
	}

	public String getPiSta() {
		return piSta;
	}

	public void setPiSta(String pi_sta) {
		this.piSta = pi_sta;
	}
	
	
	
}

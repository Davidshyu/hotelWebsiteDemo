package com.productImage.model;

import java.io.Serializable;

public class productImageVO implements Serializable{
	private Integer pim_no;
	private Integer pi_no;
	private byte[] pim_img;
	
	public Integer getPim_no() {
		return pim_no;
	}
	public void setPim_no(Integer pim_no) {
		this.pim_no = pim_no;
	}
	public Integer getPi_no() {
		return pi_no;
	}
	public void setPi_no(Integer pi_no) {
		this.pi_no = pi_no;
	}
	public byte[] getPim_img() {
		return pim_img;
	}
	public void setPim_img(byte[] pim_img) {
		this.pim_img = pim_img;
	}
	
	
}

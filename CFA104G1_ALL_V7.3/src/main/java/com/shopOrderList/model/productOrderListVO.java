package com.shopOrderList.model;

public class productOrderListVO {
	private Integer ol_no;		//訂單明細編號
	private Integer o_no;		//訂單編號
	private Integer pi_no;		//商品編號
	private Integer ol_proamot;	//訂購數量
	private Integer ol_pri;	//訂購商品單價
	public Integer getOl_no() {
		return ol_no;
	}
	public void setOl_no(Integer ol_no) {
		this.ol_no = ol_no;
	}
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public Integer getPi_no() {
		return pi_no;
	}
	public void setPi_no(Integer pi_no) {
		this.pi_no = pi_no;
	}
	public Integer getOl_proamot() {
		return ol_proamot;
	}
	public void setOl_proamot(Integer ol_proamot) {
		this.ol_proamot = ol_proamot;
	}
	public Integer getOl_pri() {
		return ol_pri;
	}
	public void setOl_pri(Integer ol_pri) {
		this.ol_pri = ol_pri;
	}
	
	

}

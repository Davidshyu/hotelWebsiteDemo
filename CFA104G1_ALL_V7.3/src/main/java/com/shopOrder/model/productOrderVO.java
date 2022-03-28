package com.shopOrder.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class productOrderVO implements Serializable{
	private Integer o_no;			//訂單編號
	private Timestamp o_purtime;		//訂單下單時間
	private Integer mem_no;			//會員編號
	private String o_sta;			//訂單狀態
	private String o_pay_sta;		//付款狀態
	private String o_ship_sta;		//運送狀態
	private Integer o_totalpri;		//訂單總價
	private String o_prodel;		//取貨方式
	private String o_deladrs;		//運送地址
	private String o_paymthd;		//付款方式
	private Timestamp o_shipdate;		//出貨時間
	private Integer o_delcost;		//運費
	public Integer getO_no() {
		return o_no;
	}
	public void setO_no(Integer o_no) {
		this.o_no = o_no;
	}
	public Timestamp getO_purtime() {
		return o_purtime;
	}
	public void setO_purtime(Timestamp o_purtime) {
		this.o_purtime = o_purtime;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getO_sta() {
		return o_sta;
	}
	public void setO_sta(String o_sta) {
		this.o_sta = o_sta;
	}
	public String getO_pay_sta() {
		return o_pay_sta;
	}
	public void setO_pay_sta(String o_pay_sta) {
		this.o_pay_sta = o_pay_sta;
	}
	public String getO_ship_sta() {
		return o_ship_sta;
	}
	public void setO_ship_sta(String o_ship_sta) {
		this.o_ship_sta = o_ship_sta;
	}
	public Integer getO_totalpri() {
		return o_totalpri;
	}
	public void setO_totalpri(Integer o_totalpri) {
		this.o_totalpri = o_totalpri;
	}
	public String getO_prodel() {
		return o_prodel;
	}
	public void setO_prodel(String o_prodel) {
		this.o_prodel = o_prodel;
	}
	public String getO_deladrs() {
		return o_deladrs;
	}
	public void setO_deladrs(String o_deladrs) {
		this.o_deladrs = o_deladrs;
	}
	public String getO_paymthd() {
		return o_paymthd;
	}
	public void setO_paymthd(String o_paymthd) {
		this.o_paymthd = o_paymthd;
	}
	public Timestamp getO_shipdate() {
		return o_shipdate;
	}
	public void setO_shipdate(Timestamp o_shipdate) {
		this.o_shipdate = o_shipdate;
	}
	public Integer getO_delcost() {
		return o_delcost;
	}
	public void setO_delcost(Integer o_delcost) {
		this.o_delcost = o_delcost;
	}
	
	
 
}

package com.activityOrder.model;

import java.io.Serializable;
import java.sql.Date;

public class ActivityOrderVO implements Serializable{

	private Integer activity_order_id;
	private Integer mem_id;
	private Integer activity_session_id;
	private Date order_time;
	private Integer entered_number;
	private Date activity_started;
	private Date activity_end;
	private Integer order_amount;
	private Integer order_state;
	private Integer refund_state;
	private String order_memo;
	public ActivityOrderVO() {
		super();
	}
	public Integer getActivity_order_id() {
		return activity_order_id;
	}
	public void setActivity_order_id(Integer activity_order_id) {
		this.activity_order_id = activity_order_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getActivity_session_id() {
		return activity_session_id;
	}
	public void setActivity_session_id(Integer activity_session_id) {
		this.activity_session_id = activity_session_id;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Integer getEntered_number() {
		return entered_number;
	}
	public void setEntered_number(Integer entered_number) {
		this.entered_number = entered_number;
	}
	public Date getActivity_started() {
		return activity_started;
	}
	public void setActivity_started(Date activity_started) {
		this.activity_started = activity_started;
	}
	public Date getActivity_end() {
		return activity_end;
	}
	public void setActivity_end(Date activity_end) {
		this.activity_end = activity_end;
	}
	public Integer getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Integer order_amount) {
		this.order_amount = order_amount;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public Integer getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(Integer refund_state) {
		this.refund_state = refund_state;
	}
	public String getOrder_memo() {
		return order_memo;
	}
	public void setOrder_memo(String order_memo) {
		this.order_memo = order_memo;
	}
	public ActivityOrderVO(Integer activity_order_id, Integer mem_id, Integer activity_session_id, Date order_time,
			Integer entered_number, Date activity_started, Date activity_end, Integer order_amount, Integer order_state,
			Integer refund_state, String order_memo) {
		super();
		this.activity_order_id = activity_order_id;
		this.mem_id = mem_id;
		this.activity_session_id = activity_session_id;
		this.order_time = order_time;
		this.entered_number = entered_number;
		this.activity_started = activity_started;
		this.activity_end = activity_end;
		this.order_amount = order_amount;
		this.order_state = order_state;
		this.refund_state = refund_state;
		this.order_memo = order_memo;
	}

	
}

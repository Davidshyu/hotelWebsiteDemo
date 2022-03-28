package com.activitySession.model;

import java.io.Serializable;
import java.sql.Date;

public class ActivitySessionVO implements Serializable {
	private Integer activity_session_id;
	private Integer activity_id;
	private String activity_session_name;
	private Date activity_started;
	private Date activity_end;
	private Integer activity_price;
	private Integer activity_state;
	private String status_note;
	private Integer activity_max_part;
	private Integer activity_min_part;
	private Integer entered_total;
	private Date entered_started;
	private Date entered_end;
	private Integer product_status;

	public ActivitySessionVO() {
		super();
	}

	public Integer getActivity_session_id() {
		return activity_session_id;
	}

	public void setActivity_session_id(Integer activity_session_id) {
		this.activity_session_id = activity_session_id;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}
	
	public String getActivity_session_name() {
		return activity_session_name;
	}

	public void setActivity_session_name(String activity_session_name) {
		this.activity_session_name = activity_session_name;
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

	public Integer getActivity_price() {
		return activity_price;
	}

	public void setActivity_price(Integer activity_price) {
		this.activity_price = activity_price;
	}

	public Integer getActivity_state() {
		return activity_state;
	}

	public void setActivity_state(Integer activity_state) {
		this.activity_state = activity_state;
	}

	public String getStatus_note() {
		return status_note;
	}

	public void setStatus_note(String status_note) {
		this.status_note = status_note;
	}

	public Integer getActivity_max_part() {
		return activity_max_part;
	}

	public void setActivity_max_part(Integer activity_max_part) {
		this.activity_max_part = activity_max_part;
	}

	public Integer getActivity_min_part() {
		return activity_min_part;
	}

	public void setActivity_min_part(Integer activity_min_part) {
		this.activity_min_part = activity_min_part;
	}

	public Integer getEntered_total() {
		return entered_total;
	}

	public void setEntered_total(Integer entered_total) {
		this.entered_total = entered_total;
	}

	public Date getEntered_started() {
		return entered_started;
	}

	public void setEntered_started(Date entered_started) {
		this.entered_started = entered_started;
	}

	public Date getEntered_end() {
		return entered_end;
	}

	public void setEntered_end(Date entered_end) {
		this.entered_end = entered_end;
	}
	
	public Integer getProduct_status() {
		return product_status;
	}

	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}

	public ActivitySessionVO(Integer activity_session_id, Integer activity_id, String activity_session_name, Date activity_started, Date activity_end,
			Integer activity_price, Integer activity_state, String status_note, Integer activity_max_part,
			Integer activity_min_part, Integer entered_total, Date entered_started, Date entered_end, Integer product_status) {
		super();
		this.activity_session_id = activity_session_id;
		this.activity_id = activity_id;
		this.activity_session_name = activity_session_name;
		this.activity_started = activity_started;
		this.activity_end = activity_end;
		this.activity_price = activity_price;
		this.activity_state = activity_state;
		this.status_note = status_note;
		this.activity_max_part = activity_max_part;
		this.activity_min_part = activity_min_part;
		this.entered_total = entered_total;
		this.entered_started = entered_started;
		this.entered_end = entered_end;
		this.product_status = product_status;
	}

	
}

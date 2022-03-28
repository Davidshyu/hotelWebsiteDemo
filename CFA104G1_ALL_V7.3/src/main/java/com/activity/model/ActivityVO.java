package com.activity.model;

public class ActivityVO implements java.io.Serializable {// io��J��X

	private Integer activity_id;
	private Integer activity_category_id;
	private String activity_name;
	private String activity_info;

	public ActivityVO() {
		super();
	}

	public ActivityVO(Integer activity_id, Integer activity_category_id, String activity_name, String activity_info) {
		super();
		this.activity_id = activity_id;
		this.activity_category_id = activity_category_id;
		this.activity_name = activity_name;
		this.activity_info = activity_info;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public Integer getActivity_category_id() {
		return activity_category_id;
	}

	public void setActivity_category_id(Integer activity_category_id) {
		this.activity_category_id = activity_category_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity_info() {
		return activity_info;
	}

	public void setActivity_info(String activity_info) {
		this.activity_info = activity_info;
	}

}

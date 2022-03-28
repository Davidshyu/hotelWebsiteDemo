package com.activityPhoto.model;

import java.io.Serializable;

public class ActivityPhotoVO implements Serializable{
	private Integer activity_photo_id;
	private Integer activity_id;
	private byte[] activity_photo_file;
	public ActivityPhotoVO() {
		super();
	}
	public ActivityPhotoVO(Integer activity_photo_id, Integer activity_id, byte[] activity_photo_file) {
		super();
		this.activity_photo_id = activity_photo_id;
		this.activity_id = activity_id;
		this.activity_photo_file = activity_photo_file;
	}
	public Integer getActivity_photo_id() {
		return activity_photo_id;
	}
	public void setActivity_photo_id(Integer activity_photo_id) {
		this.activity_photo_id = activity_photo_id;
	}
	public Integer getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}
	public byte[] getActivity_photo_file() {
		return activity_photo_file;
	}
	public void setActivity_photo_file(byte[] activity_photo_file) {
		this.activity_photo_file = activity_photo_file;
	}

}

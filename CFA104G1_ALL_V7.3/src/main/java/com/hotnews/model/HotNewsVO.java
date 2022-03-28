package com.hotnews.model;

import java.sql.Date;

public class HotNewsVO {
	private Integer hot_news_id;
	private String hot_news_title;	
	private String hot_news_description;
	private Date hot_news_date;
	private Boolean hot_news_status;
	private byte[] hot_news_photo;
	
	public Integer getHot_news_id() {
		return hot_news_id;
	}
	public void setHot_news_id(Integer hot_news_id) {
		this.hot_news_id = hot_news_id;
	}
	public String getHot_news_title() {
		return hot_news_title;
	}
	public void setHot_news_title(String hot_news_title) {
		this.hot_news_title = hot_news_title;
	}
	public String getHot_news_description() {
		return hot_news_description;
	}
	public void setHot_news_description(String hot_news_description) {
		this.hot_news_description = hot_news_description;
	}
	public Date getHot_news_date() {
		return hot_news_date;
	}
	public void setHot_news_date(Date hot_news_date) {
		this.hot_news_date = hot_news_date;
	}
	public Boolean getHot_news_status() {
		return hot_news_status;
	}
	public void setHot_news_status(Boolean hot_news_status) {
		this.hot_news_status = hot_news_status;
	}
	public byte[] getHot_news_photo() {
		return hot_news_photo;
	}
	public void setHot_news_photo(byte[] hot_news_photo) {
		this.hot_news_photo = hot_news_photo;
	}
	
}

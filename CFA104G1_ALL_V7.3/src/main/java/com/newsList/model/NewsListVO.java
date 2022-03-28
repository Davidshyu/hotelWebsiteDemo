package com.newsList.model;

import java.io.Serializable;

public class NewsListVO implements Serializable{
	
	private Integer newsId;		//新聞ID
	private String title;		//標題
	private String content;		//內容
	private String status;		//狀態
	private String newsComeFrom; //報導出處	
	
	public NewsListVO() {
	}

	public NewsListVO(Integer newsId, String title, String content, String status, String newsComeFrom) {
		super();
		this.newsId = newsId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.newsComeFrom = newsComeFrom;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNewsComeFrom() {
		return newsComeFrom;
	}

	public void setNewsComeFrom(String newsComeFrom) {
		this.newsComeFrom = newsComeFrom;
	}

}
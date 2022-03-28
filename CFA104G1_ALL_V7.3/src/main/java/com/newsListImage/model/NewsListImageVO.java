package com.newsListImage.model;

import java.io.Serializable;

public class NewsListImageVO implements Serializable{
	private Integer nimNo;
	private Integer newsID;
	private byte[] nimImg;
	
	public Integer getNimNo() {
		return nimNo;
	}
	public void setNimNo(Integer nimNo) {
		this.nimNo = nimNo;
	}
	public Integer getNewsID() {
		return newsID;
	}
	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}
	public byte[] getNimImg() {
		return nimImg;
	}
	public void setNimImg(byte[] nimImg) {
		this.nimImg = nimImg;
	}
	


	
}

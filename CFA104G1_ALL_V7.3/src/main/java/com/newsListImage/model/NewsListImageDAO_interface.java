package com.newsListImage.model;

import java.util.List;

public interface NewsListImageDAO_interface {
	public NewsListImageVO insert(NewsListImageVO newsListImage);
	public void update(NewsListImageVO newsListImage);
	public void delete(Integer nimNo);
	public NewsListImageVO findByPk(Integer nimNo);
	public List<NewsListImageVO> getAll();

}

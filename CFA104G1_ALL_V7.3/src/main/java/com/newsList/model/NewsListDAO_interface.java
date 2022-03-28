package com.newsList.model;

import java.util.List;

public interface NewsListDAO_interface {
	public NewsListVO insert(NewsListVO productVO);
	public void update(NewsListVO productVO);
	public void delete(Integer pi_no);
	public NewsListVO findByPK(Integer newsId);
	public NewsListVO findByTITLE(String title);
	public List<NewsListVO> getAll();

}

package com.hotnews.model;

import java.util.List;

public interface HotNewsDAO_interface {
	public void insert(HotNewsVO hotnewsVO);
    public void update(HotNewsVO hotnewsVO);
    public void updatePhoto(HotNewsVO hotnewsVO);
    public void delete(Integer hot_news_id);
    public HotNewsVO findByPrimaryKey(Integer hot_news_id);
    public List<HotNewsVO> getAll();
    public List<HotNewsVO> getAllOn();
}

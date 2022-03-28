package com.hotnews.model;

import java.sql.Date;
import java.util.List;


public class HotNewsService {
	private HotNewsDAO_interface dao;

	public HotNewsService() {
		dao = new HotNewsDAO();
	}

	public HotNewsVO addHotNews( String hot_news_title, String hot_news_description,
			Date hot_news_date, Boolean hot_news_status,byte[] hot_news_photo ) {

		HotNewsVO HotNewsVO = new HotNewsVO();

		hot_news_description = hot_news_description.trim();
		
		HotNewsVO.setHot_news_title(hot_news_title);
		HotNewsVO.setHot_news_description(hot_news_description);
		HotNewsVO.setHot_news_status(hot_news_status);
		HotNewsVO.setHot_news_date(hot_news_date);
		HotNewsVO.setHot_news_photo(hot_news_photo);
		dao.insert(HotNewsVO);

		return HotNewsVO;
	}

	public HotNewsVO updateHotNews(Integer hot_news_id, String hot_news_title, String hot_news_description,
			Date hot_news_date, Boolean hot_news_status) {

		HotNewsVO HotNewsVO = new HotNewsVO();
		HotNewsVO.setHot_news_id(hot_news_id);
		HotNewsVO.setHot_news_title(hot_news_title);
		HotNewsVO.setHot_news_description(hot_news_description);
		HotNewsVO.setHot_news_status(hot_news_status);
		HotNewsVO.setHot_news_date(hot_news_date);
		dao.update(HotNewsVO);

		return HotNewsVO;
	}
	public HotNewsVO updateHotNewsPhoto(Integer hot_news_id, String hot_news_title, byte[] hot_news_photo) {

		HotNewsVO HotNewsVO = new HotNewsVO();
		System.out.println("image data:"+hot_news_photo);
		HotNewsVO.setHot_news_id(hot_news_id);
		HotNewsVO.setHot_news_title(hot_news_title);
		HotNewsVO.setHot_news_photo(hot_news_photo);
		dao.updatePhoto(HotNewsVO);

		return HotNewsVO;
	}

	public void deleteHotNews(Integer hot_news_id) {
		dao.delete(hot_news_id);
	}

	public HotNewsVO getOneHotNews(Integer hot_news_id) {
		System.out.println("getOneHotNews:"+hot_news_id);
		return dao.findByPrimaryKey(hot_news_id);
	}

	public List<HotNewsVO> getAll() {
		return dao.getAll();
	}

	public List<HotNewsVO> getAllOn() {
		return dao.getAllOn();
	}
	
}

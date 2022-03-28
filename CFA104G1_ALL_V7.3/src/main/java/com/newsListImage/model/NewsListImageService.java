package com.newsListImage.model;
import java.util.List;

public class NewsListImageService {
private NewsListImageDAO_interface dao;
	
	public NewsListImageService() {
		dao = new NewsListImageJNDIDAO();
	}
	
	public NewsListImageVO insertNewsListImage(Integer newsID, byte[] nimImg) {
		
		NewsListImageVO newsListImageVO = new NewsListImageVO();
		
		newsListImageVO.setNewsID(newsID);
		newsListImageVO.setNimImg(nimImg);
		newsListImageVO = dao.insert(newsListImageVO);
		return newsListImageVO;
	}

	public NewsListImageVO updateNewsListImage(Integer nimNo, Integer newsID, byte[] nimImg) {
		
		NewsListImageVO newsListImageVO = new NewsListImageVO();
		
		newsListImageVO.setNimNo(nimNo);
		newsListImageVO.setNewsID(newsID);
		newsListImageVO.setNimImg(nimImg);
		dao.update(newsListImageVO);
		return newsListImageVO;
	}

	public void deleteNewsListImage(Integer nimNo) {
		dao.delete(nimNo);
	}

	public NewsListImageVO getOneNewsListImage(Integer nimNo) {
		return dao.findByPk(nimNo);
	}

	public List<NewsListImageVO> getAll() {
		return dao.getAll();
	}

}

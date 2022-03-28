package com.newsList.model;
import java.util.List;

public class NewsListService {
private NewsListDAO_interface dao;
	
	public NewsListService() {
		dao = new NewsListJNDIDAO();
	}
	
	public NewsListVO insertNewsList(String title, String content, String status, String newsComeFrom) {
		NewsListVO newsListVO = new NewsListVO();
		newsListVO.setTitle(title);
		newsListVO.setContent(content);
		newsListVO.setStatus(status);
		newsListVO.setNewsComeFrom(newsComeFrom);
		newsListVO = dao.insert(newsListVO);
		
		return newsListVO;
	}

	public NewsListVO updateNewsList(Integer newsId,String title, String content, String status, String newsComeFrom ) {
		NewsListVO newsListVO = new NewsListVO();
		
		newsListVO.setNewsId(newsId);
		newsListVO.setTitle(title);
		newsListVO.setContent(content);
		newsListVO.setStatus(status);
		newsListVO.setNewsComeFrom(newsComeFrom);
		
		dao.update(newsListVO);
		
		return newsListVO;
	}

	public void deleteNewsList(Integer newsId) {
		dao.delete(newsId);
	}

	public NewsListVO getOneNewsList(Integer newsId) {
		return dao.findByPK(newsId);
	}

	public NewsListVO getOneNewsListBytitle(String title) {
		return dao.findByTITLE(title);
	}

	public List<NewsListVO> getAll() {
		return dao.getAll();
	}
	
	

}

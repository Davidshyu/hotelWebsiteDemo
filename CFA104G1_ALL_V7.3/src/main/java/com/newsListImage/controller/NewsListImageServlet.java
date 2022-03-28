package com.newsListImage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newsListImage.model.NewsListImageService;
import com.newsListImage.model.NewsListImageVO;


@javax.servlet.annotation.MultipartConfig
public class NewsListImageServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("showImages".equals(action)) {  // 來自addproduct.jsp的請求
			Integer newsID = new Integer(req.getParameter("newsID"));
			
			NewsListImageService newsListImageSvc = new NewsListImageService();
			List<NewsListImageVO> newsListImageVO = newsListImageSvc.getAll();
			
			List<NewsListImageVO> Images = newsListImageVO.stream()
																.filter(i -> i.getNewsID().intValue() ==  newsID.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			req.setAttribute("newsID", newsID);  
			
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsListImg/showImages.jsp");	////////////////////////////////
			successView.forward(req, res);
			return;
		}
		
		if("delete".equals(action)) {
			Integer nimNo = new Integer(req.getParameter("nimNo"));
			Integer newsID = new Integer(req.getParameter("newsID"));

			System.out.println("nimNo : " + nimNo);
			
			/****1.刪除圖片****/
			NewsListImageService newsListImageSvc = new NewsListImageService();
			newsListImageSvc.deleteNewsListImage(nimNo);
			
			/****2.刪除圖片後重新查詢，並將結果存入req****/
			List<NewsListImageVO> newsListImageVO = newsListImageSvc.getAll();
			List<NewsListImageVO> Images = newsListImageVO.stream()
																.filter(i -> i.getNewsID().intValue() ==  newsID.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			req.setAttribute("newsID", newsID);   //先將pi_no 存起來，避商品沒有圖片時，無法新增
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsListImg/showImages.jsp");	//////////////////////////////////
			successView.forward(req, res);
			return;
			
		}
		
		if("insert".equals(action)) {
			System.out.println("enter insert");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer newsID = new Integer(req.getParameter("newsID"));
			
			req.setAttribute("newsID", newsID);  //先將pi_no 存起來，避商品沒有圖片時，無法新增


			NewsListImageService newsListImageSvc = new NewsListImageService();

			/****1.新增圖片****/ 
			Collection<Part> parts = req.getParts();

			String filename = null;
			for(Part part : parts) {
				InputStream in = part.getInputStream();				
				

				if(getFileNameFromPart(part)!=null && part.getContentType()!=null) {	
					filename = getFileNameFromPart(part);
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					newsListImageSvc.insertNewsListImage(newsID, buf);
				}
			}
			
			if(filename == null) {
				errorMsgs.add("請選擇檔案!!");
			}
			
			/****2.新增圖片後重新查詢，並將結果存入req****/
			List<NewsListImageVO> newsListImageVO = newsListImageSvc.getAll();
			List<NewsListImageVO> Images = newsListImageVO.stream()
																.filter(i -> i.getNewsID().intValue() == newsID.intValue())
																.collect(Collectors.toList());
			req.setAttribute("Images", Images);
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/newsListImg/showImages.jsp");	////////////////////
				failView.forward(req, res);
				return;
			}
			
			/****4.������V****/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsListImg/showImages.jsp");	/////////////////////
			successView.forward(req, res);
			return;
		}
		
		if("ExportImages".equals(action)) {
			res.setContentType("image/jpeg");
			Integer nimNo= new Integer(req.getParameter("nimNo"));
			NewsListImageService newsListImageSvc = new NewsListImageService();
			NewsListImageVO newsListImageVO = newsListImageSvc.getOneNewsListImage(nimNo);
			byte[] imgArry = newsListImageVO.getNimImg();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();
			return;
		}
		
		if("showShopImage".equals(action)) {
			res.setContentType("image/jpeg");
			
			/****1.�����Ѽ�****/
			Integer newsID =new Integer(req.getParameter("newsID"));
			
			/****2.�d�߷Ӥ�****/
			NewsListImageService newsListImageSvc = new NewsListImageService();
			List<NewsListImageVO> imageList = newsListImageSvc.getAll();
			
			/****3.�L�o�Ӥ�****/
			Optional<NewsListImageVO> firstImages = null;
			
			firstImages = imageList.stream()
								   .filter(i -> i.getNewsID().intValue() == newsID.intValue())
								   .findFirst();
			
			/****4.��PK���o�Ϥ�****/
			NewsListImageVO OutImages = newsListImageSvc.getOneNewsListImage(firstImages.get().getNimNo());
			
			/****5.��X�Ϥ�****/
			byte[] imgArry = OutImages.getNimImg();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();
			return;
		}
		
	}
	
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}

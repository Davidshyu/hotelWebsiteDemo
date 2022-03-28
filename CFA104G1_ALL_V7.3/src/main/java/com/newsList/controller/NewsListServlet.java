package com.newsList.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.newsList.model.NewsListService;
import com.newsList.model.NewsListVO;
import com.newsListImage.model.NewsListImageService;
import com.newsListImage.model.NewsListImageVO;

import oracle.net.aso.i;
import oracle.net.aso.k;
import oracle.net.aso.l;


@javax.servlet.annotation.MultipartConfig
public class NewsListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {	
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("Action : " + action);

//OK		
if("insert".equals(action)) {  // 來自addNewsList.jsp的請求   //2個jsp： addNewsList.jsp & listAllNewsList.jsp
	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String title = req.getParameter("title");
				if(title == null || title.trim().length()==0) {
					errorMsgs.add("*請輸入新聞標題");
				}
				
				String content = req.getParameter("content");
				if(content == null || content.trim().length()==0) {
					errorMsgs.add("*請輸入新聞內容");
				}
				
				//此欄位無須檢查
				String status = req.getParameter("status");
				
				String newsComeFrom = req.getParameter("newsComeFrom");
				if(newsComeFrom == null || newsComeFrom.trim().length()==0) {
					errorMsgs.add("*請輸入報導出處");
				}
				
				NewsListVO newsListVO1 = new NewsListVO();
				newsListVO1.setTitle(title);
				newsListVO1.setContent(content);
				newsListVO1.setStatus(status);
				newsListVO1.setNewsComeFrom(newsComeFrom);

				
				// Send the use back to the form, if there were errors
				req.setAttribute("newsListVO1", newsListVO1); // 含有輸入格式錯誤的newsListVO物件,也存入req
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/newsList/addNewsList.jsp");
					failView.forward(req, res);
					return;
				}
			
				/***************************2.開始新增商品***************************************/
				NewsListService newsListSvc = new NewsListService();
				NewsListVO newsListVO = newsListSvc.insertNewsList(title,content,status,newsComeFrom);
				System.out.println("新增報導成功!!!");
				

				/***************************3.新增商品照片***************************************/
				Collection<Part> parts = req.getParts();
				System.out.println("total parts : " + parts.size());

				for(Part part : parts) {
					InputStream in = part.getInputStream();
					if(in.available()!=0 && part.getContentType()!=null) {
						NewsListImageService newsListImageSvc = new NewsListImageService();
						byte[] img = new byte[in.available()];
						in.read(img);
						in.close();
						newsListImageSvc.insertNewsListImage(newsListVO.getNewsId(), img);
					}
				}

				/***************************4.新增完成，頁面轉向***************************************/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsList/listAllNewsList.jsp");
				successView.forward(req, res);
				return;
				
			} catch (Exception e) {
				/****5.新增失敗****/
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/newsList/addNewsList.jsp");
					failView.forward(req, res);
					return;
				}
			}
			
		}
		
if("getOneForDiplay".equals(action)){  //jsp：listOneproduct
			System.out.println("Enter getOneForDiplay!!!"); 
			/****1.取值****/
			Integer newsId = new Integer(req.getParameter("newsId"));

			/****2.從資料庫取出****/
			NewsListService newsListSvc = new NewsListService();
			NewsListVO newsListVO1 = newsListSvc.getOneNewsList(newsId);
			
			/***取出圖片***/
			NewsListImageService newsListImageSvc = new NewsListImageService();
			List<NewsListImageVO> imagesList = newsListImageSvc.getAll();
			
			List<NewsListImageVO> filterImagesList= imagesList.stream()
																 .filter(i -> i.getNewsID().intValue() == newsId.intValue())
																 .collect(Collectors.toList());
			
			System.out.println("filterImagesList:" + filterImagesList.isEmpty());
			
			req.setAttribute("filterImagesList", filterImagesList);
			
			req.setAttribute("newsListVO", newsListVO1);
			
			/****3.設定燈箱顯示****/
//			boolean openLightBox = true;
//			req.setAttribute("openLightBox", openLightBox);
//			
//			System.out.println("openLightBox : " + openLightBox);

			/****4.頁面轉向 ****/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsList/listOneNewsList.jsp");
			successView.forward(req, res);
			return;
			
		}
		
if("getOneForUpdate".equals(action)) {  // jsp:updateproduct
			
			System.out.println("enter getOneForUpdate!!");
			/****1.接收請求參數****/
			Integer newsId = new Integer(req.getParameter("newsId"));
			/****2.取得資料****/
			NewsListService newsListSvc = new NewsListService();
			NewsListVO newsListVO1  = newsListSvc.getOneNewsList(newsId);
			req.setAttribute("newsListVO", newsListVO1);
			/****3.轉向至update頁面****/
			System.out.println("頁面轉向!!!");
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsList/updateNewsList.jsp");
			successView.forward(req, res);
			return;
			
		}
		
if("update".equals(action)) { //jsp:updateNewsList listAllupdateNewsList updateupdateNewsList

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/****1.檢查輸入變數******/
			try {
				String title = req.getParameter("title");
				if(title == null || title.trim().length()==0) {
					errorMsgs.add("*請輸入新聞標題");
				}
				
				
				Integer newsId = new Integer(req.getParameter("newsId"));
				
				
				String content = req.getParameter("content");
				if(content == null || content.trim().length()==0) {
					errorMsgs.add("*請輸入新聞內容");
				}
				
				//此欄位無須檢查
				String status = req.getParameter("status");
				
				String newsComeFrom = req.getParameter("newsComeFrom");
				if(newsComeFrom == null || newsComeFrom.trim().length()==0) {
					errorMsgs.add("*請輸入報導出處");
				}

				
				NewsListVO newsListVO1 = new NewsListVO();
				
				newsListVO1.setNewsId(newsId);
				newsListVO1.setTitle(title);
				newsListVO1.setContent(content);
				newsListVO1.setStatus(status);
				newsListVO1.setNewsComeFrom(newsComeFrom);
				
				req.setAttribute("newsListVO1", newsListVO1);
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/newsList/updateNewsList.jsp");
					failView.forward(req, res);
					return;
				}
				
				/****2.開始新增商品****/
				NewsListService newsListSvc = new NewsListService();
				newsListSvc.updateNewsList(newsId, title, content, status, newsComeFrom);
				System.out.println("修改報導成功!!!");
				
				/****3.新增完成，頁面轉向***/
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/newsList/listAllNewsList.jsp");
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("更新失敗!!!");
				/****4.更新失敗****/
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/newsList/updateNewsList.jsp");
					failView.forward(req, res);
					return;
				}
			}
		}
		
if("FindByPK".equals(action)) {		//jsp:select_page afterSearch
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			try {
				
			/****1.檢查輸入參數****/
			Integer newsId = new Integer(req.getParameter("newsId"));
			
			
			/****2.查詢資料****/
			NewsListService newsListSvc = new NewsListService();
			NewsListVO newsListVO1 = newsListSvc.getOneNewsList(newsId);
			
			if(newsListVO1==null) {
				errorMsgs.add("查無報導");	
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/newsList/NewsList_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			//配合前台頁面EL取值，將VO存入List中
			List<NewsListVO> newsListVOs = new LinkedList<NewsListVO>();
			newsListVOs.add(newsListVO1);
			req.setAttribute("newsListVOs", newsListVOs);	
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
								req.getRequestDispatcher("/back-end/newsList/afterSearch.jsp");
			successView.forward(req, res);
			return;
			
			}catch (NumberFormatException e) {
				errorMsgs.add("請輸入數字!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/newsList/NewsList_select_page.jsp");
				failView.forward(req, res);
				return;
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/newsList/NewsList_select_page.jsp");
				failView.forward(req, res);
				return;
			}	
		}
		
if("ExportImages".equals(action)) {
			res.setContentType("image/jpeg");
			Integer nimNo= new Integer(req.getParameter("nimNo"));
			System.out.println("Integer >> nimNo : " + nimNo);
			NewsListImageService newsListImageSvc = new NewsListImageService();
			NewsListImageVO newsListImageVO1 = newsListImageSvc.getOneNewsListImage(nimNo);
			byte[] imgArry = newsListImageVO1.getNimImg();
			ServletOutputStream out = res.getOutputStream();
			out.write(imgArry);
			out.close();	
			return;
		}
		

if("showAllNews".equals(action)) { //jsp:productShopPage
	HttpSession session = req.getSession();
	Integer newsId = (Integer) session.getAttribute("newsId");
				
	/****1.取得變數****/

	session.setAttribute("newsId", newsId);

	/****2.取得列表****/
	List<NewsListVO> newsListVO_after = searchNewsList(newsId);
			
	req.setAttribute("newsListVO_after", newsListVO_after);
				
	/****3.頁面轉向****/
	RequestDispatcher successView = 
			req.getRequestDispatcher("/front-end/newsList/NewsListFrontPage.jsp");
	successView.forward(req, res);
	return;
}

if("showNewsDetail".equals(action)) {			
	/****1.取得參數****/
	Integer newsId = new Integer(req.getParameter("newsId"));
	
	/****2.開始查詢商品****/
	NewsListService newsListSvc = new NewsListService();
	NewsListVO newsListVO = newsListSvc.getOneNewsList(newsId);
	
	req.setAttribute("newsListVO", newsListVO);
	
	/****3.開始查詢商品圖片****/
	NewsListImageService newsListImageSvc = new NewsListImageService();
	List<NewsListImageVO> newsListImageVOs = newsListImageSvc.getAll();
	
	List<NewsListImageVO> afterFilterImages = null;
	afterFilterImages = newsListImageVOs.stream()
					 					   .filter(i -> i.getNewsID().equals(newsId))
					 					   .collect(Collectors.toList());
	req.setAttribute("afterFilterImages", afterFilterImages);
	
	/****4.頁面轉向****/
	RequestDispatcher successView = 
			 req.getRequestDispatcher("/front-end/NewsList/NewsListdetail.jsp");
	successView.forward(req, res);
	return;
}		
		
		
/*購物車>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>購物車*/
//if("cart".equals(action)) {
//
//			HttpSession session = req.getSession();
//			Vector<ProductVO> productList = (Vector<ProductVO>) session.getAttribute("shoppingCart");
//			String cart_action = req.getParameter("cart_action");
//			/*使用HashMap儲存商品數量 (key:pi_no商品編號, value: quantity購買數量)*/
//			Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap");
//			String forwardStr = null;
//			
//if(!cart_action.equals("checkout")) {
//				
//				//刪除購物車商品
//if(cart_action.equals("delete")) { //jsp:cart
//					String delete = req.getParameter("index");
//					int del = Integer.parseInt(delete);
//					productList.removeElementAt(del);
//					//刪除完商品回到購物車頁面
//					forwardStr = "/front-end/Product/cart.jsp";
//				}
//				//刪除購物車中所有商品
//				else if(cart_action.equals("deleteAll")) {
//					Quamap.clear();
//					productList.removeAllElements();
//					//刪除完商品回到購物車頁面
//					forwardStr = "/front-end/Product/cart.jsp";
//				}
//				
//				//新增商品到購物車中
//				else if(cart_action.equals("add")) {
//					Integer pi_no = new Integer(req.getParameter("pi_no"));
//					Integer quantity = new Integer(req.getParameter("quantity"));
//					boolean match = false;
//					
//					/*從新將商品查詢出來並存入req，forward回原本商品頁面才有資料 >>>>*/
//					/****開始查詢商品****/
//					ProductService productSvc = new ProductService();
//					ProductVO productVO = productSvc.getOneProductInform(pi_no);
//					req.setAttribute("productVO", productVO);
//										
//					/****開始查詢商品圖片****/
//					NewsListImageService productImageSvc = new NewsListImageService();
//					List<productImageVO> productImageVOs = productImageSvc.getAll();
//					
//					List<productImageVO> afterFilterImages = null;
//					afterFilterImages = productImageVOs.stream()
//									 					   .filter(i -> i.getPi_no().equals(pi_no))
//									 					   .collect(Collectors.toList());
//					req.setAttribute("afterFilterImages", afterFilterImages);
//					/*<<<<從新將商品查詢出來並存入req，forward回原本商品頁面才有資料 */
//					
//					
//					//新增第一個商品到購物車
//					if(productList==null) {
//						productList = new Vector<ProductVO>();
//						productList.add(productVO);
//						Quamap = new HashMap<Integer, Integer>();
//						Quamap.put(pi_no, quantity);
//					}else {
//						//檢查是否為重複的商品
//						for(int i=0 ; i<productList.size() ; i++ ) {
//							ProductVO productVO2 = productList.get(i);
//							if(productVO2.getPiNo().equals(productVO.getPiNo())) {
//								/*直接將原本的數量覆蓋掉*/
//								Quamap.put(pi_no, quantity);
//								match = true;
//							}
//						}
//						
//						//如果不是重複商品則新增
//						if(!match) {
//							productList.add(productVO);
//							Quamap.put(pi_no, quantity);
//						}
//					}
//					//新增完商品回到商品頁面
//					forwardStr = "/front-end/Product/productDetail.jsp";
//				}
//				
//				/*計算購物車內商品價格*/
//				int subTotal = 0;
//				for (ProductVO product : productList) {
//					subTotal += product.getPiPri() * Quamap.get(product.getPiNo());
//				}
//				Quamap.put(999, subTotal);
//				
//				/*計算購物車內商品數量*/
//				Integer amount = (int) productList.size();
//				Quamap.put(998, amount);
//
//				session.setAttribute("Quamap", Quamap);
//				session.setAttribute("shoppingCart", productList);
//				RequestDispatcher successVisw = 
//						req.getRequestDispatcher(forwardStr);
//				successVisw.forward(req, res);
//				return;
//			}
//		}
		/*購物車<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<購物車*/
		
//		//依類別編號查詢
//		if("findBySpcNo".equals(action)) {
//			
//			/****1.取得參數****/
//			Integer spc_no = new Integer(req.getParameter("spc_no"));
//			
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpc_no().equals(spc_no))
//																	 .collect(Collectors.toList());
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back-end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//			
//		}
//		
//		//依商品名稱查詢
//		if("findBySpiName".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/****1.取得參數****/
//			String spi_name = req.getParameter("spi_name");
//
//			if(spi_name.trim().length()==0) {
//				errorMsgs.add("請輸入商品名稱!!");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back-end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpi_name()   /*不區分大小寫*/
//																			 	   .toLowerCase()
//																			 	   .contains(spi_name.toLowerCase()))
//																	 .collect(Collectors.toList());
//			if(productInformVOs.size()==0) {
//				errorMsgs.add("查無商品");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back-end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back-end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//		}
//		
//		//依商品狀態查詢
//		if("findBySpiSta".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/****1.取得參數****/
//			String spi_sta = req.getParameter("spi_sta");
//						
//			/****2.查詢商品****/
//			ProductInformService productInformSvc = new ProductInformService();
//			List<ProductInformVO> productInformVOs = productInformSvc.getAll()
//																	 .stream()
//																	 .filter(p -> p.getSpi_sta().equals(spi_sta))
//																	 .collect(Collectors.toList());
//			if(productInformVOs.size()==0) {
//				errorMsgs.add("查無商品");
//				
//				RequestDispatcher failView = 
//						req.getRequestDispatcher("/back-end/secProductInfo/select_page.jsp");
//				failView.forward(req, res);
//				return;
//			}
//			
//			req.setAttribute("productInfoVOs", productInformVOs);
//			
//			/****3.頁面轉向****/
//			RequestDispatcher successView = 
//					req.getRequestDispatcher("/back-end/secProductInfo/afterSeach.jsp");
//			successView.forward(req, res);
//			return;
//		}
		
		//複合查詢
if("compoundQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);		
			
			/****1.取得參數****/
			String newsId_str = req.getParameter("newsId");
			Integer newsId = null;
			if(newsId_str.trim().length() != 0) {
				try {
					newsId = new Integer(req.getParameter("newsId"));
				} catch (NumberFormatException e) {
					errorMsgs.add("報導編號請輸入數字");
					
					RequestDispatcher failView = 
							req.getRequestDispatcher("/back-end/newsList/NewsList_select_page.jsp");
					failView.forward(req, res);
					return;
				}
			}

			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String status = req.getParameter("status");
			
			Map<String, String> condititon = new HashMap<String, String>();
			condititon.put("newsId_str", newsId_str);
			condititon.put("title", title);
			condititon.put("content", content);
			condititon.put("status", status);
			
			/****2.開始查詢****/
			NewsListService newsListSvc = new NewsListService();
			List<NewsListVO> newsListVOs = newsListSvc.getAll();
			Set<String> key = condititon.keySet();
			
			key.stream()
			   .forEach(k -> compoundQuery(newsListVOs, k, condititon.get(k)));
			
if(newsListVOs.isEmpty()) {
				errorMsgs.add("查無報導!!");
				RequestDispatcher failView = 
						req.getRequestDispatcher("/back-end/newsList/NewsList_select_page.jsp");
				failView.forward(req, res);
				return;
			}
			
			req.setAttribute("newsListVOs", newsListVOs);
			
			/****3.頁面轉向****/
			RequestDispatcher successView = 
					req.getRequestDispatcher("/back-end/newsList/afterSearch.jsp");
			successView.forward(req, res);
			return;
		}
	}
	
	/*此方法根據輸入的商品類別編號搜尋並過濾，回傳過濾後的商品列表*/
	private List<NewsListVO> searchNewsList(Integer newsId) {
		List<NewsListVO> afterFiterNewsList = null;
				
		/****1.開始查詢****/
		NewsListService newsListSvc = new NewsListService();
		List<NewsListVO> newsListVOs = newsListSvc.getAll();
				
		/****2.過濾商品****/
		if(newsId == 0) {
			/****pc_no=0 表示要查詢所有的商品，並去除狀態為"下架"或庫存為0的商品****/
			afterFiterNewsList = newsListVOs.stream()
//												.filter(i -> i.getPiStock().intValue() > 0)
//												.filter(i -> i.getStatus().equals("上架中"))
												.collect(Collectors.toList());
		}else {
			/****依據pc_no，開始過濾商品，並去除狀態為"下架"或庫存為0的商品****/
			afterFiterNewsList = newsListVOs.stream()
//												.filter(i -> i.getPcNo().equals(pc_no))
//												.filter(i -> i.getPiStock().intValue() > 0)
//												.filter(i -> i.getStatus().equals("上架中"))
												.collect(Collectors.toList());	
		}
		return afterFiterNewsList;
	}
	
	//此方法根據輸入的key和value過濾newsListVOs內的VO
	private void compoundQuery (List<NewsListVO> newsListVOs, String key ,String value) {
		List<NewsListVO> newsListVO_after = null;
		
		if(value.trim().length()!=0) {
			switch (key) {
			case "newsId_str":
				newsListVO_after = newsListVOs.stream()
														.filter(i -> i.getNewsId().equals(new Integer(value)))
														.collect(Collectors.toList());
				newsListVOs.clear();
				newsListVOs.addAll(newsListVO_after);
				break;
			
			
			
			case "title":
				newsListVO_after = newsListVOs.stream()
														.filter(i -> i.getTitle()	/*不區分大小寫*/
																	  .toLowerCase()
																	  .contains(value.toLowerCase()))
														.collect(Collectors.toList());
				newsListVOs.clear();
				newsListVOs.addAll(newsListVO_after);
				break;
			case "content":
				newsListVO_after = newsListVOs.stream()
														.filter(i -> i.getContent()	/*不區分大小寫*/
																	  .toLowerCase()
																	  .contains(value.toLowerCase()))
														.collect(Collectors.toList());
				newsListVOs.clear();
				newsListVOs.addAll(newsListVO_after);
				break;
			case "status":
				newsListVO_after = newsListVOs.stream()
														.filter(i -> i.getStatus().equals(value))
														.collect(Collectors.toList());
				newsListVOs.clear();
				newsListVOs.addAll(newsListVO_after);
				break;
			
			default:
				break;
			}
		}
	}
}

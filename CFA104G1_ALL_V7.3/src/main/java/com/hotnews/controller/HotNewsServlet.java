package com.hotnews.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hotnews.model.HotNewsService;
import com.hotnews.model.HotNewsVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024 * 1024)
public class HotNewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("HotNewsId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入最新消息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer hot_news_id = null;
				try {
					hot_news_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("最新消息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsVO HotNewsVO = HotNewsSvc.getOneHotNews(hot_news_id);
				if (HotNewsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("HotNewsVO", HotNewsVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/hotnews/listOneHotNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer hot_news_id = new Integer(req.getParameter("HotNewsId"));

				/*************************** 2.開始查詢資料 ****************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsVO HotNewsVO = HotNewsSvc.getOneHotNews(hot_news_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("HotNewsVO", HotNewsVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/hotnews/updateHotNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/listAllHotNews.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getPhoto_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer hot_news_id = new Integer(req.getParameter("HotNewsId"));

				/*************************** 2.開始查詢資料 ****************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsVO HotNewsVO = HotNewsSvc.getOneHotNews(hot_news_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("HotNewsVO", HotNewsVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/hotnews/updateHotNewsPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/listAllHotNews.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer hot_news_id = null;
				try {
					hot_news_id = new Integer(req.getParameter("hot_news_id").trim());
				} catch (NumberFormatException e) {
					hot_news_id = 0;
					errorMsgs.add("最新消息編號請填數字.");
				}

				String hot_news_title = req.getParameter("hot_news_title");
				String hot_news_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (hot_news_title == null || hot_news_title.trim().length() == 0) {
					errorMsgs.add("最新消息標題: 請勿空白");
				} else if (!hot_news_title.trim().matches(hot_news_titleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("最新消息標題: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
				}

				String hot_news_description = req.getParameter("hot_news_description");
				//String hot_news_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,1000}$";
				if (hot_news_description == null || hot_news_description.trim().length() == 0) {
					errorMsgs.add("最新消息內容: 請勿空白");
				} 
								
				java.sql.Date hot_news_date = null;
				try {
					hot_news_date = java.sql.Date.valueOf(req.getParameter("hot_news_date").trim());
				} catch (Exception e) {
					hot_news_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式");
				}

				Boolean hot_news_status = null;
				try {
					hot_news_status = new Boolean(req.getParameter("hot_news_status").trim());
				} catch (NumberFormatException e) {
					hot_news_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}
				
				HotNewsVO HotNewsVO = new HotNewsVO();
				HotNewsVO.setHot_news_id(hot_news_id);
				HotNewsVO.setHot_news_title(hot_news_title);
				HotNewsVO.setHot_news_description(hot_news_description);
				HotNewsVO.setHot_news_date(hot_news_date);
				HotNewsVO.setHot_news_status(hot_news_status);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("HotNewsVO", HotNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/updateHotNews.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsVO = HotNewsSvc.updateHotNews(hot_news_id, hot_news_title, hot_news_description, hot_news_date,
						hot_news_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("HotNewsVO", HotNewsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/hotnews/listOneHotNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/updateHotNews.jsp");
				failureView.forward(req, res);
			}

		}
		if ("updatephoto".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			List<String> okMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer hot_news_id = null;
				try {
					hot_news_id = new Integer(req.getParameter("hot_news_id").trim());
				} catch (NumberFormatException e) {
					hot_news_id = 0;
					errorMsgs.add("最新消息編號請填數字.");
				}
				
				String hot_news_title = req.getParameter("hot_news_title");

				byte[] hot_news_photo = null;
				try {
					Part part = req.getPart("hot_news_photo");
					InputStream in = part.getInputStream();
					hot_news_photo = new byte[in.available()];
					in.read(hot_news_photo);
					in.close();
				} catch (Exception e) {
					hot_news_photo = null;
				}

				HotNewsVO HotNewsVO = new HotNewsVO();
				HotNewsVO.setHot_news_id(hot_news_id);
				HotNewsVO.setHot_news_title(hot_news_title);
				HotNewsVO.setHot_news_photo(hot_news_photo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("HotNewsVO", HotNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/updateHotNewsPhoto.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsVO = HotNewsSvc.updateHotNewsPhoto(hot_news_id, hot_news_title, hot_news_photo);

				okMsgs.add("最新消息圖片更新成功.");
				req.setAttribute("okMsgs", okMsgs);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("HotNewsVO", HotNewsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/hotnews/updateHotNewsPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/updateHotNewsPhoto.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String hot_news_title = req.getParameter("hot_news_title");
				String hot_news_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (hot_news_title == null || hot_news_title.trim().length() == 0) {
					errorMsgs.add("最新消息名稱: 請勿空白");
				} else if (!hot_news_title.trim().matches(hot_news_titleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("最新消息名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
				}

				String hot_news_description = req.getParameter("hot_news_description");
				if (hot_news_description == null || hot_news_description.trim().length() == 0) {
					errorMsgs.add("最新消息內容: 請勿空白");
				} 

				java.sql.Date hot_news_date = null;
				try {
					hot_news_date = java.sql.Date.valueOf(req.getParameter("hot_news_date").trim());
				} catch (Exception e) {
					hot_news_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式");
				}

				Boolean hot_news_status = null;
				try {
					hot_news_status = new Boolean(req.getParameter("hot_news_status").trim());
				} catch (NumberFormatException e) {
					hot_news_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}
				
				byte[] hot_news_photo = null;
				try {
					Part part = req.getPart("hot_news_photo");
					InputStream in = part.getInputStream();
					hot_news_photo = new byte[in.available()];
					in.read(hot_news_photo);
					in.close();
				} catch (Exception e) {
					hot_news_photo = null;
				}

				HotNewsVO HotNewsVO = new HotNewsVO();
				//HotNewsVO.setHot_news_id(hot_news_id);
				HotNewsVO.setHot_news_title(hot_news_title);
				HotNewsVO.setHot_news_description(hot_news_description);
				HotNewsVO.setHot_news_date(hot_news_date);
				HotNewsVO.setHot_news_status(hot_news_status);
				HotNewsVO.setHot_news_photo(hot_news_photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("HotNewsVO", HotNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/addHotNews.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsVO = HotNewsSvc.addHotNews(hot_news_title, hot_news_description, hot_news_date,
						hot_news_status, hot_news_photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/hotnews/listAllHotNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/addHotNews.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer hot_news_id = new Integer(req.getParameter("HotNewsId"));

				/*************************** 2.開始刪除資料 ***************************************/
				HotNewsService HotNewsSvc = new HotNewsService();
				HotNewsSvc.deleteHotNews(hot_news_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/hotnews/listAllHotNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotnews/listAllHotNews.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

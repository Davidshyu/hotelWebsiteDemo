package com.activityCategory.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activityCategory.model.ActivityCategoryService;
import com.activityCategory.model.ActivityCategoryVO;

@WebServlet("/activityCategory.do")
public class ActivityCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		// 判斷action=insert
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				String activity_category_name = request.getParameter("activity_category_name");
				if(activity_category_name == null || activity_category_name.trim().length() == 0) {
					errorMsgs.add("活動類別名稱: 請勿空白");
				}
								
				String activity_category_info = request.getParameter("activity_category_info");
				if (activity_category_info == null || activity_category_info.trim().length() == 0) {
					errorMsgs.add("活動類別資訊: 請勿空白");					
				}		
				//存入vo
				ActivityCategoryVO activityCategoryVO = new ActivityCategoryVO();
				activityCategoryVO.setActivity_category_name(activity_category_name);
				activityCategoryVO.setActivity_category_info(activity_category_info);
				
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityCategoryVO", activityCategoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activityCategory/addActivityCategory.jsp");
					failureView.forward(request, response);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActivityCategoryService activityCategoryService= new ActivityCategoryService();
				activityCategoryVO = activityCategoryService.insertActivityCategory(activity_category_name, activity_category_info);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityCategory/listAllActivityCategory.jsp"); // 新增成功後轉交listAllEmp.jsp
//				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityCategory/success.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);	
				/***************************其他可能的錯誤處理**********************************/
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activityCategory/addActivityCategory.jsp");
				failureView.forward(request, response);
			}	
		
		}
		if("update".equals(action)) { // 來自updateActicityCategory.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer activity_category_id = Integer.valueOf(request.getParameter("activity_category_id"));
				
				String activity_category_name = request.getParameter("activity_category_name");
				if(activity_category_name == null || activity_category_name.trim().length() == 0) {
					errorMsgs.add("活動類別名稱: 請勿空白");
				}
				
				String activity_category_info = request.getParameter("activity_category_info");
				if (activity_category_info == null || activity_category_info.trim().length() == 0) {
					errorMsgs.add("活動類別資訊: 請勿空白");	
				}
				//存入vo
				ActivityCategoryVO activityCategoryVO = new ActivityCategoryVO();
				activityCategoryVO.setActivity_category_id(activity_category_id);
				activityCategoryVO.setActivity_category_name(activity_category_name);
				activityCategoryVO.setActivity_category_info(activity_category_info);
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityCategoryVO", activityCategoryVO); // 含有輸入格式錯誤的activityCategoryVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activityCategory/updateActivityCategory.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				ActivityCategoryService activityCategoryService= new ActivityCategoryService();
				activityCategoryVO = activityCategoryService.updateActivityCategory(activity_category_id,activity_category_name, activity_category_info);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activityCategoryVO", activityCategoryVO); // 資料庫update成功後,正確的的activityCategoryVO物件,存入req
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityCategory/listAllActivityCategory.jsp"); // 修改成功後,轉交listOneActivityCategory.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityCategory/updateActivityCategory.jsp");
				failureView.forward(request, response);
			
			}
		}
		if ("getOne_For_Update".equals(action)) {// 來自listAllActivityCategory.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				Integer activity_category_id = Integer.valueOf(request.getParameter("activity_category_id"));
				/***************************2.開始查詢資料****************************************/
				ActivityCategoryService activityCategoryService = new ActivityCategoryService();
				ActivityCategoryVO activityCategoryVO = activityCategoryService.getOneActivityCategory(activity_category_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("activityCategoryVO", activityCategoryVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityCategory/updateActivityCategory.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityCategory/listAllActivityCategory.jsp");
				failureView.forward(request, response);
			}
		}
		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("activity_category_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activityCategory/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				Integer activity_category_id = null;
				try {
					activity_category_id = Integer.valueOf(str);
					
				} catch (Exception e) {
					errorMsgs.add("活動類別編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activityCategory/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ActivityCategoryService activityCategoryService =new ActivityCategoryService();
				ActivityCategoryVO activityCategoryVO =activityCategoryService.getOneActivityCategory(activity_category_id);
				if (activityCategoryVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activityCategory/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activityCategoryVO", activityCategoryVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityCategory/listOneActivityCategory.jsp");
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityCategory/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		if ("delete".equals(action)) { // 來自listAllActivityCategory.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數***************************************/
				Integer activity_category_id = Integer.valueOf(request.getParameter("activity_category_id"));
				
				/***************************2.開始刪除資料***************************************/
				ActivityCategoryService activityCategoryService =new ActivityCategoryService();
				activityCategoryService.deleteActivityCategory(activity_category_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityCategory/listAllActivityCategory.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityCategory/listAllActivityCategory.jsp");
				failureView.forward(request, response);
			}
		}
	}
}

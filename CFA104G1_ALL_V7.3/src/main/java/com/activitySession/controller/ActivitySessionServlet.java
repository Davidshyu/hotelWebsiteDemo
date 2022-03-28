package com.activitySession.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activitySession.model.ActivitySessionService;
import com.activitySession.model.ActivitySessionVO;
@WebServlet("/activitySession.do")
public class ActivitySessionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//判斷action=insert
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer activity_id = Integer.valueOf(request.getParameter("activity_id"));
				
				String activity_session_name = request.getParameter("activity_session_name");
				if(activity_session_name == null || activity_session_name.trim().length() == 0) {
					errorMsgs.add("活動場次名稱: 請勿空白");
				}
				
				java.sql.Date entered_started = null;
				try {
					entered_started = java.sql.Date.valueOf(request.getParameter("entered_started").trim());
				} catch (IllegalArgumentException e) {
					entered_started=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始日期!");
				}
				
				java.sql.Date entered_end = null;
				try {
					entered_end = java.sql.Date.valueOf(request.getParameter("entered_end").trim());
				} catch (IllegalArgumentException e) {
					entered_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名截止日期!");
				}
				
				java.sql.Date activity_started = null;
				try {
					activity_started = java.sql.Date.valueOf(request.getParameter("activity_started").trim());
				} catch (IllegalArgumentException e) {
					activity_started=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期起!");
				}
				
				java.sql.Date activity_end = null;
				try {
					activity_end = java.sql.Date.valueOf(request.getParameter("activity_end").trim());
				} catch (IllegalArgumentException e) {
					activity_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期迄!");
				}
				
				Integer activity_price  = null ;
				try {
					activity_price = Integer.valueOf(request.getParameter("activity_price").trim());
				} catch (NumberFormatException e) {
					activity_price = 0;
					errorMsgs.add("活動定價請填數字.");
				}

//				Integer activity_state = Integer.valueOf(request.getParameter("activity_state"));
//				if(activity_state == null ) {
//					errorMsgs.add("活動狀態: 請勿空白");
//				}
				
				Integer activity_state  = null ;
				try {
					activity_state = Integer.valueOf(request.getParameter("activity_state").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("活動狀態: 請勿空白");
				}
				
				String status_note = String.valueOf(request.getParameter("status_note"));

				Integer activity_max_part  = null ;
				try {
					activity_max_part = Integer.valueOf(request.getParameter("activity_max_part").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("活動人數上限: 請勿空白");
				}
//				Integer activity_min_part = Integer.valueOf(request.getParameter("activity_min_part"));
//				if(activity_min_part == null ) {
//					errorMsgs.add("活動人數下限: 請勿空白");
//				}
				
				Integer activity_min_part  = null ;
				try {
					activity_min_part = Integer.valueOf(request.getParameter("activity_min_part").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("活動人數下限: 請勿空白");
				}
				
				Integer entered_total = Integer.valueOf(request.getParameter("entered_total"));
				
//				Integer product_status = Integer.valueOf(request.getParameter("product_status"));
//				if(product_status == null ) {
//					errorMsgs.add("商品狀態: 請勿空白");
//				}
				
				Integer product_status  = null ;
				try {
					product_status = Integer.valueOf(request.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品狀態: 請勿空白");
				}
				
				//存入vo
				ActivitySessionVO activitySessionVO = new ActivitySessionVO();
				activitySessionVO.setActivity_id(activity_id);
				activitySessionVO.setActivity_session_name(activity_session_name);
				activitySessionVO.setActivity_started(activity_started);
				activitySessionVO.setActivity_end(activity_end);
				activitySessionVO.setActivity_price(activity_price);
				activitySessionVO.setActivity_state(activity_state);
				activitySessionVO.setStatus_note(status_note);
				activitySessionVO.setActivity_max_part(activity_max_part);
				activitySessionVO.setActivity_min_part(activity_min_part);
				activitySessionVO.setEntered_total(entered_total);
				activitySessionVO.setEntered_started(entered_started);
				activitySessionVO.setEntered_end(entered_end);
				activitySessionVO.setProduct_status(product_status);

				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activitySessionVO", activitySessionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activitySession/addActivitySession.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ActivitySessionService activitySessionService= new ActivitySessionService();
				activitySessionVO = activitySessionService.insertActivitySession(activity_id,activity_session_name, activity_started,activity_end,activity_price,activity_state,status_note,activity_max_part,activity_min_part,entered_total,entered_started,entered_end,product_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activitySession/listAllActivitySession.jsp"); // 新增成功後轉交listAllActivitySession.jsp
//				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activitySession/success.jsp"); // 新增成功後轉交success.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activitySession/addActivitySession.jsp");
					failureView.forward(request, response);
				}
			}
		if ("getOne_For_Update".equals(action)) {// 來自listAllActivitySession.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				Integer activity_session_id = Integer.valueOf(request.getParameter("activity_session_id"));
				/***************************2.開始查詢資料****************************************/
				ActivitySessionService activitySessionService = new ActivitySessionService();
				ActivitySessionVO activitySessionVO = activitySessionService.getOneActivitySession(activity_session_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("activitySessionVO", activitySessionVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activitySession/updateActivitySession.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activitySession/listAllActivitySession.jsp");
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
				String str = request.getParameter("activity_session_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動場次編號");				
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activitySession/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				Integer activity_session_id = null;
				try {
					activity_session_id = Integer.valueOf(str);
					
				} catch (Exception e) {
					errorMsgs.add("活動場次編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activitySession/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ActivitySessionService activitySessionService =new ActivitySessionService();
				ActivitySessionVO activitySessionVO =activitySessionService.getOneActivitySession(activity_session_id);
				if (activitySessionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activitySession/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activitySessionVO", activitySessionVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activitySession/listOneActivitySession.jsp");
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/

			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activitySession/select_page.jsp");
				failureView.forward(request, response);
			}
		
		}
		if("update".equals(action)) { // 來自updateActicitySession.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer activity_session_id = Integer.valueOf(request.getParameter("activity_session_id"));
				Integer activity_id = Integer.valueOf(request.getParameter("activity_id"));
				
				String activity_session_name = request.getParameter("activity_session_name");
				if(activity_session_name == null || activity_session_name.trim().length() == 0) {
					errorMsgs.add("活動場次名稱: 請勿空白");
				}
				
				java.sql.Date entered_started = null;
				try {
					entered_started = java.sql.Date.valueOf(request.getParameter("entered_started").trim());
				} catch (IllegalArgumentException e) {
					entered_started=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始日期!");
				}
				
				java.sql.Date entered_end = null;
				try {
					entered_end = java.sql.Date.valueOf(request.getParameter("entered_end").trim());
				} catch (IllegalArgumentException e) {
					entered_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名截止日期!");
				}
				
				java.sql.Date activity_started = null;
				try {
					activity_started = java.sql.Date.valueOf(request.getParameter("activity_started").trim());
				} catch (IllegalArgumentException e) {
					activity_started=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期起!");
				}
				
				java.sql.Date activity_end = null;
				try {
					activity_end = java.sql.Date.valueOf(request.getParameter("activity_end").trim());
				} catch (IllegalArgumentException e) {
					activity_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入活動日期迄!");
				}
				
				Integer activity_price  = null ;
				try {
					activity_price = Integer.valueOf(request.getParameter("activity_price").trim());
				} catch (NumberFormatException e) {
					activity_price = 0;
					errorMsgs.add("活動定價請填數字.");
				}

//				Integer activity_state = Integer.valueOf(request.getParameter("activity_state"));
//				if(activity_state == null ) {
//					errorMsgs.add("活動狀態: 請勿空白");
//				}
				
				Integer activity_state  = null ;
				try {
					activity_state = Integer.valueOf(request.getParameter("activity_state").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("活動狀態: 請勿空白");
				}
				
				String status_note = String.valueOf(request.getParameter("status_note"));

				Integer activity_max_part  = null ;
				try {
					activity_max_part = Integer.valueOf(request.getParameter("activity_max_part").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("活動人數上限: 請勿空白");
				}
//				Integer activity_min_part = Integer.valueOf(request.getParameter("activity_min_part"));
//				if(activity_min_part == null ) {
//					errorMsgs.add("活動人數下限: 請勿空白");
//				}
				
				Integer activity_min_part  = null ;
				try {
					activity_min_part = Integer.valueOf(request.getParameter("activity_min_part").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("活動人數下限: 請勿空白");
				}
				
				Integer entered_total = Integer.valueOf(request.getParameter("entered_total"));
				
//				Integer product_status = Integer.valueOf(request.getParameter("product_status"));
//				if(product_status == null ) {
//					errorMsgs.add("商品狀態: 請勿空白");
//				}
				
				Integer product_status  = null ;
				try {
					product_status = Integer.valueOf(request.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("商品狀態: 請勿空白");
				}
				
				//存入vo
				ActivitySessionVO activitySessionVO = new ActivitySessionVO();
				activitySessionVO.setActivity_session_id(activity_session_id);
				activitySessionVO.setActivity_id(activity_id);
				activitySessionVO.setActivity_session_name(activity_session_name);
				activitySessionVO.setActivity_started(activity_started);
				activitySessionVO.setActivity_end(activity_end);
				activitySessionVO.setActivity_price(activity_price);
				activitySessionVO.setActivity_state(activity_state);
				activitySessionVO.setStatus_note(status_note);
				activitySessionVO.setActivity_max_part(activity_max_part);
				activitySessionVO.setActivity_min_part(activity_min_part);
				activitySessionVO.setEntered_total(entered_total);
				activitySessionVO.setEntered_started(entered_started);
				activitySessionVO.setEntered_end(entered_end);
				activitySessionVO.setProduct_status(product_status);
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activitySessionVO", activitySessionVO); // 含有輸入格式錯誤的activityVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activitySession/updateActivitySession.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				ActivitySessionService activitySessionService= new ActivitySessionService();
				activitySessionVO = activitySessionService.updateActivitySession(activity_session_id,activity_id,activity_session_name, activity_started,activity_end,activity_price,activity_state,status_note,activity_max_part,activity_min_part,entered_total,entered_started,entered_end,product_status);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activitySessionVO", activitySessionVO); // 資料庫update成功後,正確的的activityVO物件,存入req
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activitySession/listAllActivitySession.jsp"); // 修改成功後,轉交listOneActivity.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activitySession/updateActivitySession.jsp");
				failureView.forward(request, response);

			}
		}
		if ("delete".equals(action)) { // 來自listAllSessionActivity.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數***************************************/
				Integer activity_session_id = Integer.valueOf(request.getParameter("activity_session_id"));
				
				/***************************2.開始刪除資料***************************************/
				ActivitySessionService activitySessionService =new ActivitySessionService();
				activitySessionService.deleteActivitySession(activity_session_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activitySession/listAllActivitySession.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activitySession/listAllActivitySession.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
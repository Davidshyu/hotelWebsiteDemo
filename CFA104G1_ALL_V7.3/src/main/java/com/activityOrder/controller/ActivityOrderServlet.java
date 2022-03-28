package com.activityOrder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.activityOrder.model.ActivityOrderService;
import com.activityOrder.model.ActivityOrderVO;
@WebServlet("/activityOrder.do")
public class ActivityOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		// 判斷action=insert
		if("insert".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer mem_id =Integer.valueOf(request.getParameter("mem_id"));
				Integer activity_session_id =Integer.valueOf(request.getParameter("activity_session_id"));
				java.sql.Date order_time = null;
				try {
					order_time = java.sql.Date.valueOf(request.getParameter("order_time").trim());
				} catch (IllegalArgumentException e) {
					order_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer entered_number =Integer.valueOf(request.getParameter("entered_number"));
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
				Integer order_amount =Integer.valueOf(request.getParameter("order_amount"));
				Integer order_state =Integer.valueOf(request.getParameter("order_state"));
				Integer refund_state =Integer.valueOf(request.getParameter("refund_state"));
				String order_memo = String.valueOf(request.getParameter("order_memo"));
				//存入vo
				ActivityOrderVO activityOrderVO = new ActivityOrderVO();
				activityOrderVO.setMem_id(mem_id);
				activityOrderVO.setActivity_session_id(activity_session_id);
				activityOrderVO.setOrder_time(order_time);
				activityOrderVO.setEntered_number(entered_number);
				activityOrderVO.setActivity_started(activity_started);
				activityOrderVO.setActivity_end(activity_end);
				activityOrderVO.setOrder_amount(order_amount);
				activityOrderVO.setOrder_state(order_state);
				activityOrderVO.setRefund_state(refund_state);
				activityOrderVO.setOrder_memo(order_memo);
				
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityOrderVO", activityOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activityOrder/addActivityOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ActivityOrderService activityOrderService= new ActivityOrderService();
				activityOrderVO = activityOrderService.insertActivityOrder(mem_id,activity_session_id, order_time,entered_number,activity_started,activity_end,order_amount,order_state,refund_state,order_memo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityOrder/listAllActivityOrder.jsp"); // 新增成功後轉交listAllActivitySession.jsp
//				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityOrder/success.jsp"); // 新增成功後轉交success.jsp
				successView.forward(request, response);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activityOrder/addActivityOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("getOne_For_Update".equals(action)) {// 來自listAllActivityOrder.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				Integer activity_order_id = Integer.valueOf(request.getParameter("activity_order_id"));
				/***************************2.開始查詢資料****************************************/
				ActivityOrderService activityOrderService = new ActivityOrderService();
				ActivityOrderVO activityOrderVO = activityOrderService.getOneActivityOrder(activity_order_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("activityOrderVO", activityOrderVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityOrder/updateActivityOrder.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityOrder/listAllActivityOrder.jsp");
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
				String str = request.getParameter("activity_order_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動場次編號");				
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activityOrder/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				Integer activity_order_id = null;
				try {
					activity_order_id = Integer.valueOf(str);
					
				} catch (Exception e) {
					errorMsgs.add("活動場次編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activityOrder/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ActivityOrderService activityOrderService =new ActivityOrderService();
				ActivityOrderVO activityOrderVO =activityOrderService.getOneActivityOrder(activity_order_id);
				if (activityOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activityOrder/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activityOrderVO", activityOrderVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityOrder/listOneActivityOrder.jsp");
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/

			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityOrder/select_page.jsp");
				failureView.forward(request, response);
			}
		
		}
		if("update".equals(action)) { // 來自updateActicitySession.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer activity_order_id = Integer.valueOf(request.getParameter("activity_order_id"));
				Integer mem_id =Integer.valueOf(request.getParameter("mem_id"));
				Integer activity_session_id =Integer.valueOf(request.getParameter("activity_session_id"));
				java.sql.Date order_time = null;
				try {
					order_time = java.sql.Date.valueOf(request.getParameter("order_time").trim());
				} catch (IllegalArgumentException e) {
					order_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer entered_number =Integer.valueOf(request.getParameter("entered_number"));
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
				Integer order_amount =Integer.valueOf(request.getParameter("order_amount"));
				Integer order_state =Integer.valueOf(request.getParameter("order_state"));
				Integer refund_state =Integer.valueOf(request.getParameter("refund_state"));
				String order_memo = String.valueOf(request.getParameter("order_memo"));
				//存入vo
				ActivityOrderVO activityOrderVO = new ActivityOrderVO();
				activityOrderVO.setMem_id(mem_id);
				activityOrderVO.setActivity_session_id(activity_session_id);
				activityOrderVO.setOrder_time(order_time);
				activityOrderVO.setEntered_number(entered_number);
				activityOrderVO.setActivity_started(activity_started);
				activityOrderVO.setActivity_end(activity_end);
				activityOrderVO.setOrder_amount(order_amount);
				activityOrderVO.setOrder_state(order_state);
				activityOrderVO.setRefund_state(refund_state);
				activityOrderVO.setOrder_memo(order_memo);
				
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityOrderVO", activityOrderVO); // 含有輸入格式錯誤的activityVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activityOrder/updateActivityOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				ActivityOrderService activityOrderService= new ActivityOrderService();
				activityOrderVO = activityOrderService.updateActivityOrder(activity_order_id,mem_id,activity_session_id, order_time,entered_number,activity_started,activity_end,order_amount,order_state,refund_state,order_memo);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activityOrderVO", activityOrderVO); // 資料庫update成功後,正確的的activityVO物件,存入req
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityOrder/listAllActivityOrder.jsp"); // 修改成功後,轉交listOneActivity.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityOrder/updateActivityOrder.jsp");
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
				Integer activity_order_id = Integer.valueOf(request.getParameter("activity_order_id"));
				
				/***************************2.開始刪除資料***************************************/
				ActivityOrderService activityOrderService =new ActivityOrderService();
				activityOrderService.deleteActivityOrder(activity_order_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activityOrder/listAllActivityOrder.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activityOrder/listAllActivityOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if("insertFront".equals(action)) {
			List<String> errorMsgs =new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer mem_id =Integer.valueOf(request.getParameter("mem_id"));
				Integer activity_session_id =Integer.valueOf(request.getParameter("activity_session_id"));
				java.sql.Date order_time = null;
				try {
					order_time = java.sql.Date.valueOf(request.getParameter("order_time").trim());
				} catch (IllegalArgumentException e) {
					order_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer entered_number =Integer.valueOf(request.getParameter("entered_number"));
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
				Integer order_amount =Integer.valueOf(request.getParameter("order_amount"));
				Integer order_state =Integer.valueOf(request.getParameter("order_state"));
				Integer refund_state =Integer.valueOf(request.getParameter("refund_state"));
				String order_memo = String.valueOf(request.getParameter("order_memo"));
				//存入vo
				ActivityOrderVO activityOrderVO = new ActivityOrderVO();
				activityOrderVO.setMem_id(mem_id);
				activityOrderVO.setActivity_session_id(activity_session_id);
				activityOrderVO.setOrder_time(order_time);
				activityOrderVO.setEntered_number(entered_number);
				activityOrderVO.setActivity_started(activity_started);
				activityOrderVO.setActivity_end(activity_end);
				activityOrderVO.setOrder_amount(order_amount);
				activityOrderVO.setOrder_state(order_state);
				activityOrderVO.setRefund_state(refund_state);
				activityOrderVO.setOrder_memo(order_memo);
				
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityOrderVO", activityOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/activityOrder/addActivityOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ActivityOrderService activityOrderService= new ActivityOrderService();
				activityOrderVO = activityOrderService.insertActivityOrder(mem_id,activity_session_id, order_time,entered_number,activity_started,activity_end,order_amount,order_state,refund_state,order_memo);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				request.setAttribute("activityOrderVO", activityOrderVO);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/activityOrder/orderPay.jsp"); // 新增成功後轉交success.jsp
				successView.forward(request, response);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/activityOrder/addActivityOrder.jsp");
				failureView.forward(request, response);
			}
		}
	}

}

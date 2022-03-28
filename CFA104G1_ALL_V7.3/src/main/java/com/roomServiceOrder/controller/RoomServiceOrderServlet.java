package com.roomServiceOrder.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meal.model.Meal;
import com.meal.model.MealService;
import com.meal.model.MealVO;
import com.mysql.cj.Session;
import com.roomOrderList.model.RoomOrderListService;
import com.roomOrderList.model.RoomOrderListVO;
import com.roomServiceOrder.model.RoomServiceOrderService;
import com.roomServiceOrder.model.RoomServiceOrderVO;
import com.roomServiceOrderList.model.RoomServiceOrderListService;
import com.roomServiceOrderList.model.RoomServiceOrderListVO;

public class RoomServiceOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("room_id");
				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入房間編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer room_id = null;
				try {
					room_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("房間編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RoomServiceOrderService rsosSvc = new RoomServiceOrderService();
				RoomServiceOrderVO RoomServiceOrderVO = rsosSvc.getOneRoomServiceOrder(room_id);
				if (RoomServiceOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display2".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("service_order_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/roomservice/listOneRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer service_order_id = null;
				try {
					service_order_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/roomservice/listOneRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RoomServiceOrderListService rsolsSvc = new RoomServiceOrderListService();
				RoomServiceOrderListVO RoomServiceOrderListVO = rsolsSvc.getOneRoomServiceOrderList(service_order_id);
				if (RoomServiceOrderListVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/roomservice/listOneRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("RoomServiceOrderListVO", RoomServiceOrderListVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/roomservice/listOneRoomServiceOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/roomservice/listOneRoomServiceOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer service_order_id = new Integer(req.getParameter("service_order_id"));
				
				/***************************2.開始查詢資料****************************************/
				RoomServiceOrderService rsosSvc = new RoomServiceOrderService();
				RoomServiceOrderVO RoomServiceOrderVO = rsosSvc.getOneRoomServiceOrder(service_order_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/RoomServiceOrder/update_RoomServiceOrder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer service_order_id = new Integer(req.getParameter("service_order_id").trim());
				
				Integer room_id = null;
				try {
					room_id = new Integer(req.getParameter("room_id"));
				} catch (NumberFormatException e) {
					room_id = 0;
					errorMsgs.add("服務訂單編號請填數字");
				}
				
				Boolean service_order_status = null;
			    try {
			    	service_order_status = new Boolean(req.getParameter("service_order_status").trim());
			    } catch (NumberFormatException e) {
			    	service_order_status = true;
			    	errorMsgs.add("上下架狀態請勿空白.");
			    }
				
			    java.sql.Date service_order_date = null;
				try {
					service_order_date = java.sql.Date.valueOf(req.getParameter("service_order_date").trim());
				} catch (IllegalArgumentException e) {
					service_order_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
			    
				

				RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setService_order_id(service_order_id);
				RoomServiceOrderVO.setRoom_id(room_id);
				RoomServiceOrderVO.setService_order_status(service_order_status);
				RoomServiceOrderVO.setService_order_date(service_order_date);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/RoomServiceOrder/update_RoomServiceOrder_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				RoomServiceOrderService rsosSvc = new RoomServiceOrderService();
				RoomServiceOrderVO = rsosSvc.updateRoomServiceOrder(service_order_id, room_id, service_order_status, service_order_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/RoomServiceOrder/listOneRoomServiceOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/RoomServiceOrder/update_RoomServiceOrder_input.jsp");
				failureView.forward(req, res);
			}
			
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				System.out.println("我有到這");

				String roomid = req.getParameter("room_id");
				System.out.println(roomid);
				Integer room_id = Integer.parseInt(roomid);
				System.out.println(room_id);
				
				Boolean service_order_status = true;
				
			    Date service_order_date = new Date(System.currentTimeMillis());
				

				RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setRoom_id(room_id);
				RoomServiceOrderVO.setService_order_status(true);
				RoomServiceOrderVO.setService_order_date(new java.sql.Date(new java.util.Date().getTime()));
				
				Integer count = new Integer(req.getParameter("count"));
				System.out.println(count);
				List<RoomServiceOrderListVO> list2 = new ArrayList<RoomServiceOrderListVO>();
				for(int i = 1; i <= count ; i++) {
				String meal_id = req.getParameter("meal_id"+i);
				String meal_price = req.getParameter("meal_price"+i);
				String meal_quantity = req.getParameter("meal_quantity"+i);
				
				System.out.println("我有走到這");
				System.out.println("只有一個 :" + meal_id);
				System.out.println("餐點價格:" + meal_price);
				
				RoomServiceOrderListVO list = new RoomServiceOrderListVO();
				list.setMeal_id(new Integer(meal_id).intValue());
				list.setMeal_price((int)(new Float(meal_price)).floatValue());
				list.setMeal_quantity((new Integer(meal_quantity)).intValue());
				list.setMeal_total(list.getMeal_price() * list.getMeal_quantity());
				list2.add(list);
				}
				
				RoomOrderListVO RoomOrderListVO = new RoomOrderListVO();
				RoomOrderListVO.setRoom_id(room_id);
				RoomOrderListVO.setService_order_id(null);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/roomservice/service.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				RoomServiceOrderService rsosSvc = new RoomServiceOrderService();
				RoomServiceOrderVO = rsosSvc.insertWithOrderList(room_id,service_order_status,service_order_date, list2 , RoomOrderListVO);
				 
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/roomservice/service.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/roomservice/service.jsp");
				failureView.forward(req, res);
			}
			String a = null;
			req.setAttribute("shoppingcart", a);
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer service_order_id = new Integer(req.getParameter("service_order_id"));
				
				/***************************2.開始刪除資料***************************************/
				RoomServiceOrderService rsosSvc = new RoomServiceOrderService();
				rsosSvc.deleteRoomServiceOrder(service_order_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
				failureView.forward(req, res);
			}
		}
if ("checkout".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer service_order_id = new Integer(req.getParameter("service_order_id").trim());

				Boolean service_order_status = false;
					

				RoomServiceOrderVO RoomServiceOrderVO = new RoomServiceOrderVO();
				RoomServiceOrderVO.setService_order_id(service_order_id);
				RoomServiceOrderVO.setService_order_status(service_order_status);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				RoomServiceOrderService rsosSvc = new RoomServiceOrderService();
				RoomServiceOrderVO = rsosSvc.checkout(service_order_id, service_order_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("RoomServiceOrderVO", RoomServiceOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/RoomServiceOrder/listAllRoomServiceOrder.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
	
}

package com.roomOrder.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Result;
import com.roomOrder.model.RoomOrderService;
import com.roomOrder.model.RoomOrderVO;
import com.roomOrderList.model.RoomOrderListService;
import com.roomOrderList.model.RoomOrderListVO;
import com.roomSchedule.model.RoomScheduleVO;
import com.roomSchedule.model.RoomscheduleService;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;
import com.roomOrderList.model.RoomOrderListVO;
import com.room.model.*;


@WebServlet("/roomOrder/roomOrder.do")
public class RoomOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		
		if("getOne_For_Dispaly".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();			
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = request.getParameter("room_order_id");
				if(str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入住宿訂單編號");
				}
								
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				Integer room_order_id =null;
				try {
					room_order_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("請輸入數字格式");
				}
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				RoomOrderService RoomOrderSvc = new RoomOrderService();
				RoomOrderVO roomOrderVO = RoomOrderSvc.getOneRoomOrder(room_order_id);
				
				if (roomOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				request.setAttribute("roomOrderVO", roomOrderVO);
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);				
				
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/");
				failureView.forward(request, response);
			}
			
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id"));
				
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO =roomOrderService.getOneRoomOrder(room_order_id);
				
				request.setAttribute("roomOederVO", roomOrderVO);
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);	

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料"+ e.getMessage());
                RequestDispatcher failureView = request.getRequestDispatcher("/");
            	failureView.forward(request, response);
			}

		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				Integer mem_id =new Integer(request.getParameter("mem_id").trim());
				
				java.sql.Date order_date = null;
				try {
					order_date = java.sql.Date.valueOf(request.getParameter("order_date").trim());
				} catch (Exception e) {
					order_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				Integer room_order_status =new Integer(request.getParameter("room_order_status").trim());
				Integer room_charge =new Integer(request.getParameter("room_charge").trim());
				Integer room_review =new Integer(request.getParameter("room_review").trim());
				
				java.sql.Date arrival_date = null;
				try {
					arrival_date = java.sql.Date.valueOf(request.getParameter("arrival_date").trim());
				} catch (Exception e) {
					arrival_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				java.sql.Date departure_date = null;
				try {
					departure_date = java.sql.Date.valueOf(request.getParameter("departure_date").trim());
				} catch (Exception e) {
					departure_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(room_order_id);
				roomOrderVO.setMem_id(mem_id);
				roomOrderVO.setOrder_date(order_date);
				roomOrderVO.setRoom_order_status(room_order_status);
				roomOrderVO.setRoom_charge(room_charge);
				roomOrderVO.setRoom_review(room_review);
				roomOrderVO.setArrival_date(arrival_date);
				roomOrderVO.setDeparture_date(departure_date);
				
				RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
				
	
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("roomOrderVO", roomOrderVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomOrder/updateOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.updateRoomOder(room_order_id, mem_id, order_date, room_order_status, room_charge, room_review,arrival_date ,departure_date) ;
				
				request.setAttribute("roomOrderVO", roomOrderVO);
				String url = "/back-end/roomOrder/listAllOrder.jsp";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomOrder/updateOrder.jsp");
				failureView.forward(request, response);
			} 
						
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				Integer mem_id =new Integer(request.getParameter("mem_id").trim());
				
				java.sql.Date order_date = null;
				try {
					order_date = java.sql.Date.valueOf(request.getParameter("order_date").trim());
				} catch (Exception e) {
					order_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				Integer room_order_status =new Integer(request.getParameter("room_order_status").trim());
				Integer room_charge =new Integer(request.getParameter("room_charge").trim());
				Integer room_review =new Integer(request.getParameter("room_review").trim());
				
				java.sql.Date arrival_date = null;
				try {
					arrival_date = java.sql.Date.valueOf(request.getParameter("arrival_date").trim());
				} catch (Exception e) {
					arrival_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				java.sql.Date departure_date = null;
				try {
					departure_date = java.sql.Date.valueOf(request.getParameter("departure_date").trim());
				} catch (Exception e) {
					departure_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期格式1");
				}
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
			
				roomOrderVO.setMem_id(mem_id);
				roomOrderVO.setOrder_date(order_date);
				roomOrderVO.setRoom_order_status(room_order_status);
				roomOrderVO.setRoom_charge(room_charge);
				roomOrderVO.setRoom_review(room_review);
				roomOrderVO.setArrival_date(arrival_date);
				roomOrderVO.setDeparture_date(departure_date);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("roomOrderVO", roomOrderVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/");
					failureView.forward(request, response);
					return;
				}
				
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.addRoomOrder(mem_id, order_date, room_order_status, room_charge, room_review,arrival_date,departure_date);
				
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);
				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
			}
					
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderSvc.deleteRoomOrder(room_order_id);
				
				String url = "/";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);
				
			} catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/");
				failureView.forward(request, response);
				// TODO: handle exception
			}
		}
		
		if ("backend_getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer room_order_id = new Integer(request.getParameter("room_order_id"));
				/*************************** 2.開始查詢資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(room_order_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("roomOrderVO",roomOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/roomOrder/updateOrder.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/roomOrder/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("SaveDateAndShowCheckOut".equals(action)) {
			Integer room_type_id = new Integer(request.getParameter("room_type_id"));
			Integer room_type_price = new Integer(request.getParameter("room_type_price"));
			String room_type_name = request.getParameter("room_type_name");
			
			RoomTypeVO roomTypeVO = new RoomTypeVO();
			roomTypeVO.setRoom_type_id(room_type_id);
			roomTypeVO.setRoom_type_price(room_type_price);
			roomTypeVO.setRoom_type_name(room_type_name);
		
			session.setAttribute("roomTypeVO", roomTypeVO);
			
			String url = "/front-end/roomOrder/checkout.jsp";	
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		}

		

		
		if("checkIn".equals(action)) {
			
			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				Integer mem_id = new Integer(request.getParameter("mem_id").trim());
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(room_order_id);
				
				RoomOrderListService roomOrderListService = new  RoomOrderListService();
				List<RoomOrderListVO> list = roomOrderListService.getOrderId(room_order_id);
				
				for(RoomOrderListVO vo : list) {
					//取值
					Integer roomOrderListId = vo.getRoom_order_list_id();
					Integer roomTypeID = vo.getRoom_type_id();
					//找出房型所剩空房
					RoomService roomService = new RoomService();
					List<RoomVO> roomTypeList = roomService.getTypeAll(roomTypeID);
					System.out.println(roomTypeList.get(0).getRoom_id());
					Integer room_id = roomTypeList.get(0).getRoom_id();
					
					
					vo.setRoom_id(room_id);
					//明細列表寫入房號
					vo =roomOrderListService.checkUpdateRoomID(roomOrderListId, room_id);
					//改房間狀態
					roomService.checkIn(room_id,mem_id.toString());

				}
				
//				====================寫入============================
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.checkIn(room_order_id) ;
				
				
				
				request.setAttribute("roomOrderVO", roomOrderVO);
				String url = "/back-end/checkInAndOut/CheckcControl.jsp";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/checkInAndOut/CheckcControl.jsp");
				failureView.forward(request, response);
			} 
						
		}
		
		if ("SaveOder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();			
			request.setAttribute("errorMsgs", errorMsgs);
			java.sql.Date arrival_date = null;
			arrival_date = java.sql.Date.valueOf(request.getParameter("arrival_date").trim());
			java.sql.Date departure_date = null;
			departure_date = java.sql.Date.valueOf(request.getParameter("departure_date").trim());
			//計算要住的晚上時間and總訂房數量
			long stayDate = (departure_date.getTime() - arrival_date.getTime())/(24 * 60 * 60 * 1000L);
			Integer total_room_number = new Integer(request.getParameter("total_room_number").trim());
			
			// save 定房資訊for Room_Oder表格
			Integer mem_id = new Integer(request.getParameter("mem_id").trim());

			java.sql.Date order_date = null;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>  "+request.getParameter("order_date").trim());
			order_date = java.sql.Date.valueOf(request.getParameter("order_date").trim());
			Integer room_order_status = new Integer(request.getParameter("room_order_status").trim());
			Integer room_charge = new Integer(request.getParameter("room_charge").trim());
			Integer room_review = new Integer(request.getParameter("room_review").trim());
			
			RoomOrderVO roomOrderVO = new RoomOrderVO();
			roomOrderVO.setMem_id(mem_id);
			roomOrderVO.setOrder_date(order_date);
			roomOrderVO.setRoom_order_status(room_order_status);
			roomOrderVO.setRoom_charge(room_charge);
			roomOrderVO.setRoom_review(room_review);
			roomOrderVO.setArrival_date(arrival_date);
			roomOrderVO.setDeparture_date(departure_date);

			RoomOrderService roomOrderSvc = new RoomOrderService();
			roomOrderVO = roomOrderSvc.addRoomOrder(mem_id, order_date, room_order_status, room_charge,
					room_review,arrival_date,departure_date);
			// save 訂房資訊by 房間數量	
			for (int k = 0; k < total_room_number; k++) {
				// save 訂房資訊for Roomschedule 表格 by 每天做一次
				for (int i = 0; i < stayDate; i++) {
					long long2 = arrival_date.getTime() + i * 24 * 60 * 60 * 1000L;
					java.sql.Date date = new java.sql.Date(long2);
					Integer room_type_id = new Integer(request.getParameter("room_type_id").trim()); // from Jsp 頁面

					// ROOM_TYPE_AMOUNT抓ROOM_TYPE 裡面的 ROOM_TYPE_AMOUNT房型數量
					RoomTypeService roomTypeSvc = new RoomTypeService();
					RoomTypeVO roomTypeVO = roomTypeSvc.getOneRoomType(room_type_id);
					Integer room_amount = roomTypeVO.getRoom_type_amount();

					Integer room_rsv_booked = 0;
					RoomScheduleVO roomScheduleVO1 = new RoomScheduleVO();
					RoomscheduleService roomscheduleSvc = new RoomscheduleService();
					roomScheduleVO1 = roomscheduleSvc.getOnebyDateandTypeID(room_type_id, date);

					// for 如果當天日期這個房型都還沒有被訂過
					if (roomScheduleVO1 == null) {
						room_rsv_booked = 1;
						java.sql.Date room_schedule_date = date;

						RoomScheduleVO roomScheduleVO = new RoomScheduleVO();
						roomScheduleVO.setRoom_type_id(room_type_id);
						roomScheduleVO.setRoom_schedule_date(room_schedule_date);
						roomScheduleVO.setRoom_amount(room_amount);
						roomScheduleVO.setRoom_rsv_booked(room_rsv_booked);

						roomscheduleSvc.addRoomSchedule(room_type_id, room_schedule_date, room_amount, room_rsv_booked);
					} else {
						// 當日此房型有被訂過
						// 被搶先訂走了
						if(roomScheduleVO1.getRoom_rsv_booked() == roomScheduleVO1.getRoom_amount()) {
							RoomOrderVO roomOrderVOa = new RoomOrderVO();
							roomOrderVOa = roomOrderSvc.getRoomOrderbyMAXID();
							roomOrderSvc.deleteRoomOrder(roomOrderVOa.getRoom_order_id()); 
						
							errorMsgs.add("很抱歉!!房間已滿");
						
							RequestDispatcher failureView = request.getRequestDispatcher("/front-end/roomOrder/addRoomreservation.jsp");
							failureView.forward(request, response);
							return;					
						}
			
						room_rsv_booked = roomScheduleVO1.getRoom_rsv_booked() + 1;
						roomScheduleVO1.setRoom_rsv_booked(room_rsv_booked);
						roomscheduleSvc.updateRoomschedulersv(roomScheduleVO1, room_rsv_booked);
					}

				}


				// save定房資訊forRoomorderlist表格
				Integer room_id = new Integer(request.getParameter("room_id").trim());
				// 取得roomOrder中最新一筆room_orderid
				RoomOrderVO roomOrderVO2 = new RoomOrderVO();
				roomOrderVO2 = roomOrderSvc.getRoomOrderbyMAXID();
				Integer room_order_id = roomOrderVO2.getRoom_order_id();
				
				Integer number_of_people = new Integer(request.getParameter("number_of_people").trim());
				String special_req = request.getParameter("special_req").trim();
				Integer room_price = new Integer(request.getParameter("room_price").trim());
				Integer service_order_id = new Integer(request.getParameter("service_order_id").trim());
				Integer room_type_id = new Integer(request.getParameter("room_type_id").trim());

				RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
				roomOrderListVO.setRoom_id(room_id);
				roomOrderListVO.setRoom_order_id(room_order_id);
				roomOrderListVO.setNumber_of_people(number_of_people);
				roomOrderListVO.setSpecial_req(special_req);
				roomOrderListVO.setRoom_price(room_price);
				roomOrderListVO.setService_order_id(service_order_id);
				roomOrderListVO.setRoom_type_id(room_type_id);

				RoomOrderListService roomOrderListSvc = new RoomOrderListService();
				roomOrderListSvc.addRoom_order_list(room_id, room_order_id, number_of_people, special_req, room_price, service_order_id, room_type_id);
			}
			
			String url = "/front-end/roomOrder/checkoutOK.jsp";
			RequestDispatcher sucessView = request.getRequestDispatcher(url);
			sucessView.forward(request, response);

		}
		
		
		if("CheckOut".equals(action)) {
			
			try {
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoom_order_id(room_order_id);
				
//				RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
//				roomOrderListVO.setRoom_order_id(room_order_id);
				
				RoomOrderListService roomOrderListService = new  RoomOrderListService();
				List<RoomOrderListVO> list = roomOrderListService.getOrderId(room_order_id);
				for(RoomOrderListVO vo : list) {
					//取值
					Integer roomOrderListId = vo.getRoom_order_list_id();
					Integer roomTypeID = vo.getRoom_type_id();
					Integer roomID = vo.getRoom_id();
					
					//明細列表寫入房號
					vo =roomOrderListService.checkUpdateRoomID(roomOrderListId, 0	);
					
					//房間狀態改變成待清潔
					RoomService roomService = new RoomService();
					roomService.checkOut(roomID);
					
				}
				
	
				
//				====================寫入============================
				RoomOrderService roomOrderSvc = new RoomOrderService();
				roomOrderVO = roomOrderSvc.checkOut(room_order_id) ;
				
				
				
				request.setAttribute("roomOrderVO", roomOrderVO);
				String url = "/back-end/checkInAndOut/CheckcControl.jsp";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/checkInAndOut/CheckcControl.jsp");
				failureView.forward(request, response);
			} 
						
		}
		if("saveRoomReview".equals(action)) {
			
			try {
				Integer room_review = new Integer(request.getParameter("room_review").trim());
				Integer room_order_id = new Integer(request.getParameter("room_order_id").trim());
				
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				RoomOrderService roomOrderService = new  RoomOrderService();
				roomOrderVO = roomOrderService.getOneRoomOrder(room_order_id);				
				roomOrderVO.setRoom_review(room_review);
				
				roomOrderService.updateRoomOder(room_order_id,roomOrderVO.getMem_id(), roomOrderVO.getOrder_date(), roomOrderVO.getRoom_order_status(), roomOrderVO.getRoom_charge(), room_review, roomOrderVO.getArrival_date(), roomOrderVO.getDeparture_date());
				String url = "/front-end/roomOrder/memCheckOrder.jsp";
				RequestDispatcher sucessView = request.getRequestDispatcher(url);
				sucessView.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/roomOrder/memCheckOrder.jsp");
				failureView.forward(request, response);
			} 
						
		}
		
	}

}

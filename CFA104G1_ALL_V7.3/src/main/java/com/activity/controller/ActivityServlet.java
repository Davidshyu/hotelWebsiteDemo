package com.activity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.activity.model.ActivityService;
import com.activity.model.ActivityVO;
import com.activityPhoto.model.ActivityPhotoService;
import com.activityPhoto.model.ActivityPhotoVO;

@WebServlet("/activity.do")
@MultipartConfig(fileSizeThreshold = 1024 *1024 , maxFileSize = 5*1024*1024, maxRequestSize = 5*100*1024*1024)
public class ActivityServlet extends HttpServlet {
     
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//判斷action=insert
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer activity_category_id = Integer.valueOf(request.getParameter("activity_category_id"));
				
				String activity_name = request.getParameter("activity_name");
				if(activity_name == null || activity_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				}
				String activity_info = request.getParameter("activity_info");
				if (activity_info == null || activity_info.trim().length() == 0) {
					errorMsgs.add("活動資訊: 請勿空白");					
				}	
				InputStream is = null;
				byte[] act_pic = null;
				Part filePart = request.getPart("activity_photo_file");
				if (filePart != null) {
					is = filePart.getInputStream();
					act_pic = new byte[is.available()];
					is.read(act_pic);
					is.close();
				}
				//存入vo
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActivity_category_id(activity_category_id);
				activityVO.setActivity_name(activity_name);
				activityVO.setActivity_info(activity_info);
				
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activity/addActivity.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始新增資料***************************************/
				ActivityService activityService= new ActivityService();
				activityVO = activityService.insertActivity(activity_category_id,activity_name, activity_info);

				Integer activity_id  =activityVO.getActivity_id();
				
				ActivityPhotoVO activityPhotoVO =new ActivityPhotoVO();
				activityPhotoVO.setActivity_id(activity_id);
				activityPhotoVO.setActivity_photo_file(act_pic);
				
				ActivityPhotoService activityPhotoService = new ActivityPhotoService();
				activityPhotoVO = activityPhotoService.insertActivityPhoto(activity_id, act_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activity/listAllActivity.jsp"); // 新增成功後轉交listAllActivity.jsp
//				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activity/success.jsp"); // 新增成功後轉交success.jsp
				successView.forward(request, response);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activity/addActivity.jsp");
				failureView.forward(request, response);
			}
			
		}
		if ("getOne_For_Update".equals(action)) {// 來自listAllActivity.jsp的請求
			List<String>errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數****************************************/
				Integer activity_id = Integer.valueOf(request.getParameter("activity_id"));
				/***************************2.開始查詢資料****************************************/
				ActivityService activityService = new ActivityService();
				ActivityVO activityVO = activityService.getOneActivity(activity_id);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("activityVO", activityVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activity/updateActivity.jsp");
				successView.forward(request, response);
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activity/listAllActivity.jsp");
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
				String str = request.getParameter("activity_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activity/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				Integer activity_id = null;
				try {
					activity_id = Integer.valueOf(str);
					
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activity/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ActivityService activityService =new ActivityService();
				ActivityVO activityVO =activityService.getOneActivity(activity_id);
				if (activityVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/activity/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activityVO", activityVO);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activity/listOneActivity.jsp");
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activity/select_page.jsp");
				failureView.forward(request, response);
			}
			
		}
		if("update".equals(action)) { // 來自updateActicity.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer activity_id = Integer.valueOf(request.getParameter("activity_id"));
				Integer activity_category_id = Integer.valueOf(request.getParameter("activity_category_id"));
				
				String activity_name = request.getParameter("activity_name");
				if(activity_name == null || activity_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				}
				
				String activity_info = request.getParameter("activity_info");
				if (activity_info == null || activity_info.trim().length() == 0) {
					errorMsgs.add("活動資訊: 請勿空白");	
				}
				//存入vo
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActivity_id(activity_id);
				activityVO.setActivity_category_id(activity_category_id);
				activityVO.setActivity_name(activity_name);
				activityVO.setActivity_info(activity_info);
				//若有錯誤訊息時，把vo存入session
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("activityVO", activityVO); // 含有輸入格式錯誤的activityVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activity/updateActivity.jsp");
					failureView.forward(request, response);
					return;
				}
				/***************************2.開始修改資料*****************************************/
				ActivityService activityService= new ActivityService();
				activityVO = activityService.updateActivity(activity_id,activity_category_id,activity_name, activity_info);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("activityVO", activityVO); // 資料庫update成功後,正確的的activityVO物件,存入req
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activity/listAllActivity.jsp"); // 修改成功後,轉交listOneActivity.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activity/updateActivity.jsp");
				failureView.forward(request, response);
			
			}
		}
		if ("delete".equals(action)) { // 來自listAllActivity.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數***************************************/
				Integer activity_id = Integer.valueOf(request.getParameter("activity_id"));
				
				/***************************2.開始刪除資料***************************************/
				ActivityService activityService =new ActivityService();
				activityService.deleteActivity(activity_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/activity/listAllActivity.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/activity/listAllActivity.jsp");
				failureView.forward(request, response);
			}
		}
		if ("toUpdatePIC".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer activity_id = new Integer(request.getParameter("activity_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				
				ActivityService activityService = new ActivityService();
				ActivityVO activityVO = activityService.getOneActivity(activity_id);
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("activityVO",activityVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/activityPhoto/listAllActivityPhoto.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/activity/listAllActivity.jsp");
				failureView.forward(request, response);
			}
		}
	}
}

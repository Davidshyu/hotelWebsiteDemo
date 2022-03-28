package com.activityPhoto.controller;

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

import com.activity.model.ActivityVO;
import com.activityPhoto.model.ActivityPhotoService;
import com.activityPhoto.model.ActivityPhotoVO;



@WebServlet("/ActivityPhotoPicServlet.do")
@MultipartConfig(fileSizeThreshold = 1024 *1024 , maxFileSize = 5*1024*1024, maxRequestSize = 5*100*1024*1024)
public class ActivityPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer activity_id = new Integer(req.getParameter("activity_id"));
				Integer activity_photo_id = new Integer(req.getParameter("activity_photo_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				ActivityPhotoService activityPhotoService = new ActivityPhotoService();
				activityPhotoService.deleteActivityPhoto(activity_photo_id);
				
				ActivityPhotoVO activityPhotoVO = new ActivityPhotoVO();
				activityPhotoVO.setActivity_id(activity_id);
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActivity_id(activity_id);
				


				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("activityPhotoVO",activityPhotoVO);
				req.setAttribute("activityVO",activityVO);
				String url = "/back-end/activityPhoto/listAllActivityPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activityPhoto/listAllActivityPhoto.jsp");
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
				Integer activity_id = null;
				try {
					activity_id = new Integer(req.getParameter("activity_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("價格請填數字.");
				}

				
				InputStream is = null;
				byte[] room_pic = null;
				Part filePart = req.getPart("activity_photo_file");
				if (filePart != null) {
					is = filePart.getInputStream();
					room_pic = new byte[is.available()];
					is.read(room_pic);
					is.close();
				}
				
				ActivityPhotoVO activityPhotoVO =new ActivityPhotoVO();
				activityPhotoVO.setActivity_id(activity_id);
				activityPhotoVO.setActivity_photo_file(room_pic);
				
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActivity_id(activity_id);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityPhotoVO", activityPhotoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activityPhoto/addActivityPic.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				
				
				ActivityPhotoService activityPhotoService = new ActivityPhotoService();
				activityPhotoVO = activityPhotoService.addRoomTypePic(activity_id, room_pic);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("activityPhotoVO",activityPhotoVO);
				req.setAttribute("activityVO",activityVO);
				String url = "/back-end/activityPhoto/listAllActivityPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activityPhoto/addActivityPic.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Insert".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer activity_id = new Integer(req.getParameter("activity_id"));
				System.out.println(activity_id);

				/*************************** 2.開始查詢資料 ****************************************/
				ActivityPhotoService activityPhotoService = new ActivityPhotoService();
				ActivityPhotoVO activityPhotoVO = new ActivityPhotoVO();
				activityPhotoVO.setActivity_id(activity_id);
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("activityPhotoVO",activityPhotoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/activityPhoto/addActivityPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activityPhoto/addActivityPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("Update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer activity_photo_id = new Integer(req.getParameter("activity_photo_id").trim());
				Integer activity_id = new Integer(req.getParameter("activity_id").trim());
				
				
//				//取房型原始值
//				Integer room_type_id = 0;
//				
//				try {
//					RoomTypePicService roomTypePicService = new RoomTypePicService();
//					RoomTypePicVO roomTypePicVO = roomTypePicService.getOneRoomType(room_photo_id);
//					
//					room_type_id = roomTypePicVO.getRoom_type_id();
//				} catch (Exception e) {
//					errorMsgs.add("房型號碼錯誤");
//				}
//				// ========================================
				
				
				//修改照片
				InputStream is = null;
				byte[] room_pic = null;
				Part filePart = null ;
				try {
					filePart = req.getPart("room_type_pic");
					if (filePart != null) {
						is = filePart.getInputStream();
						room_pic = new byte[is.available()];
						is.read(room_pic);
						is.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ActivityPhotoVO activityPhotoVO = new ActivityPhotoVO();
				activityPhotoVO.setActivity_id(activity_id);
				activityPhotoVO.setActivity_photo_file(room_pic);
				activityPhotoVO.setActivity_photo_id(activity_photo_id);
				
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActivity_id(activity_id);

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("activityPhotoVO", activityPhotoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("activityVO",activityVO); // 資料庫update成功後,正確的的empVO物件,存入req
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				
				ActivityPhotoService activityPhotoService = new ActivityPhotoService();
				activityPhotoVO = activityPhotoService.updateActivityPhoto(activity_photo_id, activity_id, room_pic);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("activityPhotoVO",activityPhotoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				req.setAttribute("activityVO",activityVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/activityPhoto/listAllActivityPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/activityPhoto/listAllActivityPhoto.jsp");
				failureView.forward(req, res);
			}
		}
	}

}

package com.meal.controller;

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

import com.meal.model.MealService;
import com.meal.model.MealVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 10 * 1024 * 1024)
public class MealServlet extends HttpServlet {

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
				String str = req.getParameter("meal_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐點編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer meal_id = null;
				try {
					meal_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("餐點編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(meal_id);
				if (mealVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mealVO", mealVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/Meal/listOneMeal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/select_page.jsp");
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
				Integer meal_id = new Integer(req.getParameter("meal_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				MealService mealSvc = new MealService();
				MealVO mealVO = mealSvc.getOneMeal(meal_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("mealVO", mealVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/Meal/update_Meal_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/listAllMeal.jsp");
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

				Integer meal_id = null;
				try {
					meal_id = new Integer(req.getParameter("meal_id").trim());
				} catch (NumberFormatException e) {
					meal_id = 0;
					errorMsgs.add("餐點編號請填數字.");
				}

				Integer meal_category_id = null;
				try {
					meal_category_id = new Integer(req.getParameter("meal_category_id").trim());
				} catch (NumberFormatException e) {
					meal_category_id = 0;
					errorMsgs.add("類別編號請填數字.");
				}

				String meal_description = req.getParameter("meal_description");
				String meal_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (meal_description == null || meal_description.trim().length() == 0) {
					errorMsgs.add("餐點描述: 請勿空白");
				} else if (!meal_description.trim().matches(meal_descriptionReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點描述: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_price = null;
				try {
					meal_price = new Integer(req.getParameter("meal_price").trim());
				} catch (NumberFormatException e) {
					meal_price = 0;
					errorMsgs.add("餐點價格請填數字.");
				}

				String meal_name = req.getParameter("meal_name");
				String meal_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("餐點名稱: 請勿空白");
				} else if (!meal_name.trim().matches(meal_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_quantity = null;
				try {
					meal_quantity = new Integer(req.getParameter("meal_quantity").trim());
				} catch (NumberFormatException e) {
					meal_quantity = 0;
					errorMsgs.add("餐點數量請填數字.");
				}

				Boolean meal_status = null;
				try {
					meal_status = new Boolean(req.getParameter("meal_status").trim());
				} catch (NumberFormatException e) {
					meal_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}
				
				byte[] meal_photo = null;
				try {
					Part part = req.getPart("meal_photo");
					InputStream in = part.getInputStream();
					meal_photo = new byte[in.available()];
					in.read(meal_photo);
				} catch (Exception e) {
					meal_photo = null;
				}

				MealVO mealVO = new MealVO();
				mealVO.setMeal_id(meal_id);
				mealVO.setMeal_category_id(meal_category_id);
				mealVO.setMeal_description(meal_description);
				mealVO.setMeal_price(meal_price);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_quantity(meal_quantity);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_photo(meal_photo);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/update_Meal_input.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				MealService mealSvc = new MealService();
				mealVO = mealSvc.updateMeal(meal_id, meal_category_id, meal_description, meal_price, meal_name,
						meal_quantity, meal_status, meal_photo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mealVO", mealVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/Meal/listOneMeal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/update_Meal_input.jsp");
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
//String mealid = req.getParameter("mealid");
//				String mealidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mealid == null || mealid.trim().length() == 0) {
//					errorMsgs.add("餐點編號: 請勿空白");
//				} else if(!mealid.trim().matches(mealidReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("餐點編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }

				Integer meal_id = null;
				try {
					meal_id = new Integer(req.getParameter("meal_id").trim());
				} catch (NumberFormatException e) {
					meal_id = 0;
					errorMsgs.add("餐點編號請填數字.");
				}

				Integer meal_category_id = null;
				try {
					meal_category_id = new Integer(req.getParameter("meal_category_id").trim());
				} catch (NumberFormatException e) {
					meal_category_id = 0;
					errorMsgs.add("類別編號請填數字.");
				}

				String meal_description = req.getParameter("meal_description");
				String meal_descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (meal_description == null || meal_description.trim().length() == 0) {
					errorMsgs.add("餐點描述: 請勿空白");
				} else if (!meal_description.trim().matches(meal_descriptionReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點描述: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_price = null;
				try {
					meal_price = new Integer(req.getParameter("meal_price").trim());
				} catch (NumberFormatException e) {
					meal_price = 0;
					errorMsgs.add("餐點價格請填數字.");
				}

				String meal_name = req.getParameter("meal_name");
				String meal_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (meal_name == null || meal_name.trim().length() == 0) {
					errorMsgs.add("餐點名稱: 請勿空白");
				} else if (!meal_name.trim().matches(meal_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("餐點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer meal_quantity = null;
				try {
					meal_quantity = new Integer(req.getParameter("meal_quantity").trim());
				} catch (NumberFormatException e) {
					meal_quantity = 0;
					errorMsgs.add("餐點數量請填數字.");
				}

				Boolean meal_status = null;
				try {
					meal_status = new Boolean(req.getParameter("meal_status").trim());
				} catch (NumberFormatException e) {
					meal_status = true;
					errorMsgs.add("上下架狀態請勿空白.");
				}
				
				byte[] meal_photo = null;
				try {
					Part part = req.getPart("meal_photo");
					InputStream in = part.getInputStream();
					meal_photo = new byte[in.available()];
					in.read(meal_photo);
					in.close();
				} catch (Exception e) {
					meal_photo = null;
				}

//Integer deptno = new Integer(req.getParameter("deptno").trim());

				MealVO mealVO = new MealVO();
				mealVO.setMeal_id(meal_id);
				mealVO.setMeal_category_id(meal_category_id);
				mealVO.setMeal_description(meal_description);
				mealVO.setMeal_price(meal_price);
				mealVO.setMeal_name(meal_name);
				mealVO.setMeal_quantity(meal_quantity);
				mealVO.setMeal_status(meal_status);
				mealVO.setMeal_photo(meal_photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealVO", mealVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/addMeal.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				MealService mealSvc = new MealService();
				mealVO = mealSvc.addMeal(meal_id, meal_category_id, meal_description, meal_price, meal_name,
						meal_quantity, meal_status, meal_photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Meal/listAllMeal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/addMeal.jsp");
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
				Integer meal_id = new Integer(req.getParameter("meal_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				MealService mealSvc = new MealService();
				mealSvc.deleteMeal(meal_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Meal/listAllMeal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Meal/listAllMeal.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("gatecategory".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer meal_id = new Integer(req.getParameter("mealcategory"));

				/*************************** 2.開始查詢資料 ****************************************/
				MealService mealSvc = new MealService();
				MealVO mealVO = (MealVO) mealSvc.getMealCategory(meal_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("mealVO", mealVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/roomservice/service.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/roomservice/service.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}

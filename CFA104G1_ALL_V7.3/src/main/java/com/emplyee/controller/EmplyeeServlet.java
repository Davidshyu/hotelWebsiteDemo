package com.emplyee.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emplyee.model.EmplyeeService;
import com.emplyee.model.EmplyeeVO;

import empRolesFucnction.EmpRolesFunctionService;
import empRolesFucnction.EmpRolesFunctionVO;

public class EmplyeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String id = req.getParameter("id");

		/****** 員工登入 ******/
		if ("login".equals(action)) { // 來自signIn.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
								
				if (req.getParameter("empno") == null || req.getParameter("empno").trim().length() == 0) {
					errorMsgs.put("empno","員工編號請勿空白");
//					errorMsgs.add("員工編號請勿空白");
				}
				
				System.out.println(1 + req.getParameter("empno"));
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/login.jsp");
					failureView.forward(req, res);
					return;
				}
				
				System.out.println(2 + req.getParameter("empno"));
				
				Integer empno = new Integer(req.getParameter("empno"));
				
				System.out.println(3 + req.getParameter("empno"));

				String emp_password = req.getParameter("emp_password");
				if (emp_password == null || (emp_password.trim()).length() == 0) {
					errorMsgs.put("emp_password","請輸入密碼");
//					errorMsgs.add("請輸入密碼");
				}
				
				System.out.println(4 + req.getParameter("emp_password"));
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
				EmplyeeService empSvc = new EmplyeeService();
				EmplyeeVO emplyeeVO = empSvc.logIn(empno, emp_password);
				if (emplyeeVO == null) {
					errorMsgs.put("emp_login","員工編號或密碼錯誤，請重新輸入");
//					errorMsgs.add("員工編號或密碼錯誤，請重新輸入");
				}
				if (empSvc.getOneEmp(empno).getEmp_status() == false) {
					errorMsgs.put("emp_status","登入出現問題，請聯繫管理員");
//					errorMsgs.add("登入出現問題，請聯繫管理員");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2-1.查詢所有功能***************************/
//				EmpRolesFunctionService empRFSvc = new EmpRolesFunctionService();
//				List<Integer> list = empRFSvc.getAllFunctionID(empno);
//				EmpRolesFunctionVO empRFVO = new EmpRolesFunctionVO();
//				empRFVO.setEmplyeeVO(emplyeeVO);
//				empRFVO.setRoles_id(emplyeeVO.getRoles_id());
//				empRFVO.setFunctionList(list);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emplyeeVO", emplyeeVO); // 資料庫取出的empVO物件,存入req
				HttpSession session = req.getSession();
				session.setAttribute("staff", emplyeeVO);
				session.setAttribute("username", emplyeeVO.getEmp_name());
				System.out.println(5 + emplyeeVO.getEmp_name());
				session.setAttribute("emplyee", req.getParameter("empno"));
//				session.setAttribute("empRFVO", empRFVO);

				String url = "/back-end/admin/dashboard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("Exception","無法取得資料:" + e.getMessage());
//				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin/login.jsp");
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
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始查詢資料 ****************************************/
				EmplyeeService empSvc = new EmplyeeService();
				EmplyeeVO emplyeeVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("emplyeeVO", emplyeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emplyee/updateEmpInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/listAllEmp.jsp");
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
				Integer empno = new Integer(req.getParameter("empno").trim());

				Integer roles_id = new Integer(req.getParameter("roles_id").trim());

				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Boolean emp_status = new Boolean(req.getParameter("emp_status"));

				EmplyeeVO emplyeeVO = new EmplyeeVO();
				emplyeeVO.setEmpno(empno);
				emplyeeVO.setRoles_id(roles_id);
				emplyeeVO.setEmp_name(emp_name);
				emplyeeVO.setEmp_status(emp_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emplyeeVO", emplyeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/updateEmpInput.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmplyeeService empSvc = new EmplyeeService();
				emplyeeVO = empSvc.updateEmp(empno, roles_id, emp_name, emp_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emplyeeVO", emplyeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emplyee/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/updateEmpInput.jsp");
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
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始刪除資料 ***************************************/
				EmplyeeService empSvc = new EmplyeeService();
				empSvc.deleteEmp(empno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emplyee/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/listAllEmp.jsp");
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
							
				if (req.getParameter("empno") == null || req.getParameter("empno").trim().length() == 0) {
					errorMsgs.add("員工編號請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer roles_id = new Integer(req.getParameter("roles_id"));
				
				String emp_name = req.getParameter("emp_name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String emp_password = req.getParameter("emp_password").trim();
				if (emp_password == null || emp_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}

				String reemp_password = req.getParameter("reemp_password").trim();
				if (reemp_password == null || reemp_password.trim().length() == 0) {
					errorMsgs.add("密碼重複請勿空白");
				} else if (!reemp_password.equals(req.getParameter("emp_password"))) {
					errorMsgs.add("密碼與密碼重複不同");
				}
				
				Integer empno = new Integer (req.getParameter("empno"));
				
				EmplyeeVO emplyeeVO = new EmplyeeVO();
				emplyeeVO.setEmpno(empno);
				emplyeeVO.setRoles_id(roles_id);
				emplyeeVO.setEmp_password(emp_password);
				emplyeeVO.setEmp_name(emp_name);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emplyeeVO", emplyeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}


				/***************************
				 * 1-2.select帳號確認沒重複
				 ***************************************/
				EmplyeeService empSvc = new EmplyeeService();

				if (!empSvc.checkEmp(empno)) {
					errorMsgs.add("員工編號重複");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emplyeeVO", emplyeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				emplyeeVO = empSvc.addEmplyee(empno, roles_id, emp_name, emp_password);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emplyee/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emplyee/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		/** 員工登出 **/
		if ("logout".equals(action)) {
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/back-end/admin/index.jsp");
			return;
		}
	}
}

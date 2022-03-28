package com.activityOrder.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.activitySession.model.ActivitySessionDAO_interface;
import com.activitySession.model.ActivitySessionJDBCDAO;
import com.activitySession.model.ActivitySessionJNDIDAO;
import com.activitySession.model.ActivitySessionVO;
@WebServlet("/transOrder.do")
public class TransOrder extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		MemVO memVO = (MemVO) session.getAttribute("memVO");
//		int memId = memVO.getMemId();
//		request.setAttribute("memId", memId);
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("activity_session_id");
//		String max = request.getParameter("activity_max_part");
//		String total = request.getParameter("entered_total");
		ActivitySessionDAO_interface dao = new ActivitySessionJNDIDAO();
		ActivitySessionVO activitySessionVO = dao.findByPrimaryKey(Integer.parseInt(id));
		request.setAttribute("activitySessionVO", activitySessionVO);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/activityOrder/addActivityOrder.jsp");
		successView.forward(request, response);
	}

}

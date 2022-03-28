package com.activityOrder.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activityOrder.model.ActivityOrderDAO_interface;
import com.activityOrder.model.ActivityOrderJDBCDAO;
import com.activityOrder.model.ActivityOrderJNDIDAO;
import com.activityOrder.model.ActivityOrderVO;

@WebServlet("/transPay.do")
public class TransPay extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("activity_amount");
		ActivityOrderDAO_interface dao = new ActivityOrderJNDIDAO();
//		ActivityOrderVO activityOrderVO = dao.findByPrimaryKey(Integer.parseInt(amo));
//		request.setAttribute("activityOrderVO", activityOrderVO);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/activityOrder/orderPay.jsp");
		successView.forward(request, response);
	}

}

package com.activity.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activity.model.ActivityDAO_interface;
import com.activity.model.ActivityJNDIDAO;
import com.activity.model.ActivityVO;
@WebServlet("/pageTransition.do")
public class PageTransition extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("activityId");
		ActivityDAO_interface dao = new ActivityJNDIDAO();
		ActivityVO activityVO = dao.findByPrimaryKey(Integer.parseInt(id));
		System.out.println(activityVO.getActivity_id());
		request.setAttribute("activityVO", activityVO);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/activity/activityDetail.jsp");
		successView.forward(request, response);
	}

}

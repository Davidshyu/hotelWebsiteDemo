<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.activity.model.*"%>
    <%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //ActivityServlet.java (Controller) 存入req的activityVO物件 (包括幫忙取出的activityVO, 也包括輸入資料錯誤時的activityVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動資料 - listOneActivity.jsp</title>
</head>
<body>
<table>
	<tr><td>
		<h3>活動資料 - listOneActivity.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/activity/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<table>
		<tr>
			<th>活動編號</th>
			<th>活動類別編號</th>
			<th>活動名稱</th>
			<th>活動資訊</th>
			
		</tr>
			<tr>
			<td>${activityVO.activity_id}</td>
			<td>${activityVO.activity_category_id}</td>
			<td>${activityVO.activity_name}</td>
			<td>${activityVO.activity_info}</td>
</table>
</body>
</html>
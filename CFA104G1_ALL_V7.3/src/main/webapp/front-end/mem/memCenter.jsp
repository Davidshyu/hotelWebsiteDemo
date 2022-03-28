<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) session.getAttribute("user");
%>


<html>
<head>
<title>會員中心</title>
</head>
<body>
    <p>會員頭像</p>
    <img src="<%=request.getContextPath()%>/mem/mempic.do?mem_id=<%=memVO.getMem_id()%>" width="200" height="200">
    <br>
    <p><%=memVO.getMem_name()%></p>
    <p>${user.mem_name}</p>
    <br>
    <p>
    <c:if test="${user.mem_status == 1}">已啟用</c:if>
    <c:if test="${user.mem_status == 0}">未啟用</c:if>
    </p>   
    
    <c:if test="${user.mem_status == 0}">
    <a href="<%= request.getContextPath() %>/mem/mem.do?action=reverify&mem_email=<%=memVO.getMem_email()%>">重新寄驗證信</a>
    </c:if>

    

    <br>
	<a href="<%=request.getContextPath()%>/home.jsp">回首頁</a>
	
	<form data-bitwarden-watching="1" METHOD="post" ACTION="<%= request.getContextPath() %>/mem/mem.do"
		name="form1">
		<button type=submit name="action" value="logout">登出</button>
	</form>
	
	<a href="<%=request.getContextPath()%>/front-end/mem/memUpdateData.jsp">修改會員資料</a>
	<br>
	<a href="<%=request.getContextPath()%>/front-end/mem/memUpdatePwd.jsp">修改密碼</a>
	<br>
	<a href="<%=request.getContextPath()%>/front-end/mem/memUpdateAvator.jsp">修改大頭貼</a>
	
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員首頁</title>
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet"
      href=" https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css ">
    <link rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
    <style>
    #d1{
        margin-top: 5%;
        width: 60%;
    }
    #u1{
        width: 80%;
    }
    .list-group-item {
    width: 740px;
    height: 66px;
    text-align: left;
    line-height: 44px;
    margin-left:-20%;
    font-size:20px;
    }
    </style>
    
    <%@ include file= "/front-end/memFramework/include.file" %>
    
</head>

<body>
<%@ include file= "/front-end/memFramework/header.file" %>


<section class="pt-5 pb-5">
		<div class="container">
			<div class="row justify-content-center equal">
				<div id="d1" class="container">
        <ul id="u1" class="list-group">
            <li class="list-group-item list-group-item-warning">會員信箱: ${user.mem_email}</li>
            <li class="list-group-item list-group-item-warning">會員姓名: ${user.mem_name}</li>
            <li class="list-group-item list-group-item-warning">會員電話: ${user.mem_phone}</li>
            <li class="list-group-item list-group-item-warning">會員地址: ${user.mem_address}</li>
            <li class="list-group-item list-group-item-warning">會員狀態: 
            <c:if test="${user.mem_status == 1}">已啟用</c:if>
            <c:if test="${user.mem_status == 0}">未啟用</c:if>
            <c:if test="${user.mem_status == 0}">
            <a href="<%= request.getContextPath() %>/mem/mem.do?action=reverify&mem_email=<%=memVO.getMem_email()%>">重新寄驗證信</a>
            </c:if>
            </li>
          </ul>
    </div>
			</div>
		</div>
	</section>
    
    
     <%@ include file= "/front-end/memFramework/footer.file" %>
</body>

</html>
<%@page import="com.roomOrderList.model.RoomOrderListVO"%>
<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomOrder.model.*"%>

<%
RoomOrderListVO roomOrderListVO = (RoomOrderListVO) request.getAttribute("roomOrderListVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房間資料修改 - update_room.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 550px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  input{
  	margin-right: 250px;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">住宿訂單修改</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/roomOrderList/listAllOrderList.jsp">住宿訂單管理</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrderList/roomOrderList.do" name="form1">
<table class="table">
	<tr>
		<td>訂單明細編號:<font color=red><b>*</b></font></td>
		<td><%=roomOrderListVO.getRoom_order_list_id()%></td>
	</tr>
	<tr>
		<td>住宿訂單編號:<font color=red><b>*</b></font></td>
		<td><%=roomOrderListVO.getRoom_order_id()%></td>
	</tr>
	<tr>
		<td>特殊需求:</td>
		<td><input type="TEXT" name="special_req" size="20 "	value="<%=(roomOrderListVO.getSpecial_req() == null)? " " : roomOrderListVO.getSpecial_req()%>" /></td>
	</tr>
	
	

	

</table>
<br>
<input type="hidden" name="action" value="backEndUpdate">
<input type="hidden" name="room_order_list_id" value="<%=roomOrderListVO.getRoom_order_list_id()%>">
<input type="submit" value="送出修改"></FORM>
		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>

</body>
</html>
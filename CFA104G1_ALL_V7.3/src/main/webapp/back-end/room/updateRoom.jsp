<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.roomType.model.*"%>
<%@page import="java.util.List"%>

<%
  RoomVO roomVO = (RoomVO) request.getAttribute("roomVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%
RoomTypeService roomTypeService = new RoomTypeService();
List<RoomTypeVO> list = roomTypeService.getAll();
pageContext.setAttribute("list", list);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>房間資料修改</title>
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
	width: 450px;
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
  select {
  width: 250px;
}
input[type='number']{
    width: 250px;
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
            <h1 class="m-0">房間資料修改</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/room/listAllRoom.jsp">房間管理</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="room.do" name="form1">
<table class="table">
	<tr>
		<td>房間編號:<font color=red><b>*</b></font></td>
		<td><%=roomVO.getRoom_id()%></td>
	</tr>
	<tr>
		<td>房型名稱:</td>
		<td><select name="room_type_id">
					<c:forEach var="roomTypeVO" items="${list}">
						
						<option value="${roomTypeVO.room_type_id}">${roomTypeVO.room_type_name}</option>
					</c:forEach></td>
	</tr>
	<tr>
		<td>房間床位:</td>
		<td><input type="number" name="qtyofbeds" 	value="<%=roomVO.getQtyofbeds()%>" /></td>
	</tr>
	<tr>
		<td>住客名單:</td>
		<td><input type="TEXT" name="room_guest_name" size="28"	value="<%=roomVO.getRoom_guest_name()%>" /></td>
	</tr>
	<tr>
		<td>上架狀態:</td>
		<td><select name="room_sale_status">
					<option value="true" ${(roomVO.room_sale_status== true)? 'selected':'' }>上架</option>
					<option value="false" ${(roomVO.room_sale_status== false)? 'selected':'' }>下架</option>
				</select></td>
	</tr>
	<tr>
		<td>房間狀態:</td>
		<td><select name="room_status">
					<option value="0" ${(roomVO.room_status== 0)? 'selected':'' }>未入住</option>
					<option value="1" ${(roomVO.room_status== 1)? 'selected':'' }>已入住</option>
					<option value="2" ${(roomVO.room_status== 2)? 'selected':'' }>待清潔</option>
				</select></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="room_id" value="<%=roomVO.getRoom_id()%>">
<input type="submit" value="送出修改"></FORM>

		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>
        
</body>
</html>
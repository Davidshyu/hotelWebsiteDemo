<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomSchedule.model.*"%>

<%
	RoomscheduleService roomscheduleService = new RoomscheduleService();
	List<RoomScheduleVO> list = roomscheduleService.getall();
	pageContext.setAttribute("list", list);

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>listAllRommSchdule.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>

</head>

<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">所有房間預定表</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/roomSchedule/showAllRoomSchedule.jsp">所有房間預定表</a></li>
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
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table class="table table-striped">
		<tr>
			<th>房型預訂編號</th>
			<th>房型編號</th>
			<th>房型預訂期間</th>
			<th>房間數量</th>
			<th>房間已定數量</th>
			<th>修改</th>
			<th>刪除</th>
	
		</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="roomScheduleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${roomScheduleVO.room_schedule_id}</td>
				<td>${roomScheduleVO.room_type_id}</td>
				<td>${roomScheduleVO.room_schedule_date}</td>
				<td>${roomScheduleVO.room_amount}</td>
				<td>${roomScheduleVO.room_rsv_booked}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/oomSchedule/roomSchedule.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="room_order_list_id"
							value="${roomSceduleVO.room_schedule_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomSchedule/roomSchedule.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="room_order_list_id"  value="${roomSceduleVO.room_schedule_id}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</td>
		</tr>
	</c:forEach>
	</table>
	<%@ include file="page2.file" %>	
		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

	
	
</body>
</html>
<%@page import="com.room.model.RoomVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomOrder.model.*"%>

<%
RoomOrderVO roomOrderVo = (RoomOrderVO) request.getAttribute("roomOrderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單資料修改</title>
<%@ include file= "/back-end/framework/include.file" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
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
  select {
  width: 190px;
}
</style>

</head>
<body bgcolor='white'>
<body class="sb-nav-fixed">
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">住宿訂單管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/roomOrder/listAllOrder.jsp">住宿訂單管理</a></li>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder/RoomOrderServlet.do" name="form1">
<table class="table">
	<tr>
		<td>住房訂單編號:<font color=red><b>*</b></font></td>
		<td><%=roomOrderVo.getRoom_order_id()%></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=roomOrderVo.getMem_id()%></td>
	</tr>
	<tr>
		<td>訂單日期:</td>
		<td><%=roomOrderVo.getOrder_date()%></td>
	</tr>
	<tr>
		<td>訂單總金額:</td>
		<td><%=roomOrderVo.getRoom_charge()%></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><select name="room_order_status">
					<option value="0" ${(roomOrderVO.room_order_status== 0)? 'selected':'' }>訂單取消</option>
					<option value="1" ${(roomOrderVO.room_order_status== 1)? 'selected':'' }>未入住</option>
					<option value="2" ${(roomOrderVO.room_order_status== 2)? 'selected':'' }>已入住</option>
					<option value="3" ${(roomOrderVO.room_order_status== 3)? 'selected':'' }>已結單</option>
		</select></td>		
		

	</tr>
	<tr>
		<td>住宿評分:</td>
		<td><input type="number" name="room_review" size="20 "	
		value="<%=(roomOrderVo == null) ? "0" : roomOrderVo.getRoom_review()%>" /></td>
	</tr>
		<tr>
		<td>住房日期:</td>
		<td><input name=arrival_date id="f_date1" type="text"></td>
	</tr>
		<tr>
		<td>退房日期:</td>
		<td><input name="departure_date" id="f_date2" type="text"></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="room_charge" value="<%=roomOrderVo.getRoom_charge()%>">
<input type="hidden" name="order_date" value="<%=roomOrderVo.getOrder_date()%>">
<input type="hidden" name="mem_id" value="<%=roomOrderVo.getMem_id()%>">
<input type="hidden" name="room_order_id" value="<%=roomOrderVo.getRoom_order_id()%>">
<input type="submit" value="送出修改"></FORM>
        
		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>
 <script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>
        
<script>
$.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=roomOrderVo.getArrival_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               new Date(), // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
$.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=roomOrderVo.getDeparture_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               new Date(), // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>    
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.roomSchedule.model.*"%>
<%@ page import="com.roomOrderList.model.*"%>

<%
%>

<html>
<head>
 <!-- Required meta tags -->
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增住宿日期 TEST</title>
<%@ include file= "/front-end/framework/include.file" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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
	width: 650px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 2px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body bgcolor='white'>
<%@ include file= "/front-end/framework/header.file" %>
     <div class="section-header">
         <h2>預約訂房</h2>
     </div>
     <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
          <div class=" justify-content-center mb-md-0 fs-1">
           Welcome to YAYOI Hot spring resort
          </div>
        </header>
	<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red"></font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		</div>
		<div class = "container" style="min-height: 33vh;">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/roomSchedule/roomSchedule.do" name="form1" class="row align-items-center">
	
		  <div class="col-3">
		    <label for="arrival_date" class="fw-bold form-label">入住日期</label>
		    <input name="arrival_date" id="arrival_date" class="form-control" type="text">
		  </div>
		  <div class="col-3">
		    <label for="departure_date" class="fw-bold form-label">退房日期</label>
		    <input name="departure_date" id="departure_date" class="form-control" type="text"  value= "${roomOrderVO.departure_date}">
		  </div>
		  <div class="col-2">
		    <label for="totalrooms" class="fw-bold form-label">房間數量</label>
		    <input name="totalrooms" id="totalrooms" class="form-control" type="text" value= "${roomOrderVO.totalrooms}">
		  </div>
		  <div class="col-2">
		    <label for="tnum_of_people" class="fw-bold form-label">人數</label>
		    <input name="num_of_people" id="num_of_people" class="form-control" type="text" value= "${roomOrderVO.num_of_people}">
		  </div>
		  <div class="col-2 justify-content-center">
		    <button type="submit" class="btn btn-warning">收尋空房</button>
		  </div>
		
		<input type="hidden" name="action" value="searchEmptyRoomByDate">
		</FORM>
		
		</div>
		
	
<%@ include file= "/front-end/framework/footer.file" %>
<%@ include file= "/resources/datetimepicker/dateTimepickerSettingForSeearch.file" %>
</body>


</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activityOrder.model.*"%>
<%@ page import="com.activityCategory.model.*"%>
<% ActivityOrderVO activityOrderVO = (ActivityOrderVO) request.getAttribute("activityOrderVO"); %>
<% ActivityCategoryVO activityCategoryVO = (ActivityCategoryVO) request.getAttribute("activityCategoryVO"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動訂單新增</title>
<%@ include file= "/back-end/framework/include.file" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.css" />
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style></head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動類別新增</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityOrder/listAllActivityOrder.jsp">活動訂單列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityOrder/select_page.jsp">活動訂單查詢</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="col-lg-12">
 		<div class="card mb-4 a1">
        <div>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>	
		<div class="card-body">


		<%-- <jsp:useBean id="MemService" scope="page" class="com.mem.model.MemService" /> --%>
		<jsp:useBean id="activitySessionService" scope="page" class="com.activitySession.model.ActivitySessionService" />

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activityOrder.do">
		<table>
		<tr>
			<td>會員編號:</td>
			<td><input type="text" name="mem_id" /></td>  		
<%-- 			value="<%= (activityOrderVO==null)? "" : activityOrderVO.getMem_id()%>" --%>
		</tr>
		<tr>
		<td>選擇活動場次編號:</td>
  		<td><select name="activity_session_id" id="activity_session_id">
	  		<c:forEach var="activitySessionVO" items="${activitySessionService.allActivitySession}" > <!-- servlet P.231 -->
	 	 		<option value="${activitySessionVO.activity_session_id}" ${(activityOrderVO.activity_session_id==activitySessionVO.activity_session_id)? 'selected':'' } >${activitySessionVO.activity_session_name}
	  		</c:forEach>
  		</select></td>
  		</tr>
  		<tr>
  		<td>訂單日期:</td>
        <td><input name="order_time" id="order_time" readOnly="true"/></td>
  		</tr>
  		<tr>
		<td>報名人數:</td>
		<td><input type='text' id='entered_number'  name='entered_number'></td>
		</tr> 
 		<tr>
 		<td>活動日期起:</td>
<%--         <td><input type="text" name="activity_started" value="${activitySessionVO.activity_started}" readOnly="true"></td> --%>
        <td><input type="text" name="activity_started" id="f_date3"></td>
 		</tr>
  		<tr>
 		<td>活動日期迄:</td>
<%--         <td><input type="text" name="activity_end" value="${activitySessionVO.activity_end}" readOnly="true"></td> --%>
        <td><input type="text" name="activity_end"  id="f_date4"></td>
 		</tr>
 		<tr>
		<td>活動定價:</td>
		<td><input type="text" name="order_amount" /></td>
		</tr>
 		<tr>
 		<td>訂單狀態:</td>
        <td><input type="text" name="order_state" value="0" readOnly="true"/></td>
        </tr>
        <tr>
 		<td>退款狀態:</td>
        <td><input type="text" name="refund_state" value="0" readOnly="true"/></td>
        </tr>
        <tr>
 		<td>訂單備註:</td>
        <td><textarea rows="8" name="order_memo" maxlength="50" rows="5" cols="80"></textarea></td>
        </tr>
        </table>
        <input type="hidden" name="action" value="insert">
        <button type="submit">送出</button>
        </FORM>
        
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
<script src="<%=request.getContextPath()%>/resources/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Date activity_started = null;
  try {
	  activity_started = activityOrderVO.getActivity_started();
   } catch (Exception e) {
	   activity_started = new java.sql.Date(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Date activity_end = null;
  try {
	  activity_end = activityOrderVO.getActivity_end();
   } catch (Exception e) {
	   activity_end = new java.sql.Date(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Date order_time = null;
  try {
	  order_time = activityOrderVO.getOrder_time();
   } catch (Exception e) {
	   order_time = new java.sql.Date(System.currentTimeMillis());
   }
  %>
 <script>
 $('#f_date3').datetimepicker({
     format:'Y-m-d',         //format:'Y-m-d H:i:s',
     value: '${param.activity_started}',
     onShow:function(){
  	   this.setOptions({
  	    maxDate:$('#f_date4').val()?$('#f_date4').val():false,
  	   })
  	  },
  	  timepicker:false  
  });
  $('#f_date4').datetimepicker({
  	  format:'Y-m-d',
  	  value: '${param.activity_end}',
  	  onShow:function(){
  	   this.setOptions({
  	    minDate:$('#f_date3').val()?$('#f_date3').val():false
  	   })
  	  },
  	  timepicker:false
  	 });
		$('#order_time').val(new Date().toISOString().substring(0,10));
  </script>
</html>
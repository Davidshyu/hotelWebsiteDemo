<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activitySession.model.*"%>
<%
ActivitySessionVO activitySessionVO = (ActivitySessionVO) request.getAttribute("activitySessionVO"); //ActivitySessionServlet.java (Controller) 存入req的activitySessionVO物件 (包括幫忙取出的activitySessionVO, 也包括輸入資料錯誤時的activitySessionVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動場次資料修改</title>
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
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動場次資料修改</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activitySession/listAllActivitySession.jsp">活動場次列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activitySession/select_page.jsp">活動場次查詢</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="col-lg-12 p-2">
			<div>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>


			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/activitySession.do">
				<table>
					<tr>
						<td>活動場次編號:</td>
						<td>${activitySessionVO.activity_session_id}</td>
					</tr>
					<jsp:useBean id="activityService" scope="page"
						class="com.activity.model.ActivityService" />
					<tr>
						<td>活動編號:</td>
						<td><select name="activity_id">
								<c:forEach var="activityVO" items="${activityService.allActivity}">
									<!-- servlet P.231 -->
									<option value="${activityVO.activity_id}"
										${(activitySessionVO.activity_id==activityVO.activity_id)? 'selected':'' }>${activityVO.activity_name}
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>活動場次名稱:</td>
						<td><input type="TEXT" name="activity_session_name"
							maxlength="20" value="${activitySessionVO.activity_session_name}" /></td>
					</tr>
					<tr>
						<td>報名開始日期:</td>
						<td><input name="entered_started" id="f_date1" type="text"
							value="${activitySessionVO.entered_started}"></td>
					</tr>
					<tr>
						<td>報名截止日期:</td>
						<td><input name="entered_end" id="f_date2" type="text"
							value="${activitySessionVO.entered_end}"></td>
					</tr>
					<tr>
						<td>活動日期起:</td>
						<td><input name="activity_started" id="f_date3" type="text"
							value="${activitySessionVO.activity_started}"></td>
					</tr>
					<tr>
						<td>活動日期迄:</td>
						<td><input name="activity_end" id="f_date4" type="text"
							value="${activitySessionVO.activity_end}"></td>
					</tr>
					<tr>
						<td>活動定價:</td>
						<td><input type="text" name="activity_price"
							value="<%=(activitySessionVO == null) ? "" : activitySessionVO.getActivity_price()%>" /></td>
					</tr>
					<tr>
						<td><label for='activity_state' class='form-label'>活動狀態</label></td>
						<td><div class='input-group mb-3'>
								<input type='number' class='form-control' id='activity_state'
									min='0' max='4' autocomplete='off' name='activity_state'
									value='<%=(activitySessionVO == null) ? "" : activitySessionVO.getActivity_state()%>'>
								<span class='input-group-text'>(0:正常1:額滿2:取消3:未開放報名)</span>
							</div></td>
					</tr>
					<tr>
						<td>狀態備註:</td>
						<td><textarea name="status_note" maxlength="50" rows="5" cols="45"></textarea></td>
					</tr>
					<tr>
						<td><label for='activity_max_part' class='form-label'>活動人數上限</label></td>
						<td><div class='input-group mb-3'>
								<input type='number' class='form-control' id='activity_max_part'
									min='15' max='30' autocomplete='off' name='activity_max_part'
									value='<%=(activitySessionVO == null) ? "" : activitySessionVO.getActivity_max_part()%>'>
								<span class='input-group-text'>人</span>
							</div></td>
					</tr>
					<tr>
						<td><label for='activity_min_part' class='form-label'>活動人數下限</label></td>
						<td><div class='input-group mb-3'>
								<input type='number' class='form-control' id='activity_min_part'
									min='5' max='15' autocomplete='off' name='activity_min_part'
									value='<%=(activitySessionVO == null) ? "" : activitySessionVO.getActivity_min_part()%>'>
								<span class='input-group-text'>人</span>
							</div></td>
					</tr>
					<tr>
						<td>報名總數:</td>
						<td><input type="text" name="entered_total" value="0" readOnly="true" /></td>
					</tr>
					<tr>
						<td><label for='product_status' class='form-label'>商品狀態</label></td>
						<td><div class='input-group mb-3'>
								<input type='number' class='form-control' id='product_status'
									min='0' max='1' autocomplete='off' name='product_status'
									value='<%=(activitySessionVO == null) ? "" : activitySessionVO.getProduct_status()%>'>
								<span class='input-group-text'>(0:下架 1:上架)</span>
							</div></td>
					</tr>
				</table>
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="activity_session_id"
					value="${activitySessionVO.activity_session_id}">
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
java.sql.Date entered_started = null;
try {
	entered_started = activitySessionVO.getEntered_started();
} catch (Exception e) {
	entered_started = new java.sql.Date(System.currentTimeMillis());
}
%>
<%
java.sql.Date entered_end = null;
try {
	entered_end = activitySessionVO.getEntered_end();
} catch (Exception e) {
	entered_end = new java.sql.Date(System.currentTimeMillis());
}
%>
<%
java.sql.Date activity_started = null;
try {
	activity_started = activitySessionVO.getActivity_started();
} catch (Exception e) {
	activity_started = new java.sql.Date(System.currentTimeMillis());
}
%>
<%
java.sql.Date activity_end = null;
try {
	activity_end = activitySessionVO.getActivity_end();
} catch (Exception e) {
	activity_end = new java.sql.Date(System.currentTimeMillis());
}
%>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '${param.entered_started}',
		onShow : function() {
			this.setOptions({
				maxDate : $('#f_date2').val() ? $('#f_date2').val() : false

			})
		},
		timepicker : false
	});
	$('#f_date2').datetimepicker({
		format : 'Y-m-d',
		value : '${param.entered_end}',
		onShow : function() {
			this.setOptions({
				minDate : $('#f_date1').val() ? $('#f_date1').val() : false
			})
		},
		timepicker : false
	});
	$.datetimepicker.setLocale('zh');
	$('#f_date3').datetimepicker({
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '${param.activity_started}',
		onShow : function() {
			this.setOptions({
				maxDate : $('#f_date4').val() ? $('#f_date4').val() : false,
				minDate : $('#f_date2').val() ? $('#f_date2').val() : false
			})
		},
		timepicker : false
	});
	$('#f_date4').datetimepicker({
		format : 'Y-m-d',
		value : '${param.activity_end}',
		onShow : function() {
			this.setOptions({
				minDate : $('#f_date3').val() ? $('#f_date3').val() : false
			})
		},
		timepicker : false
	});
</script>

</html>
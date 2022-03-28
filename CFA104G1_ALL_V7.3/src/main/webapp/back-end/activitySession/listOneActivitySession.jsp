<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.activitySession.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
ActivitySessionVO activitySessionVO = (ActivitySessionVO) request.getAttribute("activitySessionVO"); //ActivitySessionServlet.java (Controller) 存入req的activitySessionVO物件 (包括幫忙取出的activitySessionVO, 也包括輸入資料錯誤時的activitySessionVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動場次資料 - listOneActivitySession.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動場次資料</h1>
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

	<table class="table">
		<tr>
			<th>活動場次編號</th>
			<th>活動編號</th>
			<th>活動場次名稱</th>
			<th>報名開始日期</th>
			<th>報名截止日期</th>
			<th>活動日期起</th>
			<th>活動日期迄</th>
			<th>活動定價</th>
			<th>活動狀態</th>
			<th>狀態備註</th>
			<th>活動人數上限</th>
			<th>活動人數下限</th>
			<th>報名總數</th>
			<th>商品狀態</th>
		</tr>
		<tr>
			<td>${activitySessionVO.activity_session_id}</td>
			<td>${activitySessionVO.activity_id}</td>
			<td>${activitySessionVO.activity_session_name}</td>
			<td>${activitySessionVO.entered_started}</td>
			<td>${activitySessionVO.entered_end}</td>
			<td>${activitySessionVO.activity_started}</td>
			<td>${activitySessionVO.activity_end}</td>
			<td>${activitySessionVO.activity_price}</td>
			<td>${activitySessionVO.activity_state}</td>
			<td>${activitySessionVO.status_note}</td>
			<td>${activitySessionVO.activity_max_part}</td>
			<td>${activitySessionVO.activity_min_part}</td>
			<td>${activitySessionVO.entered_total}</td>
			<td>${activitySessionVO.product_status}</td>
		</tr>
	</table>
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
</body>
</html>
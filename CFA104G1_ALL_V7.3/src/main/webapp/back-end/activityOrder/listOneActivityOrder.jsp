<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.activityOrder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
ActivityOrderVO activityOrderVO = (ActivityOrderVO) request.getAttribute("activityOrderVO"); //ActivityOrderServlet.java (Controller) 存入req的activityOrderVO物件 (包括幫忙取出的activityOrderVO, 也包括輸入資料錯誤時的activityOrderVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動訂單資料</title>
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

		<table>
				<tr>
					<th>活動訂單編號</th>
					<th>會員編號</th>
					<th>活動場次編號</th>
					<th>訂單日期</th>
					<th>報名人數</th>
					<th>活動日期起</th>
					<th>活動日期迄</th>
					<th>訂單金額</th>
					<th>訂單狀態</th>
					<th>退款狀態</th>
					<th>訂單備註</th>
					</tr>
					<tr>
					<td>${activityOrderVO.activity_order_id}</td>
					<td>${activityOrderVO.mem_id}</td>
					<td>${activityOrderVO.activity_session_id}</td>
					<td>${activityOrderVO.order_time}</td>
					<td>${activityOrderVO.entered_number}</td>
					<td>${activityOrderVO.activity_started}</td>
					<td>${activityOrderVO.activity_end}</td>
					<td>${activityOrderVO.order_amount}</td>
					<td>${activityOrderVO.order_state}</td>
					<td>${activityOrderVO.refund_state}</td>
					<td>${activityOrderVO.order_memo}</td>
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
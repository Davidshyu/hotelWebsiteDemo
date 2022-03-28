<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
ActivityOrderService activityOrderService = new ActivityOrderService();
List<ActivityOrderVO> list = activityOrderService.getAllActivityOrder();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有活動訂單資料</title>
<%@ include file= "/back-end/framework/include.file" %>
<%@ include file= "/back-end/framework/includeTableCss.file" %>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動訂單資料</h1>
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

		<div class="card mb-4 a1">
			<div class="card-header">
				<i class="fas fa-table me-1"></i>活動訂單
			</div>
			<div class="card-body">
				<a class="b1 btn btn-outline-secondary" href="<%= request.getContextPath() %>/back-end/activityOrder/addActivityOrder.jsp" role="button">新增活動訂單</a>

				<table id="datatablesSimple" class="table table-bordered table-hover">
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
							<th>修改</th>
							<th>刪除</th>
						</tr>
						<c:forEach var="activityOrderVO" items="${list}">
						
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
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/activityOrder.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> 
									<input type="hidden" name="activity_order_id" value="${activityOrderVO.activity_order_id}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/activityOrder.do"
									style="margin-bottom: 0px;">
									<input type="hidden" name="activity_order_id" value="${activityOrderVO.activity_order_id}">
									<input type="hidden" name="action" value="delete">
									<input type="submit" value="刪除"> 
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
<%@ include file= "/back-end/framework/includeTableJs.file" %>

</body>
</html>
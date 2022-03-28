<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityOrder.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) session.getAttribute("user");
%>

<%
ActivityOrderService activityOrderService = new ActivityOrderService();
List<ActivityOrderVO> list = activityOrderService.getActivityOrderByMemId(memVO.getMem_id());
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看活動訂單</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet" />
<style>
button, input {
	background-color: rgb(167, 243, 227);
	border-radius: 20em;
}

#big {
	width: 90%;
	padding: auto;
}
</style>
<%@ include file= "/front-end/memFramework/include.file" %>
</head>
<body>
<%@ include file= "/front-end/memFramework/header.file" %>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">查看會員活動訂單</h1>
				<div class="card mb-4">
					<div class="card-header">
					</div>
					<div class="card-body">


						<table id="datatablesSimple">
						  <thead>
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
							</thead>
							<tbody>
							<jsp:useBean id="ActivityOrderService" scope="page" class="com.activityOrder.model.ActivityOrderService" />
							<c:forEach var="activityOrderVO" items="${list}">
							
							<tr>
								<td>${activityOrderVO.activity_order_id}</td>
								<td>${user.mem_id}</td>
								<td>${activityOrderVO.activity_session_id}</td>
								<td>${activityOrderVO.order_time}</td>
								<td>${activityOrderVO.entered_number}</td>
								<td>${activityOrderVO.activity_started}</td>
								<td>${activityOrderVO.activity_end}</td>
								<td>${activityOrderVO.order_amount}</td>
								<c:set var="order_state" scope="session" value="${activityOrderVO.order_state}"/>
															<c:choose>
																    <c:when test="${order_state == 0}">
																       <td>未繳費</td>
																    </c:when>
																    <c:when test="${order_state == 1}">
																       <td>已繳費</td>
																    </c:when>
																    <c:when test="${order_state == 2}">
																       <td>取消訂單</td>
																    </c:when>
																    <c:when test="${order_state == 3}">
																       <td>完成訂單</td>
																    </c:when>
																    <c:otherwise>
																        <td>ERROR!</td>
																    </c:otherwise>
																</c:choose>
								<c:set var="refund_state" scope="session" value="${activityOrderVO.refund_state}"/>
															<c:choose>
																    <c:when test="${refund_state == 0}">
																       <td>無退款</td>
																    </c:when>
																    <c:when test="${refund_state == 1}">
																       <td>退款中</td>
																    </c:when>
																    <c:when test="${refund_state == 2}">
																       <td>完成退款</td>
																    </c:when>
																    <c:otherwise>
																        <td>ERROR!</td>
																    </c:otherwise>
																</c:choose>
								<td>${activityOrderVO.order_memo}</td>
							</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>
<!-- Footer Section Start -->
<%@ include file= "/front-end/memFramework/footer.file" %>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script>
        window.addEventListener('DOMContentLoaded', event => {
            // Simple-DataTables
            // https://github.com/fiduswriter/Simple-DataTables/wiki

            const datatablesSimple = document.getElementById('datatablesSimple');
            if (datatablesSimple) {
                new simpleDatatables.DataTable(datatablesSimple);
            }
        });
    </script>
</body>
</html>
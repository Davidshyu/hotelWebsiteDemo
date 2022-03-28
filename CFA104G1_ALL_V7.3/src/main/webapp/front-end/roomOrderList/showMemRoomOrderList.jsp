<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomOrderList.model.*"%>

<%

%>
<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomType.model.RoomTypeService" />

<html>
<head>
<title>查看訂單明細</title>
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
	padding: auto;
}
th{
	font-size: 15px;
	text-align: center;
}
</style>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">住宿訂單明細</h1>
				<div class="card mb-4">
					<div class="card-header">
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>訂單明細編號</th>
									<th>住宿訂單編號</th>
									<th>房間編號</th>
									<th>房型名稱</th>
									<th>人數</th>
									<th>房間價格</th>
									<th>服務訂單編號</th>
									<th>特殊要求</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="roomOrderListVO" items="${list}">
									<tr>
										<td>${roomOrderListVO.room_order_list_id}</td>
										<td>${roomOrderListVO.room_order_id}</td>
										<td>${roomOrderListVO.room_id}</td>
										<td><c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
							                    <c:if test="${roomOrderListVO.room_type_id==roomTypeVO.room_type_id}">
								                    ${roomTypeVO.room_type_name}
							                    </c:if>
							                </c:forEach></td>
										<td>${roomOrderListVO.number_of_people}</td>
										<td>${roomOrderListVO.room_price}</td>
										<td>${roomOrderListVO.service_order_id}</td>
										<td>${roomOrderListVO.special_req}</td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>

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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomOrder.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
RoomOrderService roomOrderService = new RoomOrderService();
List<RoomOrderVO> list = roomOrderService.getall();
pageContext.setAttribute("list", list);
%>

<%
MemVO memVO = (MemVO)session.getAttribute("user");
%>

<html>
<head>
<title>查看會員訂單</title>
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
<body class="sb-nav-fixed">
<%@ include file= "/front-end/memFramework/header.file" %>
 
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid px-4" id="big">
				<h1 class="mt-4" style="text-align: center;">查看會員訂單</h1>
				<div class="card mb-4">
					<div class="card-header">
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>住房訂單編號</th>
									<th>會員</th>
									<th>訂單日期</th>
									<th>訂單狀態</th>
									<th>訂單總金額</th>
									<th>住房日期</th>
									<th>退房日期</th>
									<th>住宿評分</th>
									<th>查看訂單明細</th>
									
								
								</tr>
							</thead>

							<tbody>
							<jsp:useBean id="roomOrderSvc" scope="page" class="com.roomOrder.model.RoomOrderService" />
								<c:forEach var="roomOrderVO" items="${roomOrderSvc.getRoomOrderByMemId(user.mem_id)}">
									<tr>
										<td>${roomOrderVO.room_order_id}</td>
										<td>${user.mem_name}</td>
										<td>${roomOrderVO.order_date}</td>
										
										<c:set var="order_status" scope="session" value="${roomOrderVO.room_order_status}"/>
										<c:choose>
											    <c:when test="${order_status == 0}">
											       <td>訂單取消</td>
											    </c:when>
											    <c:when test="${order_status == 1}">
											       <td>未入住</td>
											    </c:when>
											    <c:when test="${order_status == 2}">
											       <td>已入住</td>
											    </c:when>
											    <c:when test="${order_status == 3}">
											       <td>已結單</td>
											    </c:when>
											    <c:otherwise>
											        <td>你他媽亂寫阿!</td>
											    </c:otherwise>
											</c:choose>
										
										
										<td>${roomOrderVO.room_charge}</td>
										<td>${roomOrderVO.arrival_date}</td>
										<td>${roomOrderVO.departure_date}</td>
										<td>
										    <c:if test="${roomOrderVO.room_order_status==3 && roomOrderVO.room_review ==0}">
										      <form METHOD="post" ACTION="<%=request.getContextPath()%>/roomOrder/roomOrder.do">
										        <select class="form-select" aria-label="Default select example" name="room_review" id="room_review">
													  <option selected>請幫忙評分</option >
													  <option value="1">1</option>
													  <option value="2">2</option>
													  <option value="4">4</option>
													  <option value="5">5</option>
													  <option value="6">6</option>
													  <option value="7">7</option>
													  <option value="8">8</option>
													  <option value="9">9</option>
													  <option value="10">10</option>												  
											    </select>
											    <input type="hidden" name="action" value="saveRoomReview">
											    <input type="hidden" name="room_order_id" value="${roomOrderVO.room_order_id}">
											    <button type="submit" class="btn btn-primary">Submit</button>
										      </form>
											</c:if>
											<c:if test="${roomOrderVO.room_review!=0}">
										      ${roomOrderVO.room_review}
											</c:if>
											<c:if test="${roomOrderVO.room_review==0}">

											</c:if>											
										</td>
										
										<td>
										<A HREF="javascript:presses${roomOrderVO.room_order_id}()" class="btn btn-outline-secondary">查看詳情</a>
										
											 <script>
										     function presses${roomOrderVO.room_order_id}(){
										     document.open("<%=request.getContextPath()%>/roomOrderList/roomOrderList.do?room_order_id=${roomOrderVO.room_order_id}&action=getRoomOrderListByRoomOrderid", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
										     }
										  
										    </script>
										
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>
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
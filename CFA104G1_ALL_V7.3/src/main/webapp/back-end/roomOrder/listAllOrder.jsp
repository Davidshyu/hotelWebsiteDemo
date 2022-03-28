<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomOrder.model.*"%>

<%
RoomOrderService roomOrderService = new RoomOrderService();
List<RoomOrderVO> list = roomOrderService.getall();
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>住宿訂單管理</title>
<%@ include file= "/back-end/framework/include.file" %>

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
</head>
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
				<div class="card mb-4">
					<div class="card-header">
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>住房訂單編號</th>
									<th>會員名稱</th>
									<th>訂單日期</th>
									<th>訂單狀態</th>
									<th>訂單總金額</th>
									<th>住宿評分</th>
									<th>住房日期</th>
									<th>退房日期</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="roomOrderVO" items="${list}">
									<tr>
										<td>${roomOrderVO.room_order_id}</td>
										<td><c:forEach var="memVO" items="${memSvc.all}">
							                    <c:if test="${roomOrderVO.mem_id==memVO.mem_id}">
								                    ${memVO.mem_name}
							                    </c:if>
							                </c:forEach></td>
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
										<td>${roomOrderVO.room_review}</td>
										<td>${roomOrderVO.arrival_date}</td>
										<td>${roomOrderVO.departure_date}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomOrder/RoomOrderServlet.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="room_order_id" value="${roomOrderVO.room_order_id}">
												<input type="hidden" name="action" value="backend_getOne_For_Update">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>

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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>

<%
RoomService roomService = new RoomService();
List<RoomVO> list = roomService.getAll();
pageContext.setAttribute("list", list);
%>

<jsp:useBean id="roomTypeSvc" scope="page" class="com.roomType.model.RoomTypeService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<html>
<head>
<title>房間管理</title>
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
	width: 80%;
	padding: auto;
}

</style>
</head>
<body class="sb-nav-fixed">
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header ">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">房間管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/room/listAllRoom.jsp">房間管理</a></li>
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
						<input type="Submit" class="color-btn" value="新增房間" onclick="location.href='<%=request.getContextPath()%>/back-end/room/addRoom.jsp'" />
					</div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>房間號碼</th>
									<th>房型類別</th>
									<th>床位</th>
									<th>住客名單</th>
									<th>上下架狀態</th>
									<th>房間狀態</th>
									<th>操作</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="roomVO" items="${list}">
									<tr>
										<td>${roomVO.room_id}</td>
										<td><c:forEach var="roomTypeVO" items="${roomTypeSvc.all}">
							                    <c:if test="${roomVO.room_type_id==roomTypeVO.room_type_id}">
								                    ${roomTypeVO.room_type_name}
							                    </c:if>
							                </c:forEach></td>
										<td>${roomVO.qtyofbeds}</td>
										<td><c:forEach var="memVO" items="${memSvc.all}">
							                    <c:if test="${(roomVO.room_guest_name).equals((memVO.mem_id).toString())}">
								                    ${memVO.mem_name}
							                    </c:if>
							                </c:forEach></td>
										<c:set var="salestatus" scope="session" value="${roomVO.room_sale_status}"/>
										<td><c:choose>
											    <c:when test="${salestatus == true}">
											       已上架
											    </c:when>
											    <c:when test="${salestatus == false}">
											       未上架
											    </c:when>
											</c:choose> 
										</td>
										
										<c:set var="status" scope="session" value="${roomVO.room_status}"/>
										<td><c:choose>
											    <c:when test="${status == 0}">
											       未入住
											    </c:when>
											    <c:when test="${status == 1}">
											       已入住
											    </c:when>
											    <c:when test="${status == 2}">
											       待清潔
											    </c:when>
											    <c:otherwise>
											        你他媽亂寫阿!
											    </c:otherwise>
											</c:choose>
										</td>
										<td><c:choose>
											    <c:when test="${status == 2}">
											    	<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/room/room.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="清潔" style="color:red"> <input
															type="hidden" name="room_id" value="${roomVO.room_id}">
														<input type="hidden" name="action" value="clean">
													</FORM>
											    </c:when>
											    <c:otherwise >
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/room/room.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改"> <input
															type="hidden" name="room_id" value="${roomVO.room_id}">
														<input type="hidden" name="action" value="getOne_For_Update">
													</FORM>
											    </c:otherwise>
											</c:choose>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/room/room.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除" style="background-color: red; color: white; font-weight:bold;"> <input
													type="hidden" name="room_id" value="${roomVO.room_id}">
												<input type="hidden" name="action" value="delete">
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
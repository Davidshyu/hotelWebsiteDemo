<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.roomType.model.*"%>
<%@ page import="com.roomTypePic.model.*"%>

<%


RoomTypeService roomTypeService = new RoomTypeService();
List<RoomTypeVO> list = roomTypeService.getAll();
pageContext.setAttribute("list", list);
%>
<jsp:useBean id="roomPic" scope="page"
	class="com.roomTypePic.model.RoomTypePicService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>房型管理</title>
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
	margin:10px;
}

.explainP {
	width: 100%;
	word-break: break-all;
}

#big {
	width: 85%;
	padding: auto;
}

table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid rgb(175, 169, 169);
	text-align:center;
}

.s{
	vertical-align: middle;
	text-align: center;
}
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>

</head>

<body class="sb-nav-fixed">
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">房型管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/roomtype/listAllRoomType.jsp">房型管理</a></li>
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
						<input type="Submit" value="新增房型"
							onclick="location.href='<%=request.getContextPath()%>/back-end/roomtype/addRoomType.jsp'" />
					</div>


					<!-- table -->

					<table id="shoppingCart"
						class="table table-condensed table-responsive">
						<thead>
							<tr>
								<th style="width: 60%">房間</th>
								<th style="width: 8%">價格</th>
								<th style="width: 13%">上下架狀態</th>
								<th style="width: 7%">數量</th>
								<th style="width: 12%; text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="roomTypeVO" items="${list}">
								<tr>
									<td data-th="Product">
										<div class="row">
											<div class="col-md-3 text-start">
														<img src="<%=request.getContextPath()%>/roomType/roomTypePic.do?room_type_id=${roomTypeVO.room_type_id}" 
														alt=""  width="130px" height="130px"  style="border-radius: 10px;display:block; margin:auto;"/>
											</div>
											<div class="col-md-9 text-start mt-sm-2">
												<h4>${roomTypeVO.room_type_name}</h4>
												<p class="font-weight-light explainP">${roomTypeVO.room_type_content}</p>
											</div>
										</div>
									</td>
									<td data-th="Price"class="align-middle">${roomTypeVO.room_type_price} </td>
									<c:set var="salestatus" scope="session" value="${roomTypeVO.room_type_sale_status}"/>
										<td class="align-middle"><c:choose>
											    <c:when test="${salestatus == true}">
											       已上架
											    </c:when>
											    <c:when test="${salestatus == false}">
											       未上架
											    </c:when>
											</c:choose>   
										</td>
									<td data-th="Quantity"class="align-middle">${roomTypeVO.room_type_amount}</td>
									<td class="actions" data-th="">
										<div class="text-end" style="justify-content: center;">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
												style="margin-bottom: 0px; display: inline;">
												<input type="submit" value="修改房型"> 
												<input type="hidden" name="room_type_id"value="${roomTypeVO.room_type_id}"> 
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
												style="margin-bottom: 0px; display: inline;">
												<input type="submit" value="修改照片"> <input
													type="hidden" name="room_type_id"
													value="${roomTypeVO.room_type_id}"> <input
													type="hidden" name="action" value="toUpdatePIC">
											</FORM>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/roomType/roomType.do"
												style="margin-bottom: 0px; display: inline;">
												<input type="submit" value="刪除房型" style="background-color: red; color: white; font-weight:bold;" > <input
													type="hidden" name="room_type_id"
													value="${roomTypeVO.room_type_id}"> <input
													type="hidden" name="action" value="delete">
											</FORM>
										</div>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
		</div>
    </section>
    <!-- /.content -->
<%@ include file= "/back-end/framework/footer.file" %>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
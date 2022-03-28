<%@page import="com.activityPhoto.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.activity.model.*"%>
<%
ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO");
int id = activityVO.getActivity_id();
%>

<%
	ActivityPhotoService activityPhotoService = new ActivityPhotoService();
	List<ActivityPhotoVO> list = activityPhotoService.getFKPic(id);
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>照片管理</title>
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
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動${activityVO.activity_id}:照片管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp">活動列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activity/select_page.jsp">活動查詢</a></li>
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
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ActivityPhotoPicServlet.do" style="margin-bottom: 0px;">
												<input type="submit" value="新增照片"> 
												<input type="hidden" name="activity_id"value="${activityVO.activity_id}"> 
												<input type="hidden" name="action" value="getOne_For_Insert">
						</FORM>
						<input type="Submit" value="返回活動管理"
							onclick="location.href='<%=request.getContextPath()%>/back-end/activity/listAllActivity.jsp'" />
					</div>
					<div class="card-header"></div>
					<div class="card-body">
						<table id="datatablesSimple">
							<thead>
								<tr>
									<th>照片編號</th>
									<th>照片</th>
									<th>操作</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="picVO" items="${list}">
									<tr>
										<td>${picVO.activity_photo_id}</td>
										<td><img
											src="<%=request.getContextPath()%>/ActivityPicReaderforid.do?activity_photo_id=${picVO.activity_photo_id}"
											alt=""
											class="img-fluid d-none d-md-block rounded mb-2 shadow " width="200px" height = "200px"></td>
										<td>
											<FORM METHOD="post" enctype="multipart/form-data" ACTION="<%=request.getContextPath()%>/ActivityPhotoPicServlet.do" style="margin-bottom: 0px;">
												
												<input type="file" name="room_type_pic" size="50" />
												
												
												<input type="submit" value="修改">
												 <input type="hidden" name="activity_photo_id" value="${picVO.activity_photo_id}"> 
												 <input type="hidden" name="activity_id" value="${activityVO.activity_id}"> 
													<input type="hidden" name="action" value="Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/ActivityPhotoPicServlet.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> 
												<input type="hidden" name="activity_photo_id" value="${picVO.activity_photo_id}"> 
												<input type="hidden" name="activity_id" value="${picVO.activity_id}"> 
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
	<!--*******************
		Start Include JS File
		******************* -->
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
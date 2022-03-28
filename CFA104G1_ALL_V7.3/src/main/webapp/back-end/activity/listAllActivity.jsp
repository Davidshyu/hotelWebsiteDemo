<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ActivityService activityService = new ActivityService();
List<ActivityVO> list = activityService.getAllActivity();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有活動資料-listAllActivity.jsp</title>
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
            <h1 class="m-0">所有活動資料</h1>
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
				<i class="fas fa-table me-1"></i>所有活動資料
			</div>
			<div class="card-body">
				<a class="b1 btn btn-outline-secondary" href="<%= request.getContextPath() %>/back-end/activity/addActivity.jsp" role="button">新增活動</a>

				<table id="datatablesSimple" class="table table-bordered table-hover">
					<tr>
						<th>活動編號</th>
						<th>活動類別編號</th>
						<th>活動照片</th>
						<th>活動名稱</th>
						<th>活動資訊</th>
						<th>修改照片</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
					<c:forEach var="activityVO" items="${list}">
					<tr>
						<td>${activityVO.activity_id}</td>
						<td>${activityVO.activity_category_id}</td>
						
						<td><img src="<%=request.getContextPath()%>/ActivityPicReader?activity_id=${activityVO.activity_id}" 
							alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow " width="100px" height="100px"></td>
						<td>${activityVO.activity_name}</td>
						<td>${activityVO.activity_info}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/activity.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="照片修改"> 
								<input type="hidden" name="activity_id" value="${activityVO.activity_id}"> 
								<input type="hidden" name="action" value="toUpdatePIC">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/activity.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> 
								<input type="hidden" name="activity_id" value="${activityVO.activity_id}"> 
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/activity.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="activity_id" value="${activityVO.activity_id}">
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
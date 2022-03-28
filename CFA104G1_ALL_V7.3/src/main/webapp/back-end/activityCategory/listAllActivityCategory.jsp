<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.activityCategory.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ActivityCategoryService activityCategoryService = new ActivityCategoryService();
List<ActivityCategoryVO> list = activityCategoryService.getAllActivityCategory();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file= "/back-end/framework/include.file" %>
<%@ include file= "/back-end/framework/includeTableCss.file" %>

<meta charset="UTF-8">
<title>所有活動類別資料</title>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動類別資料</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityCategory/listAllActivityCategory.jsp">活動類別列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityCategory/select_page.jsp">活動類別查詢</a></li>
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
				<i class="fas fa-table me-1"></i>活動類別維護
			</div>
			<div class="card-body">
				<a class="b1 btn btn-outline-secondary" href="<%= request.getContextPath() %>/back-end/activityCategory/addActivityCategory.jsp" role="button">新增活動類別</a>
			<table id="datatablesSimple" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>活動類別編號</th>
						<th>活動類別名稱</th>
						<th>活動類別資訊</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
			
				<tbody>
					<c:forEach var="activityCategoryVO" items="${list}">
					<tr>
						<td>${activityCategoryVO.activity_category_id}</td>
						<td>${activityCategoryVO.activity_category_name}</td>
						<td>${activityCategoryVO.activity_category_info}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/activityCategory.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="activity_category_id"
									value="${activityCategoryVO.activity_category_id}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/activityCategory.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="activity_category_id" value="${activityCategoryVO.activity_category_id}">
								<input type="hidden" name="action" value="delete">
								<input type="submit" value="刪除"> 
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
<%@ include file= "/back-end/framework/includeTableJs.file" %>
	
</body>
<script>
$(function () {
    $('#table_id').DataTable();
})
</script>

</html>
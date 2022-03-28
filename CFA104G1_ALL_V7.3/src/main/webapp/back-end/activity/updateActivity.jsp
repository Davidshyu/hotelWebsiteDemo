<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<%
ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); //ActivityServlet.java (Controller) 存入req的activityVO物件 (包括幫忙取出的activityVO, 也包括輸入資料錯誤時的activityVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動資料修改 - updateActicity.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>
<link href="<%=request.getContextPath()%>/resources/plugins/summernote/summernote-bs4.min.css" rel="stylesheet">
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動資料修改</h1>
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
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		</div>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity.do" >
 		<div class="card mb-4 a1">
			<div class="card-body">

				<table>
				<tr>
				<td>活動編號:</td>
				<td>${activityVO.activity_id}</td>
				</tr>
				<jsp:useBean id="activityCategoryService" scope="page" class="com.activityCategory.model.ActivityCategoryService" />
				<tr>
				<td>活動類別編號:</td>
				  		<td><select name="activity_category_id">
					  		<c:forEach var="activityCategoryVO" items="${activityCategoryService.allActivityCategory}" > <!-- servlet P.231 -->
					 	 		<option value="${activityCategoryVO.activity_category_id}" ${(activityVO.activity_category_id==activityCategoryVO.activity_category_id)? 'selected':'' } >${activityCategoryVO.activity_category_name}
					  		</c:forEach>
				  		</select></td>
				  		</tr>
				<tr>
				<td>活動名稱:</td>
				<td><input type="TEXT" name="activity_name" maxlength="20" value="${activityVO.activity_name}" /></td>
				</tr>
				<tr>
							<td>活動資訊: </td>       
				<!-- 			<td><textarea name="activity_info" maxlength="1000" rows="20" cols="50"></textarea></td> -->
							<td>
								<textarea name="activity_info" id="summernote" >
									${activityVO.activity_info}
				              	</textarea>
				            </td>
				</tr>
				</table>
	  		</div>
			<div class="card-footer">
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="activity_id" value="${activityVO.activity_id}">
				<button type="submit">送出</button>
	  		</div>
	  </div>
	</FORM>
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>

</body>
<!-- Summernote -->
<script src="<%=request.getContextPath()%>/resources/plugins/summernote/summernote-bs4.min.js"></script>
<script>
  $(function () {
    // Summernote
    $('#summernote').summernote({
        height: 150
      });
  })
</script>
</html>
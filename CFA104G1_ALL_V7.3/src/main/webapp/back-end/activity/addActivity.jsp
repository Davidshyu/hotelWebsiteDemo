<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activity.model.*"%>
<% ActivityVO activityVO = (ActivityVO) request.getAttribute("activityVO"); %>
<!DOCTYPE html>
<html>
<head>
<!-- include libraries(jQuery, bootstrap) -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" />
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- include summernote css/js-->
<link href="<%=request.getContextPath()%>/resources/summernote/summernote-bs5.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/summernote/summernote-bs5.js"></script>

<meta charset="UTF-8">
<title>活動新增 - addActivity.jsp</title>
</head>
<body>
<table>
	<tr><td>
	<h3>活動新增 - addActivity.jsp</h3>
	<h4><a href="<%=request.getContextPath()%>/back-end/activity/select_page.jsp">回首頁</a></h4>
</td></tr>
</table>
		<jsp:useBean id="activityCategoryService" scope="page" class="com.activityCategory.model.ActivityCategoryService" />
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activity.do" enctype="multipart/form-data">
		<table>
		<tr>
		<td>選擇活動類別:</td>
  		<td>
  		<select name="activity_category_id">
	  		<c:forEach var="activityCategoryVO" items="${activityCategoryService.allActivityCategory}" > <!-- servlet P.231 -->
	 	 		<option value="${activityCategoryVO.activity_category_id}" ${(activityVO.activity_category_id==activityCategoryVO.activity_category_id)? 'selected':'' } >${activityCategoryVO.activity_category_name}
	  		</c:forEach>
  		</select>
  		</td>
  		</tr>
  		
  		<tr>
			<td>活動名稱:</td>
			<td><input type="text" name="activity_name" maxlength="20" value="<%= (activityVO==null)? "" : activityVO.getActivity_name()%>"/> </td>
		</tr>
		<tr>
			<td>活動資訊: </td>       
<!-- 			<td><textarea name="activity_info" maxlength="1000" rows="20" cols="50"></textarea></td> -->
			<td><textarea name="activity_info" id="summernote">
			<%= (activityVO==null)? "" : activityVO.getActivity_info()%>
              </textarea></td>
		</tr>
			<tr>
				<td>活動圖片</td>
				<td><input type="file" name="activity_photo_file" size="50" /></td>
			</tr>
		</table>
			<input type="hidden" name="action" value="insert">
        	<button type="submit">送出</button>
		</FORM>
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
</body>
<!-- Summernote -->
<script>
  $(function () {
    // Summernote
    $('#summernote').summernote();
  })
</script>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.activityCategory.model.*"%>
<%
ActivityCategoryVO activityCategoryVO = (ActivityCategoryVO) request.getAttribute("activityCategoryVO"); //ActivityCategoryServlet.java (Concroller) 存入req的activityCategoryVO物件 (包括幫忙取出的activityCategoryVO, 也包括輸入資料錯誤時的activityCategoryVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動類別資料修改</title>
<%@ include file= "/back-end/framework/include.file" %>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">活動類別資料修改</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityCategory/listAllActivityCategory.jsp">活動類別列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityCategory/select_page.jsp">活動類別列表</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="col-lg-12">
      

 		<div class="card mb-4 a1">
			<div class="card-body">

				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activityCategory.do" >
				活動類別編號:
				${activityCategoryVO.activity_category_id}<br>
				活動類別名稱:<br>
				<input type="TEXT" name="activity_category_name" maxlength="20" value="${activityCategoryVO.activity_category_name}" /><br>
				活動類別資訊:<br>
				<textarea name="activity_category_info" maxlength="1000" rows="10" cols="100" >${activityCategoryVO.activity_category_info}</textarea><br> 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="activity_category_id" value="${activityCategoryVO.activity_category_id}">

			  </div>
			  <div class="card-footer">
				<button type="submit">送出</button>
				</FORM>

			  </div>

	  		</div>

	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>








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
</html>
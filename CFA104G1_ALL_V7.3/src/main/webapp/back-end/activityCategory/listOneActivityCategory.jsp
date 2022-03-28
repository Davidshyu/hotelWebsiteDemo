<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.activityCategory.model.*"%>
    <%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
ActivityCategoryVO activityCategoryVO = (ActivityCategoryVO) request.getAttribute("activityCategoryVO"); //ActivityCategoryServlet.java (Concroller) 存入req的activityCategoryVO物件 (包括幫忙取出的activityCategoryVO, 也包括輸入資料錯誤時的activityCategoryVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動類別資料 - listOneActivityCategory.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>
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

			<table  class="table">
				<tr>
					<th>活動類別編號</th>
					<th>活動類別名稱</th>
					<th>活動類別資訊</th>
					
				</tr>
					<tr>
					<td>${activityCategoryVO.activity_category_id}</td>
					<td>${activityCategoryVO.activity_category_name}</td>
					<td>${activityCategoryVO.activity_category_info}</td>
				</tr>
			</table>
	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
</body>
</html>
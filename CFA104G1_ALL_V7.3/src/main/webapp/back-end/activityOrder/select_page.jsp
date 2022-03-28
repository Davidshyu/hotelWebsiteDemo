<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料查詢</title>
<%@ include file= "/back-end/framework/include.file" %>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">資料查詢</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityOrder/listAllActivityOrder.jsp">活動訂單列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/activityOrder/select_page.jsp">活動訂單查詢</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="col-lg-12">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
      
			<ul>
			  <li><a href='listAllActivityOrder.jsp'>列出</a>所以活動訂單.  <br></li>
			
			<li>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/activityOrder.do">
			  <b>輸入活動訂單編號:</b>
			  <input type="text" name="activity_order_id">
			  <input type="hidden" name="action" value="getOne_For_Display">
			  <input type="submit" value="送出">
			  </FORM>
			  </li>
			 </ul>
			
			  
 	  </div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
      
      


</body>
</html>
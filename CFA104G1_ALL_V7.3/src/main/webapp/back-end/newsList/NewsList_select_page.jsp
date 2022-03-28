<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.newsList.model.*" %>

<%-- <jsp:useBean id="productClassSvc" scope="page" class="com.newsList.model.NewsListJDBCDAO"/> --%>
<!DOCTYPE html>
<html>
<head>


<!--*******************	
		Start Include CSS File  
		******************* -->
<%@ include file= "/back-end/framework/include.file" %>
<!--*******************	
		End Include CSS File  
		******************* -->
		
<meta charset="UTF-8">
<title>媒體報導後台管理</title>
<link rel="icon" type="image/png" href="../resources/images/favicon.png">

</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">管理媒體報導</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/newsList/listAllNewsList.jsp">新聞列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/newsList/addNewsList.jsp">新增報導</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title">查詢報導</h3>
							</div>
							<div class="card-content">
								<!-- table head dark -->
								<div class="table-responsive">
									<table class="table mb-0">
										<thead>
																					
											<tr>
												<td>
													
													<!--錯誤訊息顯示 -->
													<c:if test="${not empty errorMsgs}">
														<c:forEach var="message" items="${errorMsgs}">
															<h5 style="color:red">${message}</h5>
														</c:forEach>
													</c:if>
													
													<!--錯誤訊息顯示 -->													
    												
													<!-- 複合查詢>>>>>>>-->
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/newsList/NewsList.do" >
												        <b>輸入報導編號 : </b>
												        <input type="text" name="newsId">
    													<br><br>
												        <b>輸入報導標題 : </b>
												        <input type="text" name="title">
    													<br><br>
												        <b>輸入報導內容 : </b>
												        <input type="text" name="content">
												        <br><br>
												        <b>輸入狀態 : </b>
												        <select name="status">
												        	<option value=" ">請選擇</option>
												        	<option value="上架中">上架中</option>
												        	<option value="下架中">下架中</option>
												        </select>
												        <input type="hidden" name="action" value="compoundQuery">
												        <br><br>
												        <b>*若無指定條件送出，即可查詢所有報導列表</b>
												        <br><br>
												        <input type="submit" class="btn btn-secondary" value="送出查詢">
    												</FORM>
												</td>
											</tr>
											<tr><td>
												<a href="<%=request.getContextPath()%>/back-end/newsList/listAllNewsList.jsp" class="btn btn-secondary"> 顯示所有報導 </a>
											</td></tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
        
		</div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
        
		<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>
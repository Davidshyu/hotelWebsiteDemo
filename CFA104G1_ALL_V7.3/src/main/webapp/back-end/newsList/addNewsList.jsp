<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="com.newsList.model.*"  %>

<%
NewsListVO newsListVO = (NewsListVO) request.getAttribute("newsListVO");
%>
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
<title>後台管理</title>
</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h3><a href="<%=request.getContextPath()%>/back_end/newsList/NewsList_select_page.jsp">管理報導</a></h3>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/newsList/NewsList_select_page.jsp">查詢報導</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/newsList/listAllNewsList.jsp">新聞列表</a></li>
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
				<div class="card-content">
					<div class="card-header">
						<h3 class="card-title">新增報導</h3>
					</div>
				
					<div class="card-body">
						<!--錯誤顯示>>>>-->
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="error" items="${errorMsgs}"> 
									<h6 class="card-text" style="color:red">${error}</h6>
							</c:forEach>
							<h5 class="card-text" style="color:red"></h5>
						</c:if>
							<br>
						<!--<<<<錯誤顯示-->
	
						<form method="post" class="form form-horizontal" enctype="multipart/form-data" action="<%=request.getContextPath()%>/newsList/NewsList.do">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<label>新聞標題</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="title" value="<%=(newsListVO==null)?"":newsListVO.getTitle()%>">
									</div>
																		
<%-- 									<jsp:useBean id="classSvc" scope="page" class="com.newsList.model.ProductClassService"/> --%>
<!-- 									<div class="col-md-4"> -->
<!-- 										<label>商品類別</label> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-8 form-group">									 -->
<!-- 										<select class="form-select" id="basicSelect" name="pc_no"> -->
<%-- 											<c:forEach var="classVO" items="${classSvc.all}"> --%>
<%--                                             	<option value="${classVO.pcNo}" ${(classVO.pcNo == productVO.pcNo)? 'selected':'' }>${classVO.pcName}</option>                                           		 --%>
<%--                                            </c:forEach> --%>
<!--                                     	</select> -->
<!-- 									</div> -->
									<div class="col-md-4">
										<label>新聞內容</label>
									</div>
									<div class="col-md-8 form-group">
										<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content"><%=(newsListVO==null)?"":newsListVO.getTitle()%></textarea>
									</div>
									


<!-- 									<div class="col-md-4"> -->
<!-- 										<label>商品價格</label> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-8 form-group"> -->
<!-- 										<input type="text" class="form-control" -->
<%-- 											name="pi_pri" value="<%=(productVO==null)?"":productVO.getPiPri()%>"> --%>
<!-- 									</div> -->

<!-- 									<div class="col-md-4"> -->
<!-- 										<label>庫存</label> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-8 form-group"> -->
<!-- 										<input type="text" class="form-control" -->
<%-- 											name="pi_stock" value="<%=(productVO==null)?"":productVO.getPiStock()%>"> --%>
<!-- 									</div> -->
								
									<div class="col-md-4">
										<label>報導狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="basicSelect" name="status">
                                            <option value = "上架中" ${( "上架中" == newsListVO.Status)? 'selected':'' }>上架中</option>
                                            <option value = "下架中" ${( "下架中" == newsListVO.Status)? 'selected':'' }>下架中</option>
                                    	</select>	
									</div>
									
									<div class="col-md-4">
										<label>報導出處</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="newsComeFrom" value="<%=(newsListVO==null)?"":newsListVO.getNewsComeFrom()%>">
									</div>
									
									
									<div class="col-md-4">
										<label>報導封面照片</label>
									</div>
									<div class="col-md-8 form-group">
										<input class="form-control" type="file" id="formFile" multiple name="imageFile">
									</div>
									
									<div class="col-sm-12 d-flex justify-content-end">
										<button type="submit" class="btn btn-primary me-1 mb-1">新增</button>
										<button type="reset" class="btn btn-light-secondary me-1 mb-1">清除</button>
									</div>
										<input type="hidden" name="action" value="insert">
										
								</div>
							</div>
						</form>
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
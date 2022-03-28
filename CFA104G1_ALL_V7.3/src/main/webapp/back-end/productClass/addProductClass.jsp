<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productClass.model.*" %>	

<%-- <%  --%>
<!-- // 	ProductClassVO productClassVO = (ProductClassVO) request.getAttribute("productClassVO"); -->
<%-- %> --%>
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
<title>商城後台管理</title>
<!-- <link rel="icon" type="image/png" -->
<!-- 	href="../back_CSS_JS/assets/images/logo/favicon.png"> -->

</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">管理商品</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/product/select_Page.jsp">查詢商品</a></li>
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
					<div class="card-body">
						<h4>新增商品類別</h4><br>
					
	
						<form method="post" class="form form-horizontal" action="<%=request.getContextPath()%>/productClass/ProductClass.do">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<label>類別名稱：</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="pc_name" value="">
									</div>
									
						<!--錯誤顯示>>>>-->
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="error" items="${errorMsgs}"> 
									<h6 class="card-text" style="color:red">${error}</h6>
							</c:forEach>
							<h5 class="card-text" style="color:red"></h5>
						</c:if>
							<br>
						<!--<<<<錯誤顯示-->
									
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
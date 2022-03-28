<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*" %>	
<%@ page import="com.productImage.model.*"   %>
<%@ page import="java.util.*" %>

<%-- <%  --%>
<!-- // 	ProductClassService productClassSvc = new ProductClassService(); -->
<!-- // 	List<ProductClassVO> classList = productClassSvc.getAll(); -->
<!-- // 	request.setAttribute("classList", classList); -->
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
							<div class="card-header">
								<h4 class="card-title">商品類別</h4>
							</div>
							<div class="card-content">
								<div class="card-body">
									<!-- Table with outer spacing -->
									<div class="table-responsive">
										<table class="table table-lg">
											<thead>
												<tr>
													<th>類別編號</th>
													<th>${productClassVO.pcNo}</th>
												</tr>
												<tr>
													<th>類別名稱</th>
													<th>${productClassVO.pcName}</th>
												</tr>
											</thead>
										</table>
									</div>
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
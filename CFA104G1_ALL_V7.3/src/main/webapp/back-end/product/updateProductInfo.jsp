<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productClass.model.*" %>	
<%@ page import="com.product.model.*"  %>

<% 
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
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
<link rel="icon" type="image/png"
	href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

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
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp">商品列表</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
    	<!-- Table head options start 二手商品-->
        <div class="col-lg-12">
			<div class="card">
				<div class="card-content">
					<div class="card-body">
						<h4>修改商品</h4><br>
						<!--錯誤顯示>>>>-->
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="error" items="${errorMsgs}"> 
									<h4 class="card-text" style="color:red">${error}</h4>
							</c:forEach>
							<h5 class="card-text" style="color:red"></h5>
						</c:if>
							<br>
						<!--<<<<錯誤顯示-->
	
						<form method="post" class="form form-horizontal" enctype="multipart/form-data" action="<%=request.getContextPath()%>/product/Product.do">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<label>商品名稱</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="pi_name" value="<%=(productVO==null)?"":productVO.getPiName()%>">
									</div>
																		
									<jsp:useBean id="classSvc" scope="page" class="com.productClass.model.ProductClassService"/>
									<div class="col-md-4">
										<label>商品類別</label>
									</div>
									<div class="col-md-8 form-group">									
										<select class="form-select" id="basicSelect" name="pc_no">
											<c:forEach var="ClassVO" items="${classSvc.all}">
                                            	<option value = "${ClassVO.pcNo}">${ClassVO.pcName}</option>
                                           </c:forEach>
                                    	</select>
									</div>
									<div class="col-md-4">
										<label>商品內容</label>
									</div>
									<div class="col-md-8 form-group">
										<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="pi_content"><%=(productVO==null)?"":productVO.getPiContent()%></textarea>
									</div>

									<div class="col-md-4">
										<label>商品價格</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="pi_pri" value="<%=(productVO==null)?" ":productVO.getPiPri()%>">
									</div>

									<div class="col-md-4">
										<label>庫存</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="pi_stock" value="<%=(productVO==null)?" ":productVO.getPiStock()%>">
									</div>
								
									<div class="col-md-4">
										<label>商品狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="basicSelect" name="pi_sta">
                                            <option value = "上架中">上架</option>
                                            <option value = "下架中">下架</option>
                                    	</select>	
									</div>
									
<!-- 									<div class="col-md-4"> -->
<!-- 										<label>商品照片</label> -->
<!-- 									</div> -->
									
<!-- 									<div class="col-md-8 form-group"> -->
<!-- 										<input class="form-control" type="file" id="formFile" multiple name="imageFile"> -->
<!-- 									</div> -->
									
									<div class="col-sm-12 d-flex justify-content-end">
										<button type="submit" class="btn btn-primary me-1 mb-1">修改</button>
										<button type="reset" class="btn btn-light-secondary me-1 mb-1">清除</button>
									</div>
										<input type="hidden" name="pi_no" value="<%=productVO.getPiNo()%>">
										<input type="hidden" name="action" value="update">
										
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Table head options end 商品-->
    </section>

		<!--*******************	
		Start Include JS File  
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
		<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>
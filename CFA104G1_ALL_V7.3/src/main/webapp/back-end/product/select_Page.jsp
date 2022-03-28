<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productClass.model.*" %>

<jsp:useBean id="productClassSvc" scope="page" class="com.productClass.model.productClassJDBCDAO"/>
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
<title> 後台管理</title>
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
								<!-- table head dark -->
								<div class="table-responsive">
									<table class="table mb-0">
										<thead>
																					
											<tr>
												<th>
													<h4>查詢商品</h4>
													
													<!--錯誤訊息顯示 -->
													<c:if test="${not empty errorMsgs}">
														<c:forEach var="message" items="${errorMsgs}">
															<h5 style="color:red">${message}</h5>
														</c:forEach>
													</c:if>
													
													<!--錯誤訊息顯示 -->													
    												
													<!-- 複合查詢>>>>>>>-->
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/Product.do" >
												        <b>輸入商品編號 : </b>
												        <input type="text" name="pi_no">
    													<br><br>
												        <b>輸入商品名稱 : </b>
												        <input type="text" name="pi_name">
    													<br><br>
												        <b>輸入商品類別 : </b>
												        <select name="pc_no">
														        <option value=" "></option>
												        	<c:forEach var="productClassVO" items="${productClassSvc.all}">
												        		<option value="${productClassVO.pcNo}">${productClassVO.pcName}</option>
												        	</c:forEach>
												        </select>
    													<br><br>
												        <b>輸入狀態 : </b>
												        <select name="pi_sta">
												        	<option value=" "></option>
												        	<option value="上架中">上架</option>
												        	<option value="下架中">下架</option>
												        </select>
												        <input type="hidden" name="action" value="compoundQuery">
												        <br><br>
												        <input type="submit" class="btn btn-secondary" value="送出查詢">
    												</FORM>
													<!--<<<<<<<複合查詢 -->
												</th>
											</tr>
											
											<tr>
												<th><a href="<%=request.getContextPath()%>/back-end/product/addProduct.jsp" class="btn btn-secondary"> 新增商品 </a></th>
											</tr>	
																						
										</thead>
									</table>
								</div>
							</div>
						</div>
		</div>
		<!-- Table head options end 商品-->

		<!-- Table head options start 商品類別-->
        <div class="col-lg-12">
						<div class="card">
							<div class="card-content">
								<!-- table head dark -->
								<div class="table-responsive">
									<table class="table mb-0">
										<thead>
																					
											<tr>
												<th>
													<h4>查詢商品類別</h4>
													
													<!--錯誤訊息顯示 -->
													<c:if test="${not empty errorMsgs_class}">
														<c:forEach var="message" items="${errorMsgs_class}">
															<h5 style="color:red">${message}</h5>
														</c:forEach>
													</c:if>
													
													<!--錯誤訊息顯示 -->
													
												    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/productClass/ProductClass.do">
												        <b>輸入類別編號 : </b>
												        <input type="text" name="pc_no">
												        <input type="hidden" name="action" value="FindByPK">
												        <input type="submit" class="btn btn-secondary" value="送出">
    												</FORM>
    												<br>
												</th>
											</tr>
											
											<tr>
												<th><a href="<%=request.getContextPath()%>/back-end/productClass/listAllProductClass.jsp" class="btn btn-secondary"> 顯示所有商品類別 </a></th>
											</tr>
											
											<tr>
												<th><a href="<%=request.getContextPath()%>/back-end/productClass/addProductClass.jsp" class="btn btn-secondary"> 新增商品類別 </a></th>
											</tr>	
																						
										</thead>
									</table>
								</div>
							</div>
						</div>
		</div>
		<!-- Table head options end 商品類別-->
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
</body>
</html>
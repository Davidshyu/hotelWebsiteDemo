<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*" %>	
<%@ page import="com.productImage.model.*"   %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
ProductClassService productClassSvc = new ProductClassService();
	List<ProductClassVO> classList = productClassSvc.getAll();
	request.setAttribute("classList", classList);
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
	<div id="app">
		<div id="main">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3>
							<a
								href="<%=request.getContextPath()%>/back_end/product/select_Page.jsp">管理商品</a>
						</h3>
					</div>
				</div>
			</div>

			<!-- Basic Tables start -->
			<section class="section">
				<div class="row" id="basic-table">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">商品詳細資訊</h4>
							</div>
							<div class="card-content">
								<div class="card-body">
									<!-- Table with outer spacing -->
									<div class="table-responsive">
										<table class="table table-lg">
											<thead>
												<tr>
													<th>商品編號</th>
													<th>${productVO.piNo}</th>
												</tr>
												<tr>
													<th>商品名稱</th>
													<th>${productVO.piName}</th>
												</tr>
												<tr>
													<th>商品類別</th>
													<c:forEach var="classList" items="${classList}">  
														<c:if test="${productVO.pcNo == classList.pcNo}">
															<th>${classList.pcName}</th>
														</c:if>
													</c:forEach>
												</tr>
												<tr>
													<th>商品內容</th>
													<th>${productVO.piContent}</th>
												</tr>
												<tr>
													<th>商品價格</th>
													<th><fmt:formatNumber type="number" maxFractionDigits="3" value="${productVO.piPri}"/></th>
												</tr>
												<tr>
													<th>商品庫存</th>
													<th>${productVO.piStock}</th>
												</tr>
												<tr>
													<th>商品狀態</th>
													<th>${productVO.piSta}</th>
												</tr>
												
												<c:forEach var="imagesList" items="${filterImagesList}">
													<tr>
														<th>商品照片</th>
														<th>
															<img src="<%=request.getContextPath()%>/product/Product.do?pim_no=${imagesList.pim_no}&action=ExportImages"  style="width:100px;height:100px;">
														</th>
													</tr>
												</c:forEach>
												
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Basic Tables end -->


		</div>
	</div>

	<!--*******************	
		Start Include JS File  
		******************* -->
<%@ include file= "/back-end/framework/includeJs.file" %>
	<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>
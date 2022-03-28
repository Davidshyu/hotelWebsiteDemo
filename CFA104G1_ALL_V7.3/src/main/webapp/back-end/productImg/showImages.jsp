<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*"%>	
<%@ page import="com.productImage.model.*"%>
<%@ page import="java.util.*" %>



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
							<a href="<%=request.getContextPath()%>/back_end/product/select_Page.jsp">管理商品</a>
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
								<h6 class="card-title"><a href="<%=request.getContextPath()%>/back_end/product/listAllProduct.jsp">回到列表</a></h6>
							</div>
							<div class="card-content">
								<div class="card-body">
									<!--錯誤顯示>>>>-->
										<c:if test="${not empty errorMsgs}">
											<c:forEach var="error" items="${errorMsgs}"> 
											<h4 class="card-text" style="color:red">${error}</h4>
											</c:forEach>
											<h5 class="card-text" style="color:red"></h5>
										</c:if>
										<br>
									<!--<<<<錯誤顯示-->
									<!-- Table with outer spacing -->
									<div class="table-responsive">
										<table class="table table-lg">
											<thead>
												 <c:forEach var="images" items="${Images}">
													<tr>
														<th>商品照片</th>
														<th><img src="<%=request.getContextPath()%>/productImg/ProductImg.do?pim_no=${images.pim_no}&action=ExportImages"  style="width:100px;height:100px;"></th>
														<th>
															<form
																action="<%=request.getContextPath()%>/productImg/ProductImg.do" method="post">
																<input type="submit" class="btn btn-outline-secondary" value="刪除">
																<input type="hidden" name="pim_no"value="${images.pim_no}"> 
																<input type="hidden" name="pi_no"value="${images.pi_no}"> 
																<input type="hidden" name="action" value="delete">
															</form>														
														</th>
													</tr>
												</c:forEach>
													
													<tr>
														<th>新增照片</th>

															<form
																action="<%=request.getContextPath()%>/productImg/ProductImg.do" method="post" enctype="multipart/form-data">
																<th><input class="form-control" type="file" id="formFile" multiple name="imageFile"></th>
																<th><input type="submit" class="btn btn-outline-secondary" value="新增"></th>
																<input type="hidden" name="pi_no"value="${pi_no}"> 
																<input type="hidden" name="action" value="insert">
															</form>
													</tr>
																					
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
<%@ include file= "/back-end/framework/include.file" %>
	<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>
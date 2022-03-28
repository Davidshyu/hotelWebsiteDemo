<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
						<div class="card-header">
							<h3 class="card-title">商品列表</h3>
						</div>

						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>商品編號</th>
										<th>商品名稱</th>
										<th>商品類別編號</th>
										<th>商品價格</th>
										<th>商品狀態</th>
										<th>詳細</th>
										<th>圖片</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="productInfoVO" items="${productVOs}" varStatus="s">
										<tr>
											<td>${productInfoVO.piNo}</td>
											<td>${productInfoVO.piName}</td>
											<td>${productInfoVO.pcNo} <c:forEach
													var="prodcutClassVO" items="${productClassSvc.all}">
													<c:if
														test="${productInfoVO.pcNo == prodcutClassVO.pcNo}"> 
															${prodcutClassVO.pcName}
														</c:if>
												</c:forEach>
											</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productInfoVO.piPri}"/></td>
											<td>${productInfoVO.piSta}</td>
											<td>
												<A HREF="javascript:presses${s.count}()" class="btn btn-outline-secondary">查看詳情</a>
											</td>
											<td>										
												<A HREF="javascript:presses_img${s.count}()" class="btn btn-outline-secondary">查看圖片</a>
											</td>
											<td>
												<form
													action="<%=request.getContextPath()%>/product/Product.do"
													method="post">
													<input type="submit" class="btn btn-outline-secondary" value="修改">
													<input type="hidden" name="pi_no" value="${productInfoVO.piNo}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
											</td>
										</tr>
										<script>
         									function presses${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/product/Product.do?pi_no=${productInfoVO.piNo}&action=getOneForDiplay", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
         									function presses_img${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/productImg/ProductImg.do?pi_no=${productInfoVO.piNo}&action=showImages", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
        								</script>
									</c:forEach>
								</tbody>
							</table>
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
		
	<script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
</body>
</html>
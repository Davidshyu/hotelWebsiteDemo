<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
ProductService productSvc = new ProductService();
 	List<ProductVO> list = productSvc.getAll();
 	pageContext.setAttribute("list", list);
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
										<th>詳細內容</th>
										<th>圖片</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="productVO" items="${list}" varStatus="s">
										<tr>
											<td>${productVO.piNo}</td>
											<td>${productVO.piName}</td>
											<td>${productVO.pcNo} <c:forEach
													var="prodcutClassVO" items="${productClassSvc.all}">
													<c:if
														test="${productVO.pcNo == prodcutClassVO.pcNo}"> 
															${productVO.pcName}
														</c:if>
												</c:forEach>
											</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productVO.piPri}"/></td>
											<td>${productVO.piSta}</td>
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
													<input type="hidden" name="pi_no" value="${productVO.piNo}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
											</td>
										</tr>
										
										<script>
         									function presses${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/product/Product.do?pi_no=${productVO.piNo}&action=getOneForDiplay", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
         									function presses_img${s.count}(){
            	 							document.open("<%=request.getContextPath()%>/productImg/ProductImg.do?pi_no=${productVO.piNo}&action=showImages", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
             								}
        								</script>
									</c:forEach>
								</tbody>
							</table>
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
		
	<script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
</body>
</html>
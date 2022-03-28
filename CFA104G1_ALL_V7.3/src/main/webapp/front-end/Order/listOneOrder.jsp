<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%-- <%@ page import="com.sec_product_inform.model.*" %> --%>

<%-- <% --%>
<!-- // 	ProductInformService productInformSvc = new ProductInformService(); -->
<!-- // 	List<ProductInformVO> productInformVOs = productInformSvc.getAll(); -->
<%--  %> --%>
<%-- <jsp:useBean id="productInformS" scope="page" class="w" /> --%>

<!DOCTYPE html>
<html>
<head>
<%@ include file= "/front-end/framework/include.file" %>
		<!--*******************	
		Start Include CSS File  
		******************* -->
		<%@ include file="../front_include_page/CSS_link.jsp"%>
		<!--*******************	
		End Include CSS File  
		******************* -->
<meta charset="UTF-8">
<title>後台管理</title>
<link rel="icon" type="image/png"
	href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
</head>
<body>
<%@ include file= "/front-end/framework/header.file" %>
	<div id="app">

		<div id="main">

			<!-- Basic Tables start -->
			<section class="section">
				<div class="row" id="basic-table">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">訂單詳細資訊</h4>

							</div>
							
							<div class="card-content">
								<div class="card-body">
									<!-- Table with outer spacing -->
									<div class="table-responsive">
										<table class="table table-lg">
											<thead>
												<tr>
													<th>訂單編號</th>
													<th>${productOrderVO.o_no}</th>
												</tr>
												<tr>
													<th>訂購時間</th>
													<th><fmt:formatDate value="${productOrderVO.o_purtime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
												</tr>
												<tr>
													<th>會員編號</th>
													<th>${productOrderVO.mem_no}</th>
												</tr>											
												<tr>
													<th>訂單狀態</th>
													<th>${productOrderVO.o_sta}</th>
												</tr>
												<tr>
													<th>付款狀態</th>
													<th>${productOrderVO.o_pay_sta}</th>
												</tr>
												<tr>
													<th>出貨狀態</th>
													<th>${productOrderVO.o_ship_sta}</th>
												</tr>
												
												<tr>
													<th>運送方式</th>
													<th>${productOrderVO.o_prodel}</th>
												</tr>
												<tr>
													<th>配送地址</th>
													<th>${productOrderVO.o_deladrs}</th>
												</tr>
												<tr>
													<th>付款方式</th>
													<th>${productOrderVO.o_paymthd}</th>
												</tr>
												<tr>
													<th>出貨日期</th>
													<th><fmt:formatDate value="${productOrderVO.o_shipdate}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
												</tr>
												<tr>
													<th>配送費用</th>
													<th>${productOrderVO.o_delcost}</th>
												</tr>
												<tr>
													<th>訂單總價格</th>
													<th>${productOrderVO.o_totalpri}</th>
												</tr>
											
												
											</thead>
										</table>
										<br>
										<h4>訂單明細</h4>
										<!--購物明細 -->
										<table class="table table-lg">
											<thead>
												<tr>
													<th>訂單明細編號</th>
													<th>商品名稱</th>
													<th>購買數量</th>
													<th>明細價格</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="productOrderListVO" items="${productOrderListVOs}">
													<tr>
														<td>${prouctOrderListVO.ol_no}</td>
														<td>
														<c:forEach var="productInformVO" items="${productInformSvc.all}">
															<c:if test="${prouctOrderListVO.pi_no==productVO.pi_no}">
																${productVO.pi_name}
															</c:if>
														</c:forEach>
														</td>
														<td>${prouctOrderListVO.ol_proamot}</td>
														<td>${prouctOrderListVO.ol_pri}</td>
													</tr>
												</c:forEach>
											</tbody>
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
<%@ include file= "/front-end/framework/footer.file" %>

</body>
</html>
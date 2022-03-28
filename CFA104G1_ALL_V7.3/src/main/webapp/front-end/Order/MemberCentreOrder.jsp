<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*"%>
<%@ page import="com.shopOrder.model.*"%>
<%@ page import="com.mem.model.*" %>



<jsp:useBean id="order_list_svc" scope="request"
	class="com.shopOrder.model.productOrderService" />
	
<% 
	MemVO memVO = (MemVO) session.getAttribute("user");
	
	productOrderService productOrderService = new productOrderService();
	List<productOrderVO> list = productOrderService.getAll()
										   .stream()
 										   .filter(m -> m.getMem_no().equals(memVO.getMem_id()))
										   .sorted(Comparator.comparing(productOrderVO::getO_no).reversed())
										   .collect(Collectors.toList());

	request.setAttribute("list", list);
%>

<!-- 前台_商品結帳頁面 -->
<!DOCTYPE html>
<html>
<head>
<%-- <%@ include file= "/front-end/framework/include.file" %> --%>
<!--*******************	
		Start Include CSS File  
		******************* -->
<%-- <%@ include file="../front_include_page/CSS_link.jsp"%> --%>
<!--*******************	
		End Include CSS File  
		******************* -->
		
		<style>
			/* 將付款按鈕disable */
			a.disabled {
        	pointer-events: none;
        	cursor: default;
/*         	color: #e1e1e1; */
			}

			.page-numbers-3 {
  			width: 50px;
 			height: 35px;
 			margin: 0 3px;
  			display: inline-block;
  			background-color: #113366;
  			line-height: 35px;
  			color: #ffffff;
  			-webkit-box-shadow: 0 2px 10px 0 #d8dde6;
          	box-shadow: 0 2px 10px 0 #d8dde6;
   			font-size: 14px;
   			font-weight: bold;
  			border-radius: 50px;
   			text-align: center;
			}
			
			.page-numbers-2 {
  			width: 35px;
 			height: 35px;
 			margin: 0 3px;
  			display: inline-block;
  			background-color: #ffffff;
  			line-height: 35px;
  			color: #292929;
  			-webkit-box-shadow: 0 2px 10px 0 #d8dde6;
          	box-shadow: 0 2px 10px 0 #d8dde6;
   			font-size: 14px;
   			font-weight: bold;
  			border-radius: 50px;
   			text-align: center;
			}
			
			.page-numbers-2.current, .page-numbers-2:hover, .page-numbers-2:focus {
  			background: #113366;
  			color: #ffffff;
 			-webkit-box-shadow: 0 2px 10px 0 #d8dde6;
        	box-shadow: 0 2px 10px 0 #d8dde6;
			}
			
			
		</style> 

<title>商城</title>

<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/front-end/front_CSS_JS/assets/img/favicon.png">
	
	<%@ include file= "/front-end/memFramework/include.file" %>
</head>
<body>
<%@ include file= "/front-end/memFramework/header.file" %>
<%-- <%@ include file= "/front-end/framework/header.file" %> --%>
	<!-- Start Preloader Area 載入畫面(圈圈)-->
	<div class="preloader">
		<div class="loader">
			<div class="sbl-half-circle-spin">
				<div></div>
			</div>
		</div>
	</div>
	<!-- End Preloader Area 載入畫面(圈圈)-->

	<!--*******************	
		Start Top Head Area  
		******************* -->
<%-- 	<%@ include file="../front_include_page/Top_head.jsp"%> --%>
	<!--*******************	
		End Top Head Area  
		******************* -->

	<!--*******************	
		Start Navbar Area  
		******************* -->
<%-- 	<%@ include file="../front_include_page/member_head.jsp"%> --%>
	<!--*******************	
		End Navbar Area  
		******************* -->

	 <section class="arrivals-products-area pb-20">
	    <div class="container">
			<table class="table table-striped" id="table1">
				<br>
				<br>
			    <div class="section-title">
                    <h2>購物訂單</h2>
                </div>
                <c:if test="${empty list}">
                	<tr><h5>您目前沒有購買紀錄</h5></tr>
                </c:if>
                
                <!--錯誤顯示>>>>-->
				<c:if test="${not empty errorMsgs}">
					<c:forEach var="error" items="${errorMsgs}"> 
							<h4 class="card-text" style="color:red">${error}</h4>
					</c:forEach>
				</c:if>
				<br>
				<!--<<<<錯誤顯示-->
                
				<thead>
					<tr>
						<th>訂單編號</th>
						<th>訂購時間</th>
						<th>訂單狀態</th>
						<th>付款狀態</th>
						<th>出貨狀態</th>
						<th>訂單總價</th>

						<th>付款</th>
						<th>查看詳情</th>
						<th>修改訂單</th>
					</tr>
				</thead>
				<tbody>
				    <%@ include file="page1.file" %> 
					<c:forEach var="OrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="s">
						<tr>
							<td>${OrderVO.o_no}</td>
							<td><fmt:formatDate value="${OrderVO.o_purtime}"
									pattern="yyyy-MM-dd" /></td>
							<td>${OrderVO.o_sta}</td>
							<td>${OrderVO.o_pay_sta}</td>
							<td>${OrderVO.o_ship_sta}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${OrderVO.o_totalpri}"/></td>
							
							<td>
									<c:choose>
										<c:when test="${OrderVO.o_sta == '取消訂單' || OrderVO.o_pay_sta == '已付款'}">
											<A HREF="javascript:presses2${s.count}()" class="btn btn-outline-secondary disabled">付款</a>
										</c:when>
										<c:otherwise>
											<A HREF="javascript:presses2${s.count}()" class="btn btn-outline-secondary">付款</a>
										</c:otherwise>
									</c:choose>
							</td>
							<td>
								<A HREF="javascript:presses${s.count}()" class="btn btn-outline-secondary">查看詳情</a>
							</td>
							<td>
								<form action="<%=request.getContextPath()%>/order/Order.do" method="post">
									<!-- 訂單狀態為"取消訂單"和付款狀態為"已付款" 則無法取消訂單 -->
									<input type="submit" class="btn btn-outline-secondary" id="cancelOrder" value="取消訂單" 
											${ (OrderVO.o_sta=="取消訂單")||(OrderVO.o_pay_sta=="已付款")  ?'disabled':''} >  
									<input type="hidden" name="o_no" value="${OrderVO.o_no}">
									<input type="hidden" name="action" value="front_cancelOrder">
								</form>
							</td>
						</tr>
						
						<script>
         					function presses${s.count}(){
        	 					document.open("<%=request.getContextPath()%>/order/Order.do?o_no=${OrderVO.o_no}&action=front_getOneForDiplay", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         					}
         					function presses2${s.count}(){
        	 					document.open("<%=request.getContextPath()%>/order/Order.do?o_no=${OrderVO.o_no}&action=Payment", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         					}
        				</script>
						
					</c:forEach>
				</tbody>
			</table>

		  	<b><font color=red class="page-numbers-3" ><%=whichPage%>/<%=pageNumber%></font></b>
		  	<%for(int i=1 ; i<=pageNumber ; i++){ %>
	  			<A href="<%=request.getRequestURI()%>?whichPage=<%=i%>" class="page-numbers-2"><%=i %></A>&nbsp;
	  		<%} %>
		</div>
	</section>




	<!--*******************	
		Start Footer Area  
		******************* -->
<%-- 	<%@ include file="../front_include_page/footer.jsp"%> --%>
	<!--*******************	
		End Footer Area  
		******************* -->

	<!--*******************	
		Start Go Top Area  
		******************* -->
<%-- 	<%@ include file="../front_include_page/startGoTop.jsp"%> --%>
	<!--*******************	
		End Go Top Area  
		******************* -->

	<!--*******************	
		Start Include JS File  
		******************* -->
<%-- 	<%@ include file="../front_include_page/JavaScript_Include.jsp"%> --%>
	<!--*******************	
		End Include JS File  
		******************* -->
		
<%-- <%@ include file= "/front-end/framework/footer.file" %> --%>
<%@ include file= "/front-end/memFramework/footer.file" %>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import=" java.text.DecimalFormat"%>

<%
	Vector<ProductVO> productInformList = (Vector<ProductVO>) session
			.getAttribute("shoppingCart");

	Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap");
//	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<!-- 前台_商品結帳頁面 -->
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

<title>商城</title>

<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/front-end/front_CSS_JS/assets/img/favicon.png">
</head>
<body>
<%@ include file= "/front-end/framework/header.file" %>

	<!--*******************	
		Start Top Head Area  
		******************* 
	<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--*******************	
		End Top Head Area  
		******************* -->

	<!--*******************	
		Start Navbar Area  
		******************* -->
	<%@ include file="../front_include_page/navbar.jsp"%>
	<!--*******************	
		End Navbar Area  
		******************* -->

        <!-- Start Page Banner -->
        <div class="page-title-area">
            <div class="container">
                <div class="page-title-content">
                    <h2>結帳</h2>

                    <ul>
                        <li><a href="index.html">Home</a></li>
                        <li>Checkout</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- End Page Banner -->

        <!-- Start Checkout Area -->
		<section class="checkout-area ptb-50">
            <div class="container">
                <form method="post" action="<%=request.getContextPath()%>/order/Order.do">
                    <div class="row">
                        <div class="col-lg-8 col-md-12">
                            <!-- <div class="user-actions">
                                <i class='bx bx-log-in'></i>
                                <span>Returning customer? <a href="#">Click here to login</a></span>
                            </div> -->

                            <!-- <div class="user-actions-2">
                                <i class='bx bx-code-alt'></i>
                                <span>Have a coupon? <a href="#">Click here to enter your code</a></span>
                            </div> -->
                            
                            <div class="billing-details">
                                <h3 class="title">結帳明細</h3>

                                <div class="row">
									<!--Start 錯誤訊息顯示 -->
                                    <c:if test="${not empty errorMsgs}">
                                    	<ul>
											<c:forEach var="error" items="${errorMsgs}"> 
												<li><h5 style="color:red">${error}</h5></li>
											</c:forEach>
										</ul>
<!-- 										<h5 class="card-text" style="color:red"></h5> -->
									</c:if>
                                    
                                    <!--End 錯誤訊息顯示 -->
                                    <!-- 預設帶入會員姓名 -->
                                    <div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <label>訂購者姓名</label>
<%--                                             <input type="text" class="form-control" value="${memberVO.mem_name }" disabled> --%>
                                            <input type="text" class="form-control">
                                            
                                        </div>
                                    </div>

                                    <div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <label>取貨方式<span class="required">*</span></label>
                                            <div class="select-box">
                                                <select name="o_prodel" id="select_prodel">
                                                    <option value="自取">自取</option>
                                                    <option value="宅配">宅配</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <!-- 預設帶入會員地址 -->
                                    <div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <label>配送地址 <span class="required">*</span></label>
<!--                                             <input type="text" class="form-control" name="o_deladrs" -->
<%--                                             		value="${(not empty o_deladrs)?o_deladrs:memVO.mem_address}" id="o_deladrs" disabled> --%>
												<input type="text" class="form-control" name="o_deladrs">
                                        </div>
                                    </div>

                                    <div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <label>付款方式<span class="required">*</span></label>
                                            <div class="select-box">
                                                <select name="o_paymthd" select="select_paymthd">
                                                    <option>匯款</option>
                                                    <option>信用卡</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <br> <br>

                            <!--start 商品明細 -->
                            <div class="col-lg-12 col-md-12">
                                <div class="billing-details">
                                <h3 class="title">商品明細</h3>
                                    <div class="cart-table table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">商品照</th>
                                                    <th scope="col">商品名稱</th>
                                                    <th scope="col">單價</th>
                                                    <th scope="col">購買數量</th>
                                                    <th scope="col">小計</th>
                                                </tr>
                                            </thead>
                
                                            <tbody>
                                    
			                                    <%if (productInformList != null && (productInformList.size() > 0)) {%>
			                                    	<%
			                                    	for(int index=0 ; index < productInformList.size() ; index++){
			                                    		ProductVO order = productInformList.get(index);
			                                    	%>
			                                        <tr class="top-class">
			                                            <td class="product-thumbnail">
			                                                <a href="<%=request.getContextPath()%>/productInfo/Product.do?pi_no=<%=order.getPiNo() %>&index=<%=index %>&action=cart&cart_action=delete" class="remove">
			                                                	<i class='bx bx-x'></i>
			                                                </a>
																<!--點選商品圖片會回到商品頁面 -->
			                                                <a href="<%=request.getContextPath()%>/product/Product.do?pi_no=<%=order.getPiNo() %>&action=showProductDetail">
			                                                    <img src="<%=request.getContextPath()%>/productImg/ProductImg.do?pi_no=<%=order.getPiNo()%>&action=showShopImage" alt="item">
			                                                </a>
			                                            </td>
			        
			                                            <td class="product-name">
			                                                <a href="<%=request.getContextPath()%>/product/Product.do?pi_no=<%=order.getPiNo() %>&action=showProductDetail"><%=order.getPiName() %></a>
			                                            </td>
			        
			                                            <td class="product-price">
			                                                <span class="unit-amount">$<%=new DecimalFormat(",###").format(order.getPiPri()) %></span>
			                                            </td>
			        
			                                            <td class="product-quantity">
			                                                <div class="input-counter">
			                                                    <input type="text" value="<%=Quamap.get(order.getPiNo())%>">
			                                                </div>
			                                                
			                                            </td>
			        
			                                            <td class="product-subtotal">
			                                                <span class="subtotal-amount">$<%=new DecimalFormat(",###").format( order.getPiPri()*Quamap.get(order.getPiNo()) ) %></span>
			                                            </td>
			                                        </tr>
													<%} %>
												<%}else{ %>
													<h4>您的購物車中沒有商品</h4>
												<%} %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--end 商品明細 -->
                        </div>

						<!--Start 計算金額方塊 -->
                        <div class="col-lg-4 col-md-12">
                            <div class="order-details">
                                <div class="cart-totals">
                                    <h3>Cart Totals</h3>
                                    <ul>
                                        <li>小計  <span id="sub_total"><%=new DecimalFormat(",###").format(Quamap.get(999))%></span><span>$</span></li>
                                        <li>運費 <span id="pri">0.00</span><span>$</span></li>
                                        <li>優惠折扣 <span id="discount">${(not empty discount)?discount:0.00}</span><span>$</span></li>
                                        <li>總計 <span id="total"><%=Quamap.get(999)%></span><span>$</span></li>
                                    </ul>

									<button type="submit" class="default-btn">
										確認結帳
										<span></span>
									</button>
                                </div>
                            </div>
                        </div>
                        <!--End 計算金額方塊 -->
                    </div>
                    <input type="hidden" name="action" value="insert">
<%--                     <input type="hidden" name="discount" value="${(not empty discount)?discount:0}"> --%>
<%--                     <input type="hidden" name="ci_no" value="${(not empty ci_no)?ci_no:1}"> --%>
                </form>
            </div>
        </section>
        <!-- End Checkout Area -->		
		
		
	<!--*******************	
		Start Footer Area  
		******************* -->			
		<%@ include file="../front_include_page/footer.jsp"%>
	<!--*******************	
		End Footer Area  
		******************* -->	
	
	<!--*******************	
		Start Go Top Area  
		******************* -->				
		<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--*******************	
		End Go Top Area  
		******************* -->			

	<!--*******************	
		Start Include JS File  
		******************* -->	
		<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->			
<%@ include file= "/front-end/framework/footer.file" %>
		
		<script>					
			$(function() {
				//計算總價格 >> 小計+運費-折扣(畫面載入時給的初始值)
// 				let total = parseInt($("#sub_total").text(), 10) + parseInt($("#pri").text(), 10) - parseInt($("#discount").text(), 10);
				let total = <%=Quamap.get(999)%> + parseInt($("#pri").text(), 10) - parseInt($("#discount").text(), 10);
<%-- 				let total = <%=Quamap.get(999)%> + parseInt($("#pri").text(), 10); --%>

				$("#total").text(total.toLocaleString())
				
				//判斷當配送方式為自取時
				//1.運費=0。2.將配送地址欄類disabled
				$("#select_prodel").on("change", function(){
					let a = $("#select_prodel").val();
					if(a == "自取"){
						$("#so_deladrs").prop("disabled", true);
						$("#pri").text("0.00");
					}else{
						$("#so_deladrs").prop("disabled", false);
						$("#pri").text("100");
					}
					//計算總價
					let total = <%=Quamap.get(999)%> + parseInt($("#pri").text(), 10) - parseInt($("#discount").text(), 10);
					$("#total").text(total.toLocaleString())
				})
			})
		</script>
	
</body>
</html>
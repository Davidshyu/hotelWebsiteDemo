<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="com.mealCategory.model.*"%>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file= "/front-end/framework/include.file" %>
</head>
<body>
<%@ include file= "/front-end/framework/header.file" %>
<div class="container">
     <div class="section-header">
         <h2>客房服務</h2>
     </div>
     <div class="row d-flex justify-content-center">
     	<div class="col-md-3">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb justify-content-center text-uppercase">
						<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/front-end/roomservice/listOneRoomServiceOrder.jsp">查詢訂單</a></li>
					</ol>
					<ol class="breadcrumb justify-content-center text-uppercase">
						<li class="breadcrumb-item">
						<a href="<%=request.getContextPath()%>/front-end/roomservice/login.jsp">房號登入</a></li>
					</ol>
				</nav>
     	</div>
     	<div class="col-md-9">
     <div class="row" style="min-height: 45vh;">
        <div class="container">
          <div class="d-flex justify-content-center mb-md-0 fs-1 p-3">
			<div class="container">
			<!--     ------------------------------------------------------------------------------------- -->
				<jsp:useBean id="list" scope="page"
					class="com.mealCategory.model.MealCategoryService" />
				<div id="tab" data-wow-delay="0.1s">
					<ul class="nav nav-tabs">
						<c:forEach var="mealcategoryVO" items="${list.all}">
							<li class="nav-item">
									<a class="nav-link" href="#">${mealcategoryVO.meal_category_name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<jsp:useBean id="list2" scope="page" class="com.meal.model.MealService"/>
				<div id="content" class="p-2">
					<%
					List<MealCategoryVO> MealCategoryVOList = list.getAll();
					for (MealCategoryVO vo : MealCategoryVOList) {
						Integer meal_category_id = vo.getMeal_category_id();
					%>
					<div class="content">
						<c:forEach var="mealVO"	items="<%=list2.getMealCategory(meal_category_id)%>">
						<c:if test="${mealVO.meal_status == true}">
							<form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping.do" method="POST">
								<img src="<%=request.getContextPath()%>/Meal/DBGifReader4?meal_id=${mealVO.meal_id}" width=100px> ${mealVO.meal_name}
									<div align="center">數量： 
										<input type="text" name="meal_quantity" size="3" value="${mealVO.meal_quantity}">
									</div>
									<div align="center" class="m-3">
										<input type="submit" name="Submit" value="放入購物車">
									</div>
								<input type="hidden" name="meal_id" value="${mealVO.meal_id}">
								<input type="hidden" name="meal_name" value="${mealVO.meal_name}">
								<input type="hidden" name="meal_description" value="${mealVO.meal_description}">
								<input type="hidden" name="meal_quantity" value="${mealVO.meal_quantity}"> 
								<input type="hidden" name="meal_price" value="${mealVO.meal_price}">
								<input type="hidden" name="action" value="ADD">
							</form>
						</c:if>
						</c:forEach>
					</div>
					<%}%>
				</div>
				<jsp:include page="Cart.jsp" flush ="true" />
				
			</div>
		</div>
	  </div>
	</div>
     	</div>
     </div>
</div>     
<%@ include file= "/front-end/framework/footer.file" %>

</body>

<script>
	$('#content .content').hide().first().show().addClass('active');
	$('#tab li').click(function() {
		var index = $(this).index();
		$('#content .content.active').removeClass('active').fadeOut(function() {
			$('#content .content').eq(index).fadeIn().addClass('active')
		});
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>    
<%@ page import="com.mealCategory.model.*"%> 
<!DOCTYPE html>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="list" scope="page" class="com.mealCategory.model.MealCategoryService" />
<div id = "tab">
<ul>
<c:forEach var="mealcategoryVO" items="${list.all}">`
				<li>
				<a class="aa" href="#">${mealcategoryVO.meal_category_name}</a>	
				</li>
</c:forEach>
</ul>
</div>
<%-- <jsp:useBean id="list" scope="page" class="com.mealCategory.model.MealCategoryService" /> --%>
<%-- <c:forEach var="mealcategoryVO" items="${list.all}">` --%>
<!-- <div class="actContentDiv2"> -->
<!--     <div class="tab_container"> -->
<!--         <div class="tab_list_block"> -->
<!--             <ul class="tab_list"> -->
<%--                 <li><a href="#" data-target="tab1" class="tab -on">${mealcategoryVO.meal_category_name}</a></li> --%>
<!--             </ul> -->
<!--             </div> -->
<%--             </c:forEach> --%>

<jsp:useBean id="list2" scope="page" class="com.meal.model.MealService" />
								<div id="content">
								<c:forEach var="mealVO" items="<%=list2.getAll()%>">
									<div class = "content">
									<c:if test="${mealVO.meal_category_id==1}">
								<img src="<%=request.getContextPath()%>/Meal/DBGifReader4?meal_id=${mealVO.meal_id}" width = 100px>
											${mealVO.meal_name}
											</c:if>
									</div>
								</c:forEach>
								</div>
								
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--     <script src="/CFA104G1/front/lib/wow/wow.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/easing/easing.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/waypoints/waypoints.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/counterup/counterup.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/owlcarousel/owl.carousel.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/tempusdominus/js/moment.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/tempusdominus/js/moment-timezone.min.js"></script> -->
<!--     <script src="/CFA104G1/front/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script> -->
<script>
// const city = document.querySelector('.city');
// const station = document.querySelector('#station')
// city.addEventListener('click',function(){
//     console.log("有交換到");
//     station.innerHTML = '';
//     const array = aa[city.value];
//     for(let b of array){
//         console.log(b);
//         const option = document.createElement('option');
//         option.textContent = b;
//         station.append(option);
//     }
// });


$('#content .content').hide().show().addClass('active');
$('#tab li').click(function(){
  var index = $(this).index();
  $('#content .content.active').removeClass('active').fadeOut(function(){
    $('#content .content').eq(index).fadeIn().addClass('active')
  });
});
</script>
</html>
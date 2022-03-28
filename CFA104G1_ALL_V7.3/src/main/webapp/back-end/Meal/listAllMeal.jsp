<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meal.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MealService mealSvc = new MealService();
    List<MealVO> list = mealSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有餐點資料 - listAllMeal.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>
<%@ include file= "/back-end/framework/includeTableCss.file" %>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

<!-- <link href="/CFA104G1/resource/css/aa.css" rel="stylesheet" type="text/css"/> -->
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="/CFA104G1/resource/css/styles.css" rel="stylesheet" type="text/css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
        crossorigin="anonymous"></script>

</head>
<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">所有餐點資料</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/Meal/listAllMeal.jsp">餐點列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/Meal/select_page.jsp">餐點查詢</a></li>

            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<div class="card mb-4">
    <div class="card-body">
<table id="datatablesSimple" class="table table-bordered table-hover">
<thead>
	<tr>
		<th>餐點編號</th>
		<th>餐點類別</th>
		<th>餐點描述</th>
		<th>餐點價格</th>
		<th>餐點名稱</th>
		<th>餐點數量</th>
		<th>餐點狀態</th>
		<th>餐點圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	</thead>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="mealVO" items="${list}" >
		
		<tr>
			<td>${mealVO.meal_id}</td>
			<td>${mealVO.meal_category_id}</td>
			<td>${mealVO.meal_description}</td>
			<td>${mealVO.meal_price}</td>
			<td>${mealVO.meal_name}</td>
			<td>${mealVO.meal_quantity}</td> 
			<td>${mealVO.meal_status? "上架" : "下架"}</td>	
			<td><img src="<%=request.getContextPath()%>/Meal/DBGifReader4?meal_id=${mealVO.meal_id}" width = 150px></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Meal/meal.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="meal_id"  value="${mealVO.meal_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Meal/meal.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="meal_id"  value="${mealVO.meal_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
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
<%@ include file= "/back-end/framework/includeTableJs.file" %>
        
        

<%-- <%@ include file="page2.file" %> --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>
<script src="/CFA104G1/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="/CFA104G1/js/datatables-simple-demo.js"></script>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mealCategory.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MealCategoryVO mealCategoryVO = (MealCategoryVO) request.getAttribute("mealCategoryVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>餐點資料 - ListOneMealCategory.jsp</title>
<%@ include file= "/back-end/framework/include.file" %>
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
            <h1 class="m-0">類別資料</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/MealCategory/listAllMealCategory.jsp">類別列表</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/MealCategory/select_page.jsp">類別查詢</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
			<table id="datatablesSimple">
			<thead>
				<tr>
					<th>類別編號</th>
					<th>類別名稱</th>
				</tr>
				</thead>
				<tr>
					<td><%=mealCategoryVO.getMeal_category_id()%></td>
					<td><%=mealCategoryVO.getMeal_category_name()%></td>
				</tr>
			</table>
		</div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>
<script src="/CFA104G1/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="/CFA104G1/js/datatables-simple-demo.js"></script>
</body>
</html>
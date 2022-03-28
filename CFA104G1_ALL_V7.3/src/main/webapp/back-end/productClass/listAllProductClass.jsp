<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productClass.model.*" %>

<%
ProductClassService productClassSvc = new ProductClassService();
	List<ProductClassVO> list = productClassSvc.getAll();
	request.setAttribute("list", list);			//////////////////////////${list}
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
<!-- <link rel="icon" type="image/png" -->
<!-- 	href="../back_CSS_JS/assets/imgaes/logo/favicon.png"> -->

</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">管理商品類別</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/product/select_Page.jsp">商品管理</a></li>
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
							<h3 class="card-title">商品類別列表</h3>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped" id="table1">
                                <thead>
                                    <tr>
                                        <th>類別編號</th>
                                        <th>類別名稱</th>
                                        <th>修改</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="list" items="${list}">
	                                    <tr>
	                                        <td>${list.pcNo}</td>
	                                        <td>${list.pcName}</td>
	                                        <td>
	                                        	<form
													action="<%=request.getContextPath()%>/productClass/ProductClass.do"
													method="post">
													<input type="submit" class="btn btn-outline-secondary" value="修改">
													<input type="hidden" name="pcNo" value="${list.pcNo}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
	                                        </td>
	                                    </tr>
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
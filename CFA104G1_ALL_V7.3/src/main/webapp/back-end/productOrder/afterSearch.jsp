<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productClass.model.*"%>
<%@ page import="com.shopOrder.model.*" %>

<%-- <% --%>
<!-- // 	SecOrderService secOrderSvc = new SecOrderService(); -->
<!-- // 	List<SecOrderVO> list = secOrderSvc.getAll(); -->
<!-- // 	pageContext.setAttribute("order_list", list); -->
	
<%-- %> --%>
<%-- <jsp:useBean id="order_list" scope="request" class="com.sec_order.model.SecOrderService"/> --%>

<%-- <%Vector<SecOrderVO> productInformList = (Vector<SecOrderVO>) session.getAttribute("order_list"); %> --%>


<!-- 後台_顯示搜尋後的訂單列表頁面 -->
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

</head>
<body>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">訂單列表</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/productOrder/Order_select_page.jsp">管理訂單</a></li>
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/productOrder/listAllOrder.jsp">訂單列表</a></li>
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
							<h3 class="card-title">訂單查詢結果列表</h3>
						</div>

						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>訂單編號</th>
										<th>訂購時間</th>
										<th>會員編號</th>
										<th>訂單狀態</th>
										<th>付款狀態</th>
										<th>出貨狀態</th>
										<th>訂單總價</th>
										<th>查看詳情</th>
										<th>修改訂單</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="OrderVO" items="${order_list_search}" varStatus="s">
										<tr>
											<td>${OrderVO.o_no}</td>
											<td><fmt:formatDate value="${OrderVO.o_purtime}" pattern="yyyy-MM-dd"/></td>
											<td>${OrderVO.mem_no}</td>
											<td>${OrderVO.o_sta}</td>
											<td>${OrderVO.o_pay_sta}</td>
											<td>${OrderVO.o_ship_sta}</td>
											<td>${OrderVO.o_totalpri}</td>
<%-- 											<td>${OrderVO.o_discount_price}</td> --%>
											<td>
<!-- 												<form -->
<%-- 													action="<%=request.getContextPath()%>/secOrder/SecOrder.do" --%>
<!-- 													method="post"> -->
													
<!-- 													<input type="submit" class="btn btn-outline-secondary" value="查看詳情"> -->
<%-- 													<input type="hidden" name="so_no"value="${OrderVO.so_no}">  --%>
<!-- 													<input type="hidden" name="action" value="getOneForDiplay"> -->
<!-- 												</form> -->
												<A HREF="javascript:presses${s.count}()" class="btn btn-outline-secondary">查看詳情</a>
											</td>
											<td>
												<form
													action="<%=request.getContextPath()%>/order/Order.do""
													method="post">
													<input type="submit" class="btn btn-outline-secondary" value="修改">
													<input type="hidden" name="o_no" value="${OrderVO.o_no}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
											</td>
										</tr>
										
										<script>
         									function presses${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/order/Order.do?o_no=${OrderVO.o_no}&action=getOneForDiplay", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
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
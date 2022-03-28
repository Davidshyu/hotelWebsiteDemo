<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<script
  		src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
  		integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
  		crossorigin="anonymous">
	</script>
		
		
<meta charset="UTF-8">
<title>後台管理</title>
<link rel="icon" type="image/png"
	href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

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
				<div class="card-content">
					<div class="card-body">
						<h4>修改訂單</h4><br>
						<h6>
							<a href="<%=request.getContextPath()%>/back_end/productOrder/listAllOrder.jsp">回到訂單列表</a>
						</h6>
						<!--錯誤顯示>>>>-->
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="error" items="${errorMsgs}"> 
									<h4 class="card-text" style="color:red">${error}</h4>
							</c:forEach>
							<h5 class="card-text" style="color:red"></h5>
						</c:if>
							<br>
						<!--<<<<錯誤顯示-->
	
						<form method="post" class="form form-horizontal" action="<%=request.getContextPath()%>/order/Order.do">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<label>訂單編號</label>
									</div>
									<div class="col-md-8 form-group">
										<label>${productOrderVO.o_no}</label>
									</div>

									<div class="col-md-4">
										<label>訂單狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_sta" name="o_sta" >
                                            <option value = "通知自取">通知自取</option>
                                            <option value = "備貨中" >備貨中</option>
                                            <option value = "完成訂單" >完成訂單</option>
                                            <option value = "取消訂單">取消訂單</option>
                                    	</select>	
									</div>

									<div class="col-md-4">
										<label>付款狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_pay" name="o_pay_sta">
                                            <option value = "待付款">待付款</option>
                                            <option value = "已付款">已付款</option>
                                    	</select>	
									</div>
								
									<div class="col-md-4">
										<label>出貨狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_ship" name="o_ship_sta">
                                            <option value = "未出貨">未出貨</option>
                                            <option value = "已出貨">已出貨</option>
                                            <option value = "待取貨">待取貨</option>
                                    	</select>	
									</div>
									
									<div class="col-md-4">
										<label>運送方式</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_prodel" name="o_prodel">
                                            <option value = "自取">自取</option>
                                            <option value = "宅配">宅配</option>
                                    	</select>	
									</div>
									
									<div class="col-md-4">
										<label>配送地址</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="o_deladrs" value="${productOrderVO.o_deladrs}">
									</div>
									
									<div class="col-md-4">
										<label>出貨日期</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="date" class="form-control"
											name="o_shipdate" value="<fmt:formatDate pattern="yyyy-MM-dd" 
            																		  value="${productOrderVO.o_shipdate}"/>">
									</div>
									
									<div class="col-sm-12 d-flex justify-content-end">
										<button type="submit" class="btn btn-primary me-1 mb-1">修改</button>
										<button type="reset" class="btn btn-light-secondary me-1 mb-1">清除</button>
									</div>
										<input type="hidden" name="o_no" value="${productOrderVO.o_no}">
										<input type="hidden" name="o_purtime" value="${productOrderVO.o_purtime}">
										<input type="hidden" name="mem_no" value="${productOrderVO.mem_no}">
<%-- 										<input type="hidden" name="ci_no" value="${OrderVO.ci_no}"> --%>
										<input type="hidden" name="o_totalpri" value="${productOrderVO.o_totalpri}">
										<input type="hidden" name="o_paymthd" value="${productOrderVO.o_paymthd}">
										<input type="hidden" name="o_delcost" value="${productOrderVO.o_delcost}">
<%-- 										<input type="hidden" name="o_discount_price" value="${productOrderVO.o_discount_price}"> --%>
										<input type="hidden" name="action" value="update">
								</div>
							</div>
						</form>
					</div>
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

		$(function() {
            var temp="${productOrderVO.o_sta}"; 
            $("#mySelect_sta").val(temp);
        });
		
		$(function() {
            var temp="${productOrderVO.o_pay_sta}"; 
            $("#mySelect_pay").val(temp);
        });
		
		$(function() {
            var temp="${productOrderVO.o_ship_sta}"; 
            $("#mySelect_ship").val(temp);
        });
		
		$(function() {
            var temp="${productOrderVO.o_prodel}"; 
            $("#mySelect_prodel").val(temp);
        });
		
		</script>
</body>
</html>
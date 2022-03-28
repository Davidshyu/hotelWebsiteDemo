<%@page import="com.meal.model.Meal"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.roomServiceOrderList.model.*"%>
<%@ page import="com.meal.model.*"%>
<%@ page import="java.util.*"%>

<%RoomServiceOrderListService list = new RoomServiceOrderListService(); %>
			
<%
 			String serviceorderid = request.getParameter("service_order_id");
 			if(serviceorderid != null){
			Integer service_order_id = Integer.parseInt(serviceorderid);
		%>
							
			
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/resource/css/styles.css" rel="stylesheet" type="text/css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
        crossorigin="anonymous"></script>
</head>
<body bgcolor='white'>
<div class="container">
    <div class="row" style="min-height: 45vh;">
        <div class="container">
          <div class="d-flex justify-content-center mb-md-0 fs-1 p-3">
			<table id="datatablesSimple">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>餐點名稱</th>
					<th>餐點價格</th>
					<th>餐點數量</th>
					<th>餐點金額</th>
					<th>訂單金額</th>		
				</tr>
				</thead>
				<%!int count = 0;%>
				<%for(RoomServiceOrderListVO RoomServiceOrderListVO :list.getAllListByPK(service_order_id)){
					if(RoomServiceOrderListVO.getService_order_id() == service_order_id){ %>
				<tr>
					<td><%=RoomServiceOrderListVO.getService_order_id()%></td>
					<%  
			  				MealService mealService = new MealService();
			 				List<MealVO> mealVO = mealService.getAll();
			 				for(MealVO MealVO : mealVO){
							%>		
					<%if(RoomServiceOrderListVO.getMeal_id() == MealVO.getMeal_id()){%>
					    <td><%=MealVO.getMeal_name()%></td>
					    <%}%>
					    <%}%>
					<td><%=RoomServiceOrderListVO.getMeal_price()%></td>
					<td><%=RoomServiceOrderListVO.getMeal_quantity()%></td>
					<td><%=RoomServiceOrderListVO.getMeal_total()%></td>
					
					<%count += RoomServiceOrderListVO.getMeal_total();%>
				<%}%>
				<%}%>
					<td><%="總計:" + count%></td>
				</tr>
				<%}%>
			</table>

		</div>
		</div>
	</div>
</div>     
     
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/resources/js/datatables-simple-demo.js"></script>
</body>
</html>
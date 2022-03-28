<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , com.meal.model.Meal"%>
<%@ page import="com.roomServiceOrderList.model.*"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mode II 範例程式 - Checkout.jsp</title>
 <%@ include file= "/front-end/framework/include.file" %>
 
</head>
<body bgcolor="#FFFFFF">
<%@ include file= "/front-end/framework/header.file" %>
 <div class="container">
     <div class="section-header">
         <h2>客房服務 - 結帳</h2>
     </div>
     <div class="row d-flex justify-content-center">
		 <table border="1" width="720">
			<tr bgcolor="#999999">
				<th width="200">名稱</th>
				<th width="100">描述</th>
				<th width="100">價格</th>
				<th width="100">數量</th>
				<th width="120">總價</th>
			</tr>
			<%
				Vector<Meal> buylist = (Vector<Meal>) session.getAttribute("shoppingcart");
						String amount =  (String) request.getAttribute("amount");
						String roomid = (String)session.getAttribute("roomid");
				%>	
			<%
				for (int i = 0; i < buylist.size(); i++) {
					Meal order = buylist.get(i);
					String meal_name = order.getMeal_name();
					String meal_description = order.getMeal_description();
					float meal_price = order.getMeal_price();
					int meal_quantity = order.getMeal_quantity();
				%>
			<tr>
				<td width="200"><div align="center"><b><%=meal_name%></b></div></td>
				<td width="100"><div align="center"><b><%=meal_description%></b></div></td>
				<td width="100"><div align="center"><b><%=meal_price%></b></div></td>
				<td width="100"><div align="center"><b><%=meal_quantity%></b></div></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td></td>
				<td></td>
				<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
				<td></td>
				<td> <font color="red"><b>$<%=amount%></b></font> </td>
			</tr>
		</table>
	</div>
	<div class="content d-flex justify-content-end">
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RoomServiceOrder/RoomServiceOrder.do" name="form1">
			<%!Integer count = 0;%>
	<%
			for (int a = 0; a < buylist.size(); a++) {
				Meal order2 = buylist.get(a);
				int meal_id = order2.getMeal_id();
				float meal_price2 = order2.getMeal_price();
				int meal_quantity2 = order2.getMeal_quantity();
			%>
	<input type="hidden" name="meal_id<%= ++count %>" value="<%=meal_id%>">
	<input type="hidden" name="meal_price<%= count %>" value="<%=meal_price2%>">
	<input type="hidden" name="meal_quantity<%= count %>" value="<%=meal_quantity2%>">
	<%}%>
	<input type="hidden" name="room_id" value="<%=roomid %>">
	<input type="hidden" name="count" value="<%= count %>">
	<input type="hidden" name="meal_total" value=<%=amount%>>
	<input type="hidden" name="action" value="insert">
	<input type="submit" name="action" value="確認送出">
	</FORM>
	
	<!-- <%= count = 0 %> -->
	<%buylist = null;
	  session.setAttribute("shoppingcart", buylist);
	%>
	</div>
<hr><p>

     
     </div>
 </div>

<%@ include file= "/front-end/framework/footer.file" %>
</body>
</html>

<%@page import="com.emplyee.model.EmplyeeVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emplyee.model.*"%>

<%
EmplyeeVO emplyeeVO = (EmplyeeVO) request.getAttribute("emplyeeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改</title>
<%@ include file= "/back-end/framework/include.file" %>

<style>
table#table-1 {
	background-color: #FFCC99;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: #CC3333;
	display: inline;
}

a {
    text-decoration:none;
    color: #CC3333;
}

input{
    margin-right:190px;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #FFCC99;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">員工管理</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/emplyee/listAllEmp.jsp">員工列表</a></li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="card col-lg-12">
              <div class="card-header">
                <h2 class="card-title">員工資料修改</h2>
              </div>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emplyee/emp.do" name="form1">
				<table class="table">
					<tr>
						<td>員工編號:<font color=red><b>*</b></font></td>
						<td><%=emplyeeVO.getEmpno()%></td>
					</tr>
					
					<jsp:useBean id="rolesSvc" scope="page" class="com.roles.model.RolesService" />
			        <tr>
				        <td>員工角色:<font color=red><b>*</b></font></td>
				        <td>
				            <select size="1" name="roles_id">
					             <c:forEach var="rolesVO" items="${rolesSvc.all}">
						         <option value="${rolesVO.roles_id}" ${(emplyeeVO.roles_id==rolesVO.roles_id)?'selected':'' } >${rolesVO.roles_name}
					            </c:forEach>
				            </select>
				        </td>
			        </tr>
			
					<tr>
						<td>員工姓名:<font color=red><b>*</b></font></td>
						<td><input type="TEXT" name="emp_name" size="15"
							value="<%=emplyeeVO.getEmp_name()%>" /></td>
					</tr>
					<tr>
						<td>員工狀態:<font color=red><b>*</b></font></td>
						<td><select size="1" name="emp_status">
								<option value= true
									<c:if test="${emplyeeVO.emp_status == true}">selected</c:if>>在職</option>
								<option value= false
									<c:if test="${emplyeeVO.emp_status == false}">selected</c:if>>離職</option>
						</select></td>
					</tr>
		
				</table>
				<br> <input type="hidden" name="action" value="update">
				<input type="hidden" name="empno" value="<%=emplyeeVO.getEmpno()%>">
				<input type="submit" value="送出修改">
			</FORM>
		</div>
    </section>
    <!-- /.content -->
	<!--*******************
		Start Include JS File
		******************* -->
<%@ include file= "/back-end/framework/footer.file" %>
</body>
</html>
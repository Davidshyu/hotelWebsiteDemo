<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emplyee.model.*"%>

<%
EmplyeeDAO dao = new EmplyeeDAO();
List<EmplyeeVO> list = dao.getAll();
pageContext.setAttribute("list", list);
%>

<jsp:useBean id="rolesSvc" scope="page" class="com.roles.model.RolesService" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>員工管理</title>
<%@ include file= "/back-end/framework/include.file" %>
<%@ include file= "/back-end/framework/includeTableCss.file" %>
<style>
.a1{
     width:90%;
     margin: auto;
   }
.b1{
     margin-left:82%;
   }
</style>
</head>

<body class="sb-nav-fixed">
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
      <div class="col-lg-12">
		<div class="card mb-4 a1">
			<div class="card-header">
				<i class="fas fa-table me-1"></i> 管理員工
				<a class="b1 btn btn-outline-secondary" href="<%= request.getContextPath() %>/back-end/emplyee/addEmp.jsp" role="button">新增員工</a>
			</div>
			<div class="card-body">

				<table id="datatablesSimple" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>員工編號</th>
							<th>員工角色</th>
							<th>員工姓名</th>
							<th>員工狀態</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>員工編號</th>
							<th>員工角色</th>
							<th>員工姓名</th>
							<th>員工狀態</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="emplyeeVO" items="${list}">
							<tr>
								<td>${emplyeeVO.empno}</td>
								<td>${rolesSvc.getOneRole(emplyeeVO.roles_id).roles_name}</td>
								<td>${emplyeeVO.emp_name}</td>
								<td>
								    <c:if test="${emplyeeVO.emp_status == true}">在職</c:if>
									<c:if test="${emplyeeVO.emp_status == false}">離職</c:if>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emplyee/emp.do" style="margin-bottom: 0px;">
										<input type="submit" value="修改"> 
										<input type="hidden" name="empno" value="${emplyeeVO.empno}"> 
									    <input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
								<c:if test="${emplyeeVO.roles_id != 1}">
									<FORM id="${emplyeeVO.empno}" METHOD="post" ACTION="<%=request.getContextPath()%>/emplyee/emp.do" style="margin-bottom: 0px;">
										<input id="${emplyeeVO.empno}" class="del" type="submit" value="刪除"> 
										<input type="hidden" name="empno" value="${emplyeeVO.empno}"> 
										<input type="hidden" name="action" value="delete">
									</FORM>
								</c:if>
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
<%@ include file= "/back-end/framework/includeTableJs.file" %>

</body>

</html>
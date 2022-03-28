<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
MemDAO dao = new MemDAO();
List<MemVO> list = dao.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>會員管理</title>

<%@ include file= "/back-end/framework/include.file" %>
<%@ include file= "/back-end/framework/includeTableCss.file" %>

</head>

<body class="sb-nav-fixed">
<%@ include file= "/back-end/framework/header.file" %>
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">管理會員</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            <!-- 
              <li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/emplyee/listAllEmp.jsp">會員列表</a></li>
               -->
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="col-lg-12">
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table me-1"></i> 管理會員
			</div>
			<div class="card-body table-responsive p-3">

				<table id="datatablesSimple" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>會員編號</th>
							<th>會員信箱</th>
							<th>會員名稱</th>
							<th>會員電話</th>
							<th>會員地址</th>
							<th>會員狀態</th>
							<th>修改操作</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>會員編號</th>
							<th>會員信箱</th>
							<th>會員名稱</th>
							<th>會員電話</th>
							<th>會員地址</th>
							<th>會員狀態</th>
							<th>修改操作</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="memVO" items="${list}">
							<tr>
								<td>${memVO.mem_id}</td>
								<td>${memVO.mem_email}</td>
							    <td>${memVO.mem_name}</td>
								<td>${memVO.mem_phone}</td>
								<td>${memVO.mem_address}</td>
								<td>
									<c:if test="${memVO.mem_status == 0}">未啟用</c:if>
									<c:if test="${memVO.mem_status == 1}">啟用</c:if>
									<c:if test="${memVO.mem_status == 2}">停權</c:if>
                               </td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1">
										<input type="hidden" name="action" value="getOne_For_Update">
										<input type="hidden" name="mem_id" value="${memVO.mem_id}"> 
										<input type="submit" value="修改">
									</FORM>

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
	<!--*******************
		End Include JS File
		******************* -->
	
</body>

</html>